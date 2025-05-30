// login form
import { useState } from "react"

function LoginForm({ setToken }){
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")

    // function handleLogin(e){

    // }
    const handleLogin = (e) => {
        e.preventDefault()
        try {
            setToken(username);
        } catch (error) {
            console.log(error);
        }
    }

    return (
        <div className="container" style={{marginTop:"10vh"}}>
            <form onSubmit={handleLogin}>
                <h2>Login to your account</h2>
                <p>Welcome back!</p>
                <div className="mb-3">
                    <label htmlFor="username" className="form-label">Username :</label>
                    <input onChange={e => {setUsername(e.target.value)}} className="form-control" id="username"/>
                </div>
                <div className="mb-3">
                    <label htmlFor="password" className="form-label">Password :</label>
                    <input onChange={e => {setPassword(e.target.value)}} type="password" className="form-control" id="password"/>
                </div>
                <button type="submit" className="btn btn-primary">LOG IN</button>
                {/* <p style={{marginTop:"2vh"}}>Don't have an account?<Link to={'/register'}>Create an account</Link></p> */}
                <p><br />Demo User: <br />Email: user@example.com <br />Password: password12345</p>
            </form>
        </div>
    )
}

export default LoginForm