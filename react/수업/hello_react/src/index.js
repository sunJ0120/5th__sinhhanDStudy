import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import App6 from "./App6"; //App6를 rendering 하도록 설정한다.
import reportWebVitals from "./reportWebVitals";
//BrowserRouter를 import 한다.
import { BrowserRouter } from "react-router-dom";

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  // <React.StrictMode>
  //   <App5 />
  // </React.StrictMode>
  <BrowserRouter>
    <App6 />
  </BrowserRouter>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
