import { useParams } from "react-router-dom";
import { useState, useEffect } from "react";
import data from '../../config.json';
import RequireLogin from "../../components/RequireLogin";
import RenderTable from "../../components/RenderTable";

const computerEntities = [
  { key: "sessions", label: "Sessions", endpoint: id => `computer/sessions?id=${id}` },
  { key: "storages", label: "Storages", endpoint: id => `computer/storages?id=${id}` }
];

function ComputerDetails() {
  const { id } = useParams();
  const [selected, setSelected] = useState(computerEntities[0].key);
  const [dataList, setDataList] = useState([]);
  const [loading, setLoading] = useState(false);
  const [computerInfo, setComputerInfo] = useState(null);

  const baseUrl = data["server-url"];
  const entity = computerEntities.find(e => e.key === selected);

  // Fetch computer info for recap
  useEffect(() => {
    fetch(baseUrl + "computer?id=" + id)
      .then(res => res.json())
      .then(computer => setComputerInfo(computer))
      .catch(() => setComputerInfo(null));
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

  // Affiche toutes les infos du computer
  function renderComputerRecap(computer) {
    if (!computer) return null;
    return (
      <div style={{
        border: "1px solid #bbb",
        borderRadius: "8px",
        padding: "1rem",
        marginBottom: "1.5rem",
        background: "#f9f9f9",
        maxWidth: "400px"
      }}>
        <b>hostname:</b> {computer.hostname}<br />
        <b>ip:</b> {computer.ip}<br />
        <b>maxRam:</b> {computer.maxRam}<br />
        <b>maxCpu:</b> {computer.maxCpu}<br />
        <b>maxStorage:</b> {computer.maxStorage}
      </div>
    );
  }

  return (
    <RequireLogin>
      <div style={{ padding: "2rem" }}>
        <h1>Computer Details</h1>
        {renderComputerRecap(computerInfo)}
        <div style={{ marginBottom: "1.5rem" }}>
          <label htmlFor="computer-entity-select" style={{ fontWeight: "bold", marginRight: "1rem" }}>
            Choose associated entity:
          </label>
          <select
            id="computer-entity-select"
            value={selected}
            onChange={e => setSelected(e.target.value)}
            style={{ padding: "0.5rem", borderRadius: "6px", fontSize: "1rem" }}
          >
            {computerEntities.map(ent => (
              <option key={ent.key} value={ent.key}>{ent.label}</option>
            ))}
          </select>
        </div>
        {loading ? <p>Loading...</p> : <RenderTable data={dataList} selected={selected} />}
      </div>
    </RequireLogin>
  );
}

export default ComputerDetails;