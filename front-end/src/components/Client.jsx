import { useState } from "react";
import { resolvePath } from "react-router-dom";
import Form from "./Form";

function Client(props) {
  const [newClient, setNewClient] = useState({
    name: props.name,
    email: props.email,
    dob: props.dob,
  });

  function inputHandler(event) {
    setNewClient((previousState) => ({
      ...previousState,
      [event.target.name]: event.target.value,
    }));
  }

  function submitHandler(event) {
    event.preventDefault();
    fetch("/api/v1/client/" + props.id, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(newClient),
    })
      .then((res) => res.json)
      .then((data) => {
        console.log(data);
        setUpdating(false);
        props.getClients();
      });
  }

  function deleteHandler() {
    fetch("/api/v1/client/" + props.id, {
      method: "DELETE",
    })
      .then((res) => {
        console.log(res);
        return res.json();
      })
      .then((data) => {
        console.log(data);
        props.getClients();
      });
  }

  let [updating, setUpdating] = useState(false);

  let client = (
    <div className="card">
      <h3>Name: {props.name}</h3>
      <p>Email: {props.email}</p>
      <p>Age: {props.age}</p>
      <button onClick={deleteHandler}>Delete</button>
      <button onClick={() => setUpdating(true)}>Update</button>
    </div>
  );

  let form = (
    <Form
      newClient={newClient}
      inputHandler={inputHandler}
      submitHandler={submitHandler}
    />
  );

  return <li>{updating ? form : client}</li>;
}

export default Client;
