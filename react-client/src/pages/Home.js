import { useState, useEffect } from "react";

function Home() {
  const [username, setUsername] = useState("");

  useEffect(() => {
    const storedUsername = localStorage.getItem("username");
    if (storedUsername) {
      setUsername(storedUsername);
    } else {
      setUsername("");
    }
  }, []);

  return (
    <div style={{ padding: "2rem" }}>
      <h1>Home Page</h1>
      {username ? (
        <p style={{ fontWeight: "bold", fontSize: "1.2rem" }}>
          Hello back, {username}!
        </p>
      ) : (
        <p style={{ color: "red", fontWeight: "bold", fontSize: "1.2rem" }}>
          Please log in to access the application.
        </p>
      )}
      <p>This is a simple application to manage users, groups, and memberships.</p>
      <p>
        Use the navigation menu to access different sections of the application.
        You can create users, groups, and memberships in the "Manage" section.
      </p>
    </div>
  );
}

export default Home;