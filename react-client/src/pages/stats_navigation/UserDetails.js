import { useParams } from "react-router-dom";
import { useState, useEffect } from "react";
import data from '../../config.json';
import RequireLogin from "../../components/RequireLogin";
import RenderTable from "../../components/RenderTable";

const userEntities = [
  { key: "sessions", label: "Sessions", endpoint: id => `user/sessions?id=${id}` },
  { key: "memberships", label: "Memberships", endpoint: id => `user/memberships?id=${id}` },
  { key: "storages", label: "Storages", endpoint: id => `user/storages?id=${id}` }
];

function UserDetails() {
  const { id } = useParams();
  const [selected, setSelected] = useState(userEntities[0].key);
  const [dataList, setDataList] = useState([]);
  const [loading, setLoading] = useState(false);
  const [userInfo, setUserInfo] = useState(null);

  const baseUrl = data["server-url"];
  const entity = userEntities.find(e => e.key === selected);

  // Fetch user info for recap
  useEffect(() => {
    fetch(baseUrl + "user?id=" + id)
      .then(res => res.json())
      .then(user => setUserInfo(user))
      .catch(() => setUserInfo(null));
  }, [id, baseUrl]);

  useEffect(() => {
    if (!entity) return;
    setLoading(true);
    fetch(baseUrl + entity.endpoint(id))
      .then(res => res.json())
      .then(setDataList)
      .catch(() => setDataList([]))
      .finally(() => setLoading(false));
  }, [selected, id, baseUrl, entity]);

  // Affiche uniquement le nom de l'utilisateur
  function renderUserRecap(user) {
    if (!user) return null;
    return (
      <div style={{
        border: "1px solid #bbb",
        borderRadius: "8px",
        padding: "1rem",
        marginBottom: "1.5rem",
        background: "#f9f9f9",
        maxWidth: "400px"
      }}>
        <b>username:</b> {user.username}
      </div>
    );
  }

  return (
    <RequireLogin>
      <div style={{ padding: "2rem" }}>
        <h1>User Details</h1>
        {renderUserRecap(userInfo)}
        <div style={{ marginBottom: "1.5rem" }}>
          <label htmlFor="user-entity-select" style={{ fontWeight: "bold", marginRight: "1rem" }}>
            Choose associated entity:
          </label>
          <select
            id="user-entity-select"
            value={selected}
            onChange={e => setSelected(e.target.value)}
            style={{ padding: "0.5rem", borderRadius: "6px", fontSize: "1rem" }}
          >
            {userEntities.map(ent => (
              <option key={ent.key} value={ent.key}>{ent.label}</option>
            ))}
          </select>
        </div>
        {loading ? <p>Loading...</p> : <RenderTable data={dataList} selected={selected} />}
      </div>
    </RequireLogin>
  );
}

export default UserDetails;