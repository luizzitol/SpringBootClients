function Form(props) {
  return (
    <form onSubmit={props.submitHandler}>
      <div className="control">
        <label htmlFor="name">Name: </label>
        <input
          type="text"
          value={props.newClient.name}
          name="name"
          onChange={props.inputHandler}
          required
        />
      </div>
      <div className="control">
        <label htmlFor="email">Email: </label>
        <input
          type="email"
          value={props.newClient.email}
          name="email"
          onChange={props.inputHandler}
          required
        />
      </div>
      <div className="control">
        <label htmlFor="dob">DOB: </label>
        <input
          type="date"
          value={props.newClient.dob}
          name="dob"
          onChange={props.inputHandler}
          required
        />
      </div>
      <div className="button">
        <button type="submit">Submit</button>
      </div>
    </form>
  );
}

export default Form;
