import React, { useState, useCallback } from "react";
import Light from "./Light";

function UseCallbackTest() {
  //부모 렌더링
  console.log("부모 렌더링~~~~~~~~~~");
  const [light, setLight] = useState(false);

  const toggle = useCallback(() => {
    setLight(!light);
  });

  return (
    <>
      <div>{light ? "on" : "off"}</div>
      <div>
        <button onClick={toggle}>부모에서 토글</button>
      </div>
      <div>
        <Light toggle={toggle} />
      </div>
    </>
  );
}

export default UseCallbackTest;
