import { useState } from "react";

function CreateTicket({ onCreated }) {

  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [priority, setPriority] = useState("LOW");

  const handleSubmit = async () => {

    const token = localStorage.getItem("token");

    const res = await fetch("http://localhost:8080/api/tickets", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer " + token
      },
      body: JSON.stringify({
        title,
        description,
        priority
      })
    });

    if (!res.ok) {
      alert("Failed to create ticket");
      return;
    }

    alert("Ticket created successfully");

    // clear form
    setTitle("");
    setDescription("");
    setPriority("LOW");

    // refresh dashboard
    onCreated();
  };

  return (
    <div style={{marginBottom:"20px"}}>
      <h3>Raise a Complaint</h3>

      <input
        placeholder="Title"
        value={title}
        onChange={(e)=>setTitle(e.target.value)}
      />
      <br/><br/>

      <textarea
        placeholder="Describe your issue"
        value={description}
        onChange={(e)=>setDescription(e.target.value)}
      />
      <br/><br/>

      <select value={priority} onChange={(e)=>setPriority(e.target.value)}>
        <option>LOW</option>
        <option>MEDIUM</option>
        <option>HIGH</option>
      </select>

      <br/><br/>

      <button onClick={handleSubmit}>Submit Ticket</button>
    </div>
  );
}

export default CreateTicket;