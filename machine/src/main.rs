use reqwest::Client;
use tokio::time::sleep;

use std::{env, time::Duration};
use serde_json::json;
use chrono::Utc;
use rand::{distr::Uniform, Rng};

#[tokio::main]
async fn main()  {

    // if you don't want to use environment variables, use the following: 
    //
    //  let selected_job = "Users";  // replace the value with the required job type
    //  let server_url = <actual_server_url>


    let server_url = match env::var("URL"){
        Ok(url) => url,
        Err(_) => String::from("http://localhost:8080")
    };

    let selected_job = env::var("JOB");
    match selected_job{
        Ok(job) => {
            match job.as_str(){
                "Users" => {
                    let mock_users = 
                    [
                        // key: username, password will be the same as the username
                        "mihai",
                        "astrid",
                        "emeric",
                        "alexandre"
                    ];
                    for user in mock_users{
                        let body = json!(
                            {
                                "id" : 0 as u8,
                                "username" : user,
                                "password" : user
                            }
                        );
                        let req_url = server_url.clone().to_owned();
                        let res = Client::new()
                            .post(req_url + "/user")
                            .json(&body)
                            .send().await;
                        println!("{:?}", res);
                    }
                },
                "Computers" => {
                    let mock_computers = vec![
                        // key: hostname, ip, maxRam, maxCpu, maxStorage
                        ("computer01", "192.168.1.10", 16384, 8, 512000),
                        ("computer02", "192.168.1.11", 8192, 4, 256000),
                        ("computer03", "192.168.1.12", 32768, 16, 1024000),
                        ("computer04", "192.168.1.13", 4096, 2, 128000),
                        ("computer05", "192.168.1.14", 24576, 12, 768000),
                        ("computer06", "192.168.1.15", 6144, 6, 192000),
                        ("computer07", "192.168.1.16", 12288, 6, 384000),
                        ("computer08", "192.168.1.17", 20480, 10, 640000),
                        ("computer09", "192.168.1.18", 10240, 5, 320000),
                        ("computer10", "192.168.1.19", 16384, 8, 500000),
                    ];

                    for (hostname, ip, ram, cpu, storage) in mock_computers{
                        let body = json!(
                            {
                                "id" : 0 as u8,
                                "hostname" : hostname,
                                "ip" : ip,
                                "maxRam" : ram,
                                "maxCpu" : cpu,
                                "maxStorage" : storage 
                            }
                        );
                        let req_url = server_url.clone().to_owned();
                        let res = Client::new()
                            .post(req_url + "/computer")
                            .json(&body)
                            .send().await;
                        println!("{:?}", res);
                    }

                },
                "Groups" =>{
                    let groups = vec![
                        //key: name, description
                        ("Global", "Global group"),
                        ("Development", "Responsible for building and maintaining software products."),
                        ("IT", "Manages infrastructure, networks, and technical support."),
                        ("HR", "Oversees recruitment, employee relations, and compliance."),
                        ("Marketing", "Focuses on brand strategy, campaigns, and market research."),
                        ("Operations", "Coordinates logistics, supply chain, and process optimization."),
                    ];
                    for (name, desc) in groups{
                        let body = json!(
                            {
                                "id" : 0 as u8,
                                "name" : name,
                                "description" : desc
                            }
                        );
                        let req_url = server_url.clone().to_owned();
                        let res = Client::new()
                            .post(req_url + "/group")
                            .json(&body)
                            .send().await;
                        println!("{:?}", res);
                    }
                },
                "Memberships" =>{
                    let memberships = vec![
                        //key: Id, role, user_id, group_id
                        ("ADMIN", 1, 1),   
                        ("VIEWER", 2, 1),   
                        ("ADMIN", 3, 1),            
                        ("VIEWER", 4, 1),           
                        ("ADMIN", 1, 2),     
                        ("ADMIN", 2, 3),
                    ];
                    for (role, uid, gid) in memberships{
                        let body = json!(
                            {
                                "id" : 0 as u8,
                                "role" : role,
                                "user_id": uid,
                                "group_id" : gid
                            }
                        );
                        let req_url = server_url.clone().to_owned();
                        let res = Client::new()
                            .post(req_url + "/membership")
                            .json(&body)
                            .send().await;
                        println!("{:?}", res);
                    } 
                },
                "Processes"=>{
                    let processes = vec![
                        //key: name, pid
                        ("nginx", 1001),
                        ("postgres", 1002),
                        ("redis", 1003),
                        ("java-app", 1004),
                        ("node-server", 1005),
                        ("python-script", 1006),
                        ("docker-daemon", 1007),
                        ("kubelet", 1008),
                        ("vault", 1009),
                        ("elasticsearch", 1010),
                        ("fluentd", 1011),
                        ("grafana", 1012),
                        ("prometheus", 1013),
                        ("zookeeper", 1014),
                        ("consul", 1015),
                    ];
                    for (name, pid) in processes{
                        let body = json!(
                            {
                                "name" : name,
                                "pid" : pid,
                            }
                        );
                        let req_url = server_url.clone().to_owned();
                        let res = Client::new()
                            .post(req_url + "/process")
                            .json(&body)
                            .send().await;
                        println!("{:?}", res);
                    }

                },
                "Storages" =>{
                    let usage_data = vec![
                        //key: used storage (in gb), computer id, user id
                        (120, 1, 1),
                        (85, 2, 3),
                        (300, 3, 2),
                        (250, 4, 5),
                        (400, 5, 1),
                        (150, 6, 4),
                        (100, 7, 2),
                        (275, 8, 6),
                        (90, 9, 3),
                        (60, 10, 5),
                        (190, 1, 2),
                        (210, 3, 4),
                        (180, 6, 6),
                        (130, 8, 1),
                        (95, 9, 4),
                    ];
                    for (storage, cid, uid) in usage_data{
                        let body = json!(
                            {
                                "storageUsed" : storage,
                                "computer_id" : cid,
                                "user_id" : uid
                            }
                        );
                        let req_url = server_url.clone().to_owned();
                        let res = Client::new()
                            .post(req_url + "/storage")
                            .json(&body)
                            .send().await;
                        println!("{:?}", res);
                    }

                },
                "ResourceUsages" =>{
                    let resource_usages = vec![
                        //key: date, used ram, used cpu, resource_user_id
                        ("2025-06-01T10:00:00", 1.2, 0.35, 1),
                        ("2025-06-01T10:05:00", 0.9, 0.20, 12),
                        ("2025-06-01T10:10:00", 1.5, 0.50, 3),
                        ("2025-06-01T10:15:00", 1.1, 0.40, 14),
                        ("2025-06-01T10:20:00", 2.0, 0.75, 5),
                        ("2025-06-01T10:25:00", 0.7, 0.15, 11),
                        ("2025-06-01T10:30:00", 1.3, 0.45, 2),
                        ("2025-06-01T10:35:00", 1.8, 0.60, 3),
                        ("2025-06-01T10:40:00", 2.1, 0.80, 4),
                        ("2025-06-01T10:45:00", 1.6, 0.55, 5),
                        ("2025-06-01T10:50:00", 1.0, 0.25, 11),
                        ("2025-06-01T10:55:00", 1.7, 0.70, 2),
                        ("2025-06-01T11:00:00", 1.9, 0.85, 3),
                        ("2025-06-01T11:05:00", 2.2, 0.90, 14),
                        ("2025-06-01T11:10:00", 2.4, 0.95, 15),
                    ];

                    for (date, ram, cpu, uid) in resource_usages{
                        let body = json!(
                            {
                                "id" : 0,
                                "time" : date,
                                "usedRam" : ram,
                                "usedCpu" : cpu, 
                                "resourceUser_id" : uid
                            }
                        );
                        let req_url = server_url.clone().to_owned();
                        let res = Client::new()
                            .post(req_url + "/process/resourceUsage")
                            .json(&body)
                            .send().await;
                    }
                    //to add more data: 
                    tokio::spawn(async move{
                        loop{
                            let date = Utc::now().to_string();
                            let uid = (rand::random::<u32>())%4 + 1;
                            
                            let ram = (rand::random::<f32>())%16.0;
                            let cpu = (rand::random::<f32>())%1.0;
                            println!("{ram} {cpu}");
                            let body = json!(
                            {
                                "id" : 0,
                                "time" : date.as_str(),
                                "usedRam" : ram,
                                "usedCpu" : cpu, 
                                "resourceUser_id" : uid
                            }
                            );
                            let req_url = server_url.clone().to_owned();
                            let res = Client::new()
                                .post(req_url + "/process/resourceUsage")
                                .json(&body)
                                .send().await;
                            sleep(Duration::from_secs(5)).await;
                        }
                            
                    });


                },
                "Sessions" =>{
                    let sessions = vec![
                        // key: start time, end time, user id, computer id
                        ("2025-06-01T08:00:00", "2025-06-01T12:00:00", 1, 2),
                        ("2025-06-01T09:15:00", "2025-06-01T11:45:00", 2, 4),
                        ("2025-06-01T07:30:00", "2025-06-01T09:30:00", 4, 1),
                        ("2025-06-01T06:00:00", "2025-06-01T08:00:00", 6, 6),
                        ("2025-06-01T12:30:00", "2025-06-01T14:30:00", 3, 8),
                        ("2025-06-01T08:45:00", "2025-06-01T10:45:00", 4, 10),
                    ];
                    let null_sessions = vec![
                        ("2025-06-01T10:00:00",                3, 5), // still active
                        ("2025-06-01T13:00:00",                5, 3), // still active
                        ("2025-06-01T14:00:00",                 2, 7), // still active
                        ("2025-06-01T11:00:00",               1, 9), // still active
                    ];

                    for (start, end, uid, cid) in sessions{
                        let body = json!(
                            {   
                                "startTime" : start,
                                "endTime" : end,
                                "user_id" : uid, 
                                "computer_id" : cid
                            }
                        );
                        let req_url = server_url.clone().to_owned();
                        let res = Client::new()
                            .post(req_url + "/session")
                            .json(&body)
                            .send().await;
                        println!("{:?}", res);
                    }

                    for (start, uid, cid) in null_sessions{
                        let body = json!(
                            {   
                                "startTime" : start,
                                "endTime" : null,
                                "user_id" : uid, 
                                "computer_id" : cid
                            }
                        );
                        let req_url = server_url.clone().to_owned();
                        let res = Client::new()
                            .post(req_url + "/session")
                            .json(&body)
                            .send().await;
                        println!("{:?}", res);
                    }

                }
                _ => {
                    println!("{} is not a valid job", job);
                }
            }
        },
        Err(_) => {
            println!("No job selected: doing nothing");
        }
    }

}
