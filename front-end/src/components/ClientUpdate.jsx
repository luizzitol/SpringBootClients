import { useState } from "react";
import Form from "./Form";

function ClientUpdate(props) {
  const [newClient, setNewClient] = useState({
    name: "",
    email: "",
    dob: "",
  });

  return (
    <Form
      newClient={newClient}
      submitHandler={submitHandler}
      inputHandler={inputHandler}
    />
  );
}

export default ClientUpdate;
