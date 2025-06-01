import { useState } from "react";

function CreateComputerForm({ baseUrl, onComputerCreated }) {
  const [hostname, setHostname] = useState("");
  const [ip, setIp] = useState("");
  const [maxRam, setMaxRam] = useState("");
  const [maxCpu, setMaxCpu] = useState("");
  const [maxStorage, setMaxStorage] = useState("");
  const [error, setError] = useState("");

  const handleCreateComputer = (e) => {
    e.preventDefault();
    setError("");
    fetch(baseUrl + "computer", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        hostname,
        ip,
        maxRam: parseInt(maxRam, 10),
        maxCpu: parseInt(maxCpu, 10),
        maxStorage: parseInt(maxStorage, 10)
      })
    })
      .then(res => {
        if (!res.ok) throw new Error("Error while creating computer");
        return res.json();
      })
      .then((newComputer) => {
        if (onComputerCreated) onComputerCreated(newComputer);
        setHostname("");
        setIp("");
        setMaxRam("");
        setMaxCpu("");
        setMaxStorage("");
      })
      .catch(() => setError("Error while creating computer"));
  };

  return (
    <div style={{ padding: "1rem", maxWidth: "400px" }}>
      <h2>Create a new computer</h2>
      <form onSubmit={handleCreateComputer}>
        <input
          type="text"
          placeholder="Hostname"
          value={hostname}
          onChange={e => setHostname(e.target.value)}
          required
          style={{ display: "block", width: "100%", marginBottom: "1rem" }}
        />
        <input
          type="text"
          placeholder="IP address"
          value={ip}
          onChange={e => setIp(e.target.value)}
          required
          style={{ display: "block", width: "100%", marginBottom: "1rem" }}
        />
        <input
          type="number"
          placeholder="Max RAM (MB)"
          value={maxRam}
          onChange={e => setMaxRam(e.target.value)}
          required
          min={1}
          style={{ display: "block", width: "100%", marginBottom: "1rem" }}
        />
        <input
          type="number"
          placeholder="Max CPU (cores)"
          value={maxCpu}
          onChange={e => setMaxCpu(e.target.value)}
          required
          min={1}
          style={{ display: "block", width: "100%", marginBottom: "1rem" }}
        />
        <input
          type="number"
          placeholder="Max Storage (GB)"
          value={maxStorage}
          onChange={e => setMaxStorage(e.target.value)}
          required
          min={1}
          style={{ display: "block", width: "100%", marginBottom: "1rem" }}
        />
        <button type="submit">Create</button>
        {error && <div style={{ color: "red", marginTop: "1rem" }}>{error}</div>}
      </form>
    </div>
  );
}

export default CreateComputerForm;