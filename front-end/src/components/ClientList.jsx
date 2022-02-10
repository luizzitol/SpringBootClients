import Client from "./Client";

function ClientList(props) {
  return (
    <ul className="clientList">
      {props.clients.map((client) => (
        <Client
          getClients={props.getClients}
          id={client.id}
          age={client.age}
          name={client.name}
          email={client.email}
          key={client.id}
        />
      ))}
    </ul>
  );
}

export default ClientList;
