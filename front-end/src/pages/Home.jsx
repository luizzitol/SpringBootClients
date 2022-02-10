import { useEffect, useState } from "react";
import ClientList from "../components/ClientList";

function Home(props) {
  const [clients, setClients] = useState([]);

  useEffect(() => {
    getClients();
  }, []);

  useEffect(() => {
    console.log(clients);
  }, [clients]);

  function getClients() {
    console.log("fetching clients");
    fetch("/api/v1/client")
      .then((res) => {
        console.log(res);
        return res.json();
      })
      .then((data) => {
        console.log(data);
        setClients(data);
      });
  }

  return <ClientList clients={clients} getClients={getClients} />;
}

export default Home;
