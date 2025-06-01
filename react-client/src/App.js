import { BrowserRouter, Routes, Route } from "react-router-dom";
import Manage from './pages/Manage';
import Stats from './pages/Stats';
import Menu from './components/Menu';
import Home from './pages/Home';
import UserDetails from "./pages/stats_navigation/UserDetails";
import GroupDetails from "./pages/stats_navigation/GroupDetails";
import ComputerDetails from "./pages/stats_navigation/ComputerDetails";

function App() {
  return (
    <BrowserRouter>
      <Menu />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/stats" element={<Stats />} />
        <Route path="/manage" element={<Manage />} />
        <Route path="/user/:id" element={<UserDetails />} />
        <Route path="/group/:id" element={<GroupDetails />} />
        <Route path="/computer/:id" element={<ComputerDetails />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;