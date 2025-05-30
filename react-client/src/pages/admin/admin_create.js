import { useState } from "react"
import { useNavigate } from 'react-router-dom';
import data from '../../config.json'

function Create(){

    const [choice, setChoice] = useState("account");
    const [username, setUsername] = useState("");
    const [password, setUserPassword] = useState("");
    const [groupName, setGroupName] = useState("");
    const [groupPassword, setGroupPassword] = useState("");
    const [groupDesc, setGroupDesc] = useState("");
    const navigate = useNavigate();

    function handleCreateAccount () {
        console.log("create account");

        let full_url = data["server-url"]+"new-user";

        // let body = JSON.stringify(
        //         {
        //             username : username,
        //             password : password
        //         }
        //     );
        //     console.log(body);
        // const response = await fetch(full_url, {
        //     method: "POST",
        //     body: body
        // });

        // console.log(response);

        // const response1 = await fetch(data["server-url"]+"/users", {
        //     method: "GET",
        //     headers : {
        //         "Content-Type" : "application/json"
        //     }
        // });
        // console.log(response1);

        const xhr = new XMLHttpRequest();
        xhr.open("POST", full_url);
        xhr.setRequestHeader(
            "Content-Type","application/json"
        );
        const body = {
            "username": username,
            "password": password
        };
        xhr.onload = () => {
        if (xhr.readyState == 4 && xhr.status == 201) {
            console.log(JSON.parse(xhr.responseText));
        } else {
            console.log(`Error: ${xhr.status}`);
        }
        };
        xhr.send(body);
        navigate("/");
    }

    const handleCreateGroup = () => {
        console.log("create group");
        navigate("/");
    }


    const form = () => {
        if (choice==="account") {
            return (
                <form action={handleCreateAccount}>
                    <h2>Create a new account</h2>
                    <div className="mb-3">
                        <label htmlFor="username" className="form-label">Username :</label>
                        <input onChange={e => {setUsername(e.target.value)}} className="form-control" id="username"/>
                    </div>
                    <div className="mb-3">
                        <label htmlFor="password" className="form-label">Password :</label>
                        <input onChange={e => {setUserPassword(e.target.value)}} type="password" className="form-control" id="password"/>
                    </div>
                    <button type="submit" className="btn btn-primary">Create account</button>
                </form>
            )
        }else{
            return (
                <form action={handleCreateGroup}>
                    <h2>Create a new group</h2>
                    <div className="mb-3">
                        <label htmlFor="username" className="form-label">Group name:</label>
                        <input onChange={e => {setGroupName(e.target.value)}} className="form-control" id="groupName"/>
                    </div>
                    <div className="mb-3">
                        <label htmlFor="password" className="form-label">Group Password:</label>
                        <input onChange={e => {setGroupPassword(e.target.value)}} type="password" className="form-control" id="groupPassword"/>
                    </div>
                    <div className="mb-3">
                        <label htmlFor="username" className="form-label">Group description:</label>
                        <input onChange={e => {setGroupDesc(e.target.value)}} className="form-control" id="groupDesc"/>
                    </div>
                    <button type="submit" className="btn btn-primary">Create group</button>
                </form>
            )
        }
    }

    return (
        <>
        <select
            value={choice}
            onChange={e=>setChoice(e.target.value)}
        >
            <option value="account" >Account</option>
            <option value="group">Group</option>
        </select>
        {form()}
        </>
    );
}

export default Create