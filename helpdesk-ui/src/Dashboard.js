import { useEffect, useState } from "react";
import axios from "axios";

function Dashboard() {

    const [tickets, setTickets] = useState([]);

    const token = localStorage.getItem("token");

    // fetch my tickets
    const loadTickets = async () => {
        try {
            const response = await axios.get(
                "http://localhost:8080/api/tickets/my",
                {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                }
            );
            setTickets(response.data);
        } catch (error) {
            alert("Failed to load tickets");
        }
    };

    useEffect(() => {
        loadTickets();
    }, []);

    const logout = () => {
        localStorage.removeItem("token");
        window.location.reload();
    };

    return (
        <div style={{textAlign:"center"}}>
            <h2>My Tickets</h2>

            <button onClick={logout}>Logout</button>

            <br/><br/>

            {tickets.length === 0 ? (
                <p>No tickets yet</p>
            ) : (
                <table border="1" align="center">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Title</th>
                            <th>Status</th>
                            <th>Priority</th>
                        </tr>
                    </thead>
                    <tbody>
                        {tickets.map(ticket => (
                            <tr key={ticket.id}>
                                <td>{ticket.id}</td>
                                <td>{ticket.title}</td>
                                <td>{ticket.status}</td>
                                <td>{ticket.priority}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            )}
        </div>
    );
}

export default Dashboard;