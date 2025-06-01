import { useState } from "react";

function CreateGroupForm({ baseUrl, onGroupCreated }) {
  const [groupName, setGroupName] = useState("");
  const [groupDesc, setGroupDesc] = useState("");

  const handleCreateGroup = (e) => {
    e.preventDefault();
    fetch(baseUrl + "group", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ name: groupName, description: groupDesc })
    })
      .then((res) => {
        if (!res.ok) throw new Error("Error while creating group");
        return res.json();
      })
      .then((newGroup) => {
        onGroupCreated(newGroup);
        setGroupName("");
        setGroupDesc("");
      })
      .catch((err) => console.error(err));
  };

  return (
    <div style={{ padding: "1rem", maxWidth: "400px" }}>
      <h2>Create a new group</h2>
      <form onSubmit={handleCreateGroup}>
        <input
          type="text"
          placeholder="Group name"
          value={groupName}
          onChange={e => setGroupName(e.target.value)}
          required
          style={{ display: "block", width: "100%", marginBottom: "1rem" }}
        />
        <textarea
          placeholder="Group description"
          value={groupDesc}
          onChange={e => setGroupDesc(e.target.value)}
          required
          rows={4}
          style={{ display: "block", width: "100%", marginBottom: "1rem", resize: "vertical" }}
        />
        <button type="submit">Créer</button>
      </form>
    </div>
  );
}

export default CreateGroupForm;