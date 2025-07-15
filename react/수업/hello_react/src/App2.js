import { useState } from "react";
import Counter from "./component/Counter";
import Event3 from "./component/Event3";
import Event5 from "./component/Event5";
//name export가 아닌, default export 이므로 맞춰준다.

function App2() {
  //이렇게 초기값을 주는 것이다.
  const [name, setName] = useState("김김김");
  return (
    <>
      <Counter setName={setName} name={name} />
      <Event3 />
      <Event5 />
    </>
  );
}

export default App2;
