use std::time::Duration;

use chrono::Utc;
use sysinfo::RefreshKind;
use sysinfo::CpuRefreshKind;
use tokio::time::sleep;

#[tokio::main]
async fn main()  {
    //spawn request thread
    tokio::spawn(async move{
        let mut system = sysinfo::System::new_with_specifics(
            RefreshKind::nothing().with_cpu(CpuRefreshKind::everything()),
        );
        let mock_host_data = 
                [
                    (String::from("mihai"), String::from("192.168.20.3")), 
                    (String::from("astrid"), String::from("192.168.9.12")), 
                    (String::from("emeric"), String::from("172.16.10.2")), 
                    (String::from("alexandre"), String::from("172.16.33.300"))
                ];

        let mock_hostnames = [
            String::from("pikachu"),
            String::from("darthvader"),
            String::from("goku"),
            String::from("spock")
        ];

        loop{

            let random = (rand::random::<u32>())%4;
            let random2 = (rand::random::<u32>())%4;

            let usage = system.global_cpu_usage().to_string();
            let (username, ip) = mock_host_data.get(random as usize).unwrap();
            let process_count = system.processes().len().to_string();
            let hostname = mock_hostnames.get(random2 as usize).unwrap();
            let dt = Utc::now().to_string();

            let params = 
                [
                    ("hostname",  hostname.as_str()), 
                    ("hostip", ip.as_str()), 
                    ("cpuUsage", usage.as_str()),
                    ("processCount", process_count.as_str()),
                    ("date", &dt.as_str()),
                    ("user", username.as_str())
                ];
            
            let client = reqwest::Client::new();
            let res = client.post("http://localhost:8080/ajoutsysinfo")
                .form(&params)
                .send()
                .await.unwrap();

            println!("Sent request {} {}!", res.status().as_str(), res.text().await.unwrap());

            sleep(Duration::from_secs(3)).await;
            system.refresh_cpu_usage();
        }
    });
    
    //do infinite computing
    loop{
        sleep(Duration::from_secs(10)).await;
    }
}
