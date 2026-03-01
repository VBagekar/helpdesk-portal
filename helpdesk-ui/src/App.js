import Login from "./Login";
import Dashboard from "./Dashboard";

function App() {

  const token = localStorage.getItem("token");

  // handle null, undefined, or empty token
  if (!token || token === "undefined" || token === "null") {
    localStorage.removeItem("token");
    return <Login />;
  }

  return <Dashboard />;
}

export default App;