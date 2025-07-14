import logo from "./logo.svg";
import "./App.css";
import { Fragment } from "react/jsx-runtime";
import Greet from "./component/Greet";

function App() {
  const name = "React";
  const greet = true;

  return (
    <>
      <h1 className="a">Hello {name}</h1>
      <h2 style={{ backgroundColor: "black", color: "white" }}>
        {greet && <Greet />}
      </h2>
    </>
  );
}

export default App;
