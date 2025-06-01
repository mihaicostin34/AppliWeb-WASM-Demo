import React, { useEffect, useState } from "react";
import data from '../config.json'
import CreateUserForm from "./creation/CreateUserForm";
import CreateGroupForm from "./creation/CreateGroupForm";
import CreateMembershipForm from "./creation/CreateMembershipForm";
import CreateComputerForm from "./creation/CreateComputerForm";
import RequireLogin from "../components/RequireLogin";

function Manage() {
  const [choice, setChoice] = useState("createUser");
  const [users, setUsers] = useState([]);
  const [groups, setGroups] = useState([]);
  const [confirmation, setConfirmation] = useState("");
  const baseUrl = data["server-url"];

  useEffect(() => {
    fetch(baseUrl + "users")
      .then(res => res.json())
      .then(setUsers)
      .catch(err => console.error("Error users:", err));
  }, [baseUrl]);

  useEffect(() => {
    fetch(baseUrl + "groups")
      .then(res => res.json())
      .then(setGroups)
      .catch(err => console.error("Error groups:", err));
  }, [baseUrl]);

  // Unified handler for all creations
  const handleCreated = (type, item) => {
    if (type === "user") {
      setUsers(prev => [...prev, item]);
      setConfirmation("User created successfully!");
    } else if (type === "group") {
      setGroups(prev => [...prev, item]);
      setConfirmation("Group created successfully!");
    } else if (type === "membership") {
      setConfirmation("Membership created successfully!");
    } else if (type === "computer") {
      setConfirmation("Computer created successfully!");
    }
    setTimeout(() => setConfirmation(""), 3000);
  };

  return (
    <RequireLogin>
      <div style={{ padding: "2rem" }}>
        <div style={{
          display: "flex",
          alignItems: "center",
          gap: "1rem",
          marginBottom: "2rem"
        }}>
          <label htmlFor="manage-choice" style={{ fontWeight: "bold" }}>What do you want to manage?</label>
          <select
            id="manage-choice"
            value={choice}
            onChange={e => setChoice(e.target.value)}
          >
            <option value="createUser">User</option>
            <option value="createGroup">Group</option>
            <option value="createMembership">Membership</option>
            <option value="createComputer">Computer</option>
          </select>
        </div>
        <div style={{ maxWidth: "400px" }}>
            {choice === "createUser" && (
                <CreateUserForm
                baseUrl={baseUrl}
                onUserCreated={user => handleCreated("user", user)}
                />
            )}
            {choice === "createGroup" && (
                <CreateGroupForm
                baseUrl={baseUrl}
                onGroupCreated={group => handleCreated("group", group)}
                />
            )}
            {choice === "createMembership" && (
                <CreateMembershipForm
                baseUrl={baseUrl}
                users={users}
                groups={groups}
                onMembershipCreated={() => handleCreated("membership")}
                />
            )}
            {choice === "createComputer" && (
                <CreateComputerForm
                  baseUrl={baseUrl}
                  onComputerCreated={() => handleCreated("computer")}
                />
            )}
            {confirmation && (
                <div style={{ color: "green", marginBottom: "1rem" }}>{confirmation}</div>
            )}
        </div>
      </div>
    </RequireLogin>
  );
}

export default Manage;