import { useParams } from "react-router-dom";
import { useState, useEffect } from "react";
import data from '../../config.json';
import RequireLogin from "../../components/RequireLogin";
import RenderTable from "../../components/RenderTable";

const groupEntities = [
  { key: "memberships", label: "Memberships", endpoint: id => `group/memberships?id=${id}` }
];

function GroupDetails() {
  const { id } = useParams();
  const [selected, setSelected] = useState(groupEntities[0].key);
  const [dataList, setDataList] = useState([]);
  const [loading, setLoading] = useState(false);
  const [groupInfo, setGroupInfo] = useState(null);

  const baseUrl = data["server-url"];
  const entity = groupEntities.find(e => e.key === selected);

  // Fetch group info for recap
  useEffect(() => {
    fetch(baseUrl + "group?id=" + id)
      .then(res => res.json())
      .then(group => setGroupInfo(group))
      .catch(() => setGroupInfo(null));
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


  function renderGroupRecap(group) {
    if (!group) return null;
    return (
      <div style={{
        border: "1px solid #bbb",
        borderRadius: "8px",
        padding: "1rem",
        marginBottom: "1.5rem",
        background: "#f9f9f9",
        maxWidth: "400px"
      }}>
        <b>name:</b> {group.name}<br />
        <b>description:</b> {group.description || "No description available"}
        <br />
      </div>
    );
  }

  return (
    <RequireLogin>
      <div style={{ padding: "2rem" }}>
        <h1>Group Details</h1>
        {renderGroupRecap(groupInfo)}
        <div style={{ marginBottom: "1.5rem" }}>
          <label htmlFor="group-entity-select" style={{ fontWeight: "bold", marginRight: "1rem" }}>
            Choose associated entity:
          </label>
          <select
            id="group-entity-select"
            value={selected}
            onChange={e => setSelected(e.target.value)}
            style={{ padding: "0.5rem", borderRadius: "6px", fontSize: "1rem" }}
          >
            {groupEntities.map(ent => (
              <option key={ent.key} value={ent.key}>{ent.label}</option>
            ))}
          </select>
        </div>
        {loading ? <p>Loading...</p> : <RenderTable data={dataList} selected={selected} />}
      </div>
    </RequireLogin>
  );
}

export default GroupDetails;