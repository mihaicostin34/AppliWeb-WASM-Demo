import { useState } from "react";

function CreateUserForm({ baseUrl, onUserCreated}) {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  async function handleCreateAccount(e) {
    e.preventDefault();
    try {
      // Création de l'utilisateur
      const res = await fetch(baseUrl + "user", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, password })
      });
      if (!res.ok) throw new Error("Error while creating user");
      const newUser = await res.json();

      if (newUser.id) {
        const membershipRes = await fetch(baseUrl + "membership", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({ user: {id: newUser.id}, group: {id: 1}, role: "VIEWER" })
        });
        if (!membershipRes.ok) throw new Error("Error while adding user to default group");
      }

      onUserCreated(newUser);
      setUsername("");
      setPassword("");
    } catch (err) {
      console.error(err);
    }
  }

  return (
    <div style={{ padding: "1rem", maxWidth: "400px" }}>
      <h2>Create a new user</h2>
      <form onSubmit={handleCreateAccount}>
        <input
          type="text"
          placeholder="Username"
          value={username}
          onChange={e => setUsername(e.target.value)}
          required
        />
        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={e => setPassword(e.target.value)}
          required
        />
        <button type="submit">Create</button>
      </form>
    </div>
  );
}

export default CreateUserForm;