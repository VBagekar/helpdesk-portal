import { useState } from "react";

function Login() {

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = async () => {

    const res = await fetch("http://localhost:8080/api/auth/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        email: email,
        password: password
      })
    });

    if (!res.ok) {
      alert("Login failed");
      return;
    }

    // IMPORTANT: backend returns TEXT, not JSON
    const token = await res.text();

    // SAVE JWT
    localStorage.setItem("token", token);

    // refresh app
    window.location.reload();
  };

  return (
    <div>
      <h2>Helpdesk Login</h2>

      <input
        placeholder="Email"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
      />

      <br/>

      <input
        type="password"
        placeholder="Password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
      />

      <br/>

      <button onClick={handleLogin}>Login</button>
    </div>
  );
}

export default Login;