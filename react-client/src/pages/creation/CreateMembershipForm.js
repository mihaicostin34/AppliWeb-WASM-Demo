import { useState } from "react";

function CreateMembershipForm({ baseUrl, users, groups, onMembershipCreated }) {
  const [selectedUserId, setSelectedUserId] = useState("");
  const [selectedGroupId, setSelectedGroupId] = useState("");
  const [role, setRole] = useState("VIEWER");

  // Sort users and groups alphabetically
  const sortedUsers = [...users].sort((a, b) => a.username.localeCompare(b.username));
  const sortedGroups = [...groups].sort((a, b) => a.name.localeCompare(b.name));

  const handleCreateMembership = (e) => {
    e.preventDefault();
    if (!selectedUserId || !selectedGroupId) {
      alert("Please select a user and a group.");
      return;
    }

    fetch(baseUrl + "membership", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        role,
        user: { id: parseInt(selectedUserId, 10) },
        group: { id: parseInt(selectedGroupId, 10) }
      })
    })
      .then((res) => {
        if (!res.ok) throw new Error("Error creating membership");
        return res.json();
      })
      .then(() => {
        if (onMembershipCreated) onMembershipCreated();
        setSelectedUserId("");
        setSelectedGroupId("");
        setRole("VIEWER");
      })
      .catch((err) => {
        console.error(err);
        alert("Error creating membership");
      });
  };

  return (
    <div style={{ padding: "1rem", maxWidth: "400px" }}>
      <h2>Put a user in a group</h2>
      <form onSubmit={handleCreateMembership}>
        <div style={{ marginBottom: "1rem" }}>
          <label style={{ display: "block", marginBottom: "0.3rem" }}>
            User :
          </label>
          <select
            value={selectedUserId}
            onChange={e => setSelectedUserId(e.target.value)}
            style={{ width: "100%", padding: "0.3rem" }}
          >
            <option value="">-- Select a user --</option>
            {sortedUsers.map(user => (
              <option key={user.id} value={user.id}>{user.username}</option>
            ))}
          </select>
        </div>
        <div style={{ marginBottom: "1rem" }}>
          <label style={{ display: "block", marginBottom: "0.3rem" }}>
            Group :
          </label>
          <select
            value={selectedGroupId}
            onChange={e => setSelectedGroupId(e.target.value)}
            style={{ width: "100%", padding: "0.3rem" }}
          >
            <option value="">-- Select a group --</option>
            {sortedGroups.map(group => (
              <option key={group.id} value={group.id}>{group.name}</option>
            ))}
          </select>
        </div>
        <div style={{ marginBottom: "1rem" }}>
          <label style={{ display: "block", marginBottom: "0.3rem" }}>
            Role :
          </label>
          <select
            value={role}
            onChange={e => setRole(e.target.value)}
            style={{ width: "100%", padding: "0.3rem" }}
          >
            <option value="ADMIN">Admin</option>
            <option value="VIEWER">Viewer</option>
          </select>
        </div>
        <button type="submit">Create</button>
      </form>
    </div>
  );
}

export default CreateMembershipForm;