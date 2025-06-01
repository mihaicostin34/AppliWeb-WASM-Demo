function RequireLogin({ children }) {
  const isLoggedIn = localStorage.getItem("isLoggedIn") === "true";
  if (!isLoggedIn) {
    return (
      <div style={{ color: "red", textAlign: "center", marginTop: "2rem" }}>
        Please log in to access this page.
      </div>
    );
  }
  return children;
}

export default RequireLogin;