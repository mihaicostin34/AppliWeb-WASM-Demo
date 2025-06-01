import { useState } from "react";
import { useNavigate } from "react-router-dom";

const mainFieldMap = {
  user: { key: "username", label: "User", route: "user" },
  computer: { key: "hostname", label: "Computer", route: "computer" },
  group: { key: "name", label: "Group", route: "group" },
  session: { key: "startDate", label: "Session", route: "session" },
  log: { key: "time", label: "Log", route: "log" },
  process: { key: "name", label: "Process", route: "process" },
  resourceUsage: { key: "date", label: "Resource Usage", route: "resourceusage" },
  storage: { key: "storageUsed", label: "Storage Used", route: "storage" }
};

function RenderTable({ data, selected }) {
  const [sortConfig, setSortConfig] = useState({ key: null, direction: "asc" });
  const navigate = useNavigate();

  if (!Array.isArray(data) || data.length === 0) return <div>No data available.</div>;

  let columns = Object.keys(data[0]);
  if (selected === "users") columns = columns.filter(col => col !== "password" && col !== "id");
  else columns = columns.filter(col => col !== "id");

  let sortedData = [...data];
  if (sortConfig.key) {
    sortedData.sort((a, b) => {
      let aValue = a[sortConfig.key];
      let bValue = b[sortConfig.key];
      if (typeof aValue === "object" && aValue !== null) {
        const refType = colToEntity(sortConfig.key);
        if (mainFieldMap[refType]) aValue = aValue[mainFieldMap[refType].key] || "";
      }
      if (typeof bValue === "object" && bValue !== null) {
        const refType = colToEntity(sortConfig.key);
        if (mainFieldMap[refType]) bValue = bValue[mainFieldMap[refType].key] || "";
      }
      if (typeof aValue === "string") aValue = aValue.toLowerCase();
      if (typeof bValue === "string") bValue = bValue.toLowerCase();
      if (aValue < bValue) return sortConfig.direction === "asc" ? -1 : 1;
      if (aValue > bValue) return sortConfig.direction === "asc" ? 1 : -1;
      return 0;
    });
  }

  function colToEntity(col) {
    if (mainFieldMap[col]) return col;
    if (col.endsWith("s") && mainFieldMap[col.slice(0, -1)]) return col.slice(0, -1);
    return col;
  }

  const getArrow = (col) => {
    if (sortConfig.key !== col) return "↕";
    return sortConfig.direction === "asc" ? "↑" : "↓";
  };

  function handleRowClick(row) {
    // Navigation sur toute la ligne pour les entités principales
    if ((selected === "users" || selected === "computers" || selected === "groups") && row.id) {
        navigate(`/${selected.slice(0, -1)}/${row.id}`);
    }
  }

  return (
    <div style={{ overflowX: "auto" }}>
      <table style={{ borderCollapse: "collapse", width: "100%" }}>
        <thead>
          <tr>
            {columns.map(col => (
              <th
                key={col}
                style={{ border: "1px solid #ccc", padding: "0.5rem", background: "#f0f0f0", cursor: "pointer" }}
                onClick={() =>
                  setSortConfig(prev => ({
                    key: col,
                    direction: prev.key === col && prev.direction === "asc" ? "desc" : "asc",
                  }))
                }
              >
                {col} {getArrow(col)}
              </th>
            ))}
          </tr>
        </thead>
        <tbody>
          {sortedData.map((row, idx) => (
            <tr
              key={idx}
              style={
                (selected === "users" || selected === "computers" || selected === "groups") && row.id
                  ? { cursor: "pointer", background: "#f9f9f9" }
                  : { background: "#f9f9f9" }
              }
              onClick={() => handleRowClick(row)}
            >
              {columns.map(col => {
                const value = row[col];
                // Si c'est un objet, afficher son champ principal si connu, sinon JSON brut
                if (typeof value === "object" && value !== null) {
                  const refType = colToEntity(col);
                  if (mainFieldMap[refType]) {
                    const display = value[mainFieldMap[refType].key];
                    const route = mainFieldMap[refType].route;
                    return (
                      <td
                        key={col}
                        style={{
                          border: "1px solid #ccc",
                          padding: "0.5rem",
                          cursor: value.id ? "pointer" : "default",
                          color: value.id ? "#1976d2" : undefined
                        }}
                        onClick={value.id ? (e) => {
                          e.stopPropagation();
                          navigate(`/${route}/${value.id}`);
                        } : undefined}
                      >
                        {display}
                      </td>
                    );
                  }
                  return (
                    <td key={col} style={{ border: "1px solid #ccc", padding: "0.5rem" }}>
                      {JSON.stringify(value)}
                    </td>
                  );
                }
                return (
                  <td key={col} style={{ border: "1px solid #ccc", padding: "0.5rem" }}>
                    {String(value)}
                  </td>
                );
              })}
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default RenderTable;