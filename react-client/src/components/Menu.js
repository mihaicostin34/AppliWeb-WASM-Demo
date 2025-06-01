import { useNavigate } from "react-router-dom";
import LoginForm from "./Login";

function Menu() {
  const navigate = useNavigate();

  return (
    <div
      style={{
        padding: "1rem",
        borderBottom: "1px solid #ccc",
        marginBottom: "2rem",
        background: "#f8f8f8",
        display: "flex",
        alignItems: "center",
        justifyContent: "space-between"
      }}
    >
      <div>
        <button
          style={{ marginRight: "1rem", padding: "0.5rem 1rem", cursor: "pointer" }}
          onClick={() => navigate("/")}
        >
          Home
        </button>
        <button
          style={{ marginRight: "1rem", padding: "0.5rem 1rem", cursor: "pointer" }}
          onClick={() => navigate("/stats")}
        >
          Stats
        </button>
        <button
          style={{ padding: "0.5rem 1rem", cursor: "pointer" }}
          onClick={() => navigate("/manage")}
        >
          Manage
        </button>
      </div>
      <div>
        <LoginForm />
      </div>
    </div>
  );
}

export default Menu;