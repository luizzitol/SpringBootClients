import { Route, Routes } from "react-router-dom";
import Layout from "./Layout/Layout";
import CreateClient from "./pages/CreateClient";
import Home from "./pages/Home";

function App() {
  return (
    <Layout>
      <Routes>
        <Route exact path="/" element={<Home />} />
        <Route exact path="/add-client" element={<CreateClient />} />
      </Routes>
    </Layout>
  );
}

export default App;
