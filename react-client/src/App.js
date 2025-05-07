import React, { useEffect, useState } from 'react';
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer } from 'recharts';

export default function DataFetcherChart() {
  const [rawData, setRawData] = useState([]);
  const [selectedHost, setSelectedHost] = useState('');
  const [selectedFeature, setSelectedFeature] = useState('cpuUsage');
  const [loading, setLoading] = useState(false);
  const [timestampsToShow, setTimestampsToShow] = useState('All');

  const fetchData = async () => {
    setLoading(true);
    try {
      const response = await fetch('http://localhost:8080/serveur/listeinfo'); // Replace with your API
      const json = await response.json();
      setRawData(prev => [...json]);
      if (!selectedHost && json.length > 0) {
        setSelectedHost(json[0].hostname);
      }
    } catch (error) {
      console.error('Failed to fetch data', error);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  const hostnames = [...new Set(rawData.map(item => item.hostname))];

  let filteredData = rawData
    .filter(item => item.hostname === selectedHost)
    .map(item => ({
      ...item,
      date: new Date(item.timestamp).toLocaleString(),
    }));

  if (timestampsToShow !== 'All') {
    const num = parseInt(timestampsToShow);
    filteredData = filteredData.slice(-num);
  }

  return (
    <div className="p-6 max-w-4xl mx-auto">
      <h2 className="text-2xl font-bold mb-4">Host Metrics Timeline</h2>
      <div className="flex gap-4 items-center mb-4 flex-wrap">
        <button onClick={fetchData} disabled={loading} className="bg-blue-500 text-white px-4 py-2 rounded">
          {loading ? 'Loading...' : 'Fetch More Data'}
        </button>

        <select
          value={selectedHost}
          onChange={e => setSelectedHost(e.target.value)}
          className="border p-2 rounded"
        >
          <option value="">Select Host</option>
          {hostnames.map((host, index) => (
            <option key={`${host}-${index}`} value={host}>
              {host}
            </option>
          ))}
        </select>

        <select
          value={selectedFeature}
          onChange={e => setSelectedFeature(e.target.value)}
          className="border p-2 rounded"
        >
          <option value="cpuUsage">CPU Usage</option>
          <option value="processes">Processes</option>
        </select>

        <select
          value={timestampsToShow}
          onChange={e => setTimestampsToShow(e.target.value)}
          className="border p-2 rounded"
        >
          <option value="All">All</option>
          <option value="10">Last 10</option>
          <option value="20">Last 20</option>
          <option value="50">Last 50</option>
        </select>
      </div>

      <div className="mt-6">
        <ResponsiveContainer width="100%" height={400}>
          <LineChart data={filteredData} margin={{ top: 5, right: 20, left: 10, bottom: 5 }}>
            <CartesianGrid strokeDasharray="3 3" />
            <XAxis dataKey="date" angle={-45} textAnchor="end" height={70} />
            <YAxis label={{ value: selectedFeature, angle: -90, position: 'insideLeft' }} />
            <Tooltip />
            <Legend />
            <Line type="monotone" dataKey={selectedFeature} stroke="#82ca9d" activeDot={{ r: 8 }} />
          </LineChart>
        </ResponsiveContainer>
      </div>
    </div>
  );
}
