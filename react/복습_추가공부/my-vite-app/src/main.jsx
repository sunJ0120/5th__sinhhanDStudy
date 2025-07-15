import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import "./index.css";
// import App from './App.jsx'
import Test6 from "./styled_component_exam/Test6";

createRoot(document.getElementById("root")).render(
  <StrictMode>
    {/* <App /> */}
    {/* <Test2 /> */}
    <Test6 />
  </StrictMode>
);
