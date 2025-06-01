import { useState } from "react"
import data from '../config.json'

function LoginForm() {
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")
    const [isLoggedIn, setIsLoggedIn] = useState(() => localStorage.getItem("isLoggedIn") === "true");
    const [loginError, setLoginError] = useState(false);
    const baseUrl = data["server-url"];

    const handleLogin = async (e) => {
        e.preventDefault();
        setLoginError(false);
        try {
            const res = await fetch(
                `${baseUrl}login?username=${encodeURIComponent(username)}&password=${encodeURIComponent(password)}`
            );
            if (!res.ok) {
                setLoginError(true);
                return;
            }
            await res.json();
            setIsLoggedIn(true);
            localStorage.setItem("isLoggedIn", "true");
            localStorage.setItem("username", username);
            window.location.reload();
        } catch (error) {
            setLoginError(true);
        }
    };

    const handleLogout = () => {
        setIsLoggedIn(false);
        localStorage.removeItem("isLoggedIn");
        localStorage.removeItem("username");
        setUsername("");
        setPassword("");
        window.location.reload();
    };

    if (isLoggedIn) {
        return (
            <button onClick={handleLogout}>Log out</button>
        );
    }

    return (
        <form onSubmit={handleLogin} style={{ display: "inline-block" }}>
            {loginError && (
                <span style={{ color: "red", marginRight: "1rem" }}>
                    Invalid username or password
                </span>
            )}
            <input
                type="text"
                placeholder="Username"
                value={username}
                onChange={e => setUsername(e.target.value)}
                style={{ marginRight: "0.5rem" }}
            />
            <input
                type="password"
                placeholder="Password"
                value={password}
                onChange={e => setPassword(e.target.value)}
                style={{ marginRight: "0.5rem" }}
            />
            <button type="submit">Log in</button>
        </form>
    );
}

export default LoginForm;