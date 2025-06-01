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

    async function handleCreateAccount () {
        console.log("create account");

        let full_url = data["server-url"]+"/user";

        let body = JSON.stringify(
            {
                id: 0,
                username : username,
                password : password
            }
        );

        let headers = {
            "Content-Type": "application/json",
            // "Access-Control-Allow-Origin": "*"
        };
 
        const response = await fetch(full_url, {
            method: "POST",
            body: body,
            headers: headers
        });

        // uncomment if you want to see the result of the request - it is the id, username and pwd of 
        // the newly created user
        // let base_user = await response.json();

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