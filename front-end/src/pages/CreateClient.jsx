import { useState } from "react";
import { useNavigate } from "react-router-dom";
import Form from "../components/Form";

function CreateClient(props) {
  let navigate = useNavigate();
  const [newClient, setNewClient] = useState({
    name: "",
    email: "",
    dob: "",
  });

  function submitHandler(event) {
    event.preventDefault();
    fetch("/api/v1/client", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        // 'Content-Type': 'application/x-www-form-urlencoded',
      },
      body: JSON.stringify(newClient),
    })
      .then((res) => res.json)
      .then((data) => {
        console.log(data);
        navigate("/");
      });
  }

  function inputHandler(event) {
    setNewClient((previousState) => ({
      ...previousState,
      [event.target.name]: event.target.value,
    }));
  }

  return (
    <div className="createClient">
      <h1>Create Client</h1>

      <Form
        newClient={newClient}
        submitHandler={submitHandler}
        inputHandler={inputHandler}
      />
    </div>
  );
}

export default CreateClient;
