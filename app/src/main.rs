// Prevent console window in addition to Slint window in Windows release builds when, e.g., starting the app via file manager. Ignored on other platforms.
// #![cfg_attr(not(debug_assertions), windows_subsystem = "windows")]

use std::{collections::HashMap, error::Error, fmt::write, future::Future, rc::Rc, sync::{Arc, Mutex}, thread, time::Duration};

use slint::{ModelRc, SharedString, VecModel};

slint::include_modules!();

use serde::{Deserialize, Serialize};

#[derive(Debug, Serialize, Deserialize)]
struct Person {
    name: String,
    surname: String,
}

#[derive(Debug, Serialize, Deserialize)]
struct Address {
    street: String,
    city: String,
}

#[derive(Debug, Serialize, Deserialize)]
struct ApiResponse {
    people: Vec<Person>,
    addresses: Vec<Address>,
}

#[tokio::main]
async fn main() -> Result<(), Box<dyn Error>> {
    let ui = App::new()?;
    let ui_weak = ui.as_weak();

    ui.on_send_data(move |choice, str1, str2| { 
        println!("{:?}, {:?}, {:?}", choice, str1, str2);
        if choice == 1{
            //requette ajout personne
            tokio::spawn(async move{
                let client = reqwest::Client::new();
                let mut map : HashMap<&str, String>= HashMap::new();
                map.insert("name", String::from(str1));
                map.insert("surname", String::from(str2));
                client.post("http://localhost:5000/ajoutPersonne").form(&map).send().await.unwrap();
            });

        }else if choice == 2{
            //requette ajout adresse
            tokio::spawn(async move {
                let client = reqwest::Client::new();
                let mut map : HashMap<&str, String>= HashMap::new();
                map.insert("street", String::from(str1));
                map.insert("city", String::from(str2));
                client.post("http://localhost:5000/ajoutAdresse").form(&map).send().await.unwrap();
            });
        }
    });

    slint::spawn_local(async move {
        // chaque 5 secondes demande les donnees a la base de donnees
        loop{
            let ui_weak_1 = ui_weak.clone();
            tokio::spawn(async move{
                println!("Seding request from thread");
                let client = reqwest::Client::new();
                let http_response = client.get("http://localhost:5000/list").send().await.unwrap()
                                                .json::<ApiResponse>().await.unwrap();
                
                let people: Vec<SharedString> = http_response.people
                                                .iter()
                                                .map(|p| SharedString::from(format!("{} {}", p.name, p.surname)))
                                                .collect();

                let addresses: Vec<SharedString> = http_response.addresses
                                                .iter()
                                                .map(|a| SharedString::from(format!("{}, {}", a.street, a.city)))
                                                .collect();
                let ui_clone1 = ui_weak_1.clone();
                slint::invoke_from_event_loop( move||ui_clone1.unwrap().set_people(ModelRc::from(Rc::new(VecModel::from(people))))).unwrap();
                
                let ui_clone2 = ui_weak_1.clone();
                slint::invoke_from_event_loop(move || ui_clone2.unwrap().set_addresses(ModelRc::from(Rc::new(VecModel::from(addresses))))).unwrap();
            });
            tokio::time::sleep(Duration::from_secs(5)).await;
        }
    }).unwrap();

    ui.run()?;
    Ok(())
}
