import { Link } from "react-router-dom";

function Layout(props) {
  return (
    <>
      <header>
        <div className="logo">Clients</div>
        <ul>
          <li>
            <Link to="/"> Clients </Link>
          </li>
          <li>
            <Link to="/add-client"> Add Client </Link>
          </li>
        </ul>
      </header>

      <main>{props.children}</main>
    </>
  );
}

export default Layout;
