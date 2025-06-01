import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import data from '../config.json';
import RequireLogin from "../components/RequireLogin";
import RenderTable from "../components/RenderTable";

const entities = [
  { key: "users", label: "Users" },
  { key: "groups", label: "Groups" },
  { key: "sessions", label: "Sessions" },
  { key: "computers", label: "Computers" },
  { key: "logs", label: "Logs" },
  { key: "memberships", label: "Memberships" },
  { key: "storages", label: "Storages" },
  { key: "processes", label: "Processes" },
  { key: "resourceUsages", label: "Resource Usages" }
];

function Stats() {
  const [selected, setSelected] = useState("users");
  const [dataList, setDataList] = useState([]);
  const [loading, setLoading] = useState(false);
  const [subData, setSubData] = useState(null);
  const [subTitle, setSubTitle] = useState("");

  const baseUrl = data["server-url"];
  const navigate = useNavigate();

  useEffect(() => {
    setLoading(true);
    fetch(baseUrl + selected)
      .then(res => res.json())
      .then(setDataList)
      .catch(() => setDataList([]))
      .finally(() => setLoading(false));
    setSubData(null);
    setSubTitle("");
  }, [selected, baseUrl]);

  return (
    <RequireLogin>
      <div style={{ padding: "2rem" }}>
        <h1>Stats</h1>
        <div style={{ marginBottom: "1.5rem" }}>
          <label htmlFor="entity-select" style={{ fontWeight: "bold", marginRight: "1rem" }}>
            Choose entity:
          </label>
          <select
            id="entity-select"
            value={selected}
            onChange={e => setSelected(e.target.value)}
            style={{ padding: "0.5rem", borderRadius: "6px", fontSize: "1rem" }}
          >
            {entities.map(ent => (
              <option key={ent.key} value={ent.key}>{ent.label}</option>
            ))}
          </select>
        </div>
        {loading ? (
          <p>Loading...</p>
        ) : (
          <>
            <RenderTable data={dataList} selected={selected} navigate={navigate} />
            {subData && (
              <>
                <h2 style={{ marginTop: "2rem" }}>{subTitle}</h2>
                <RenderTable data={subData} selected={selected} navigate={navigate} />
              </>
            )}
          </>
        )}
      </div>
    </RequireLogin>
  );
}

export default Stats;