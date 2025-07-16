import ContextConsumer from "./component/ContextConsumer";
import ContextConsumer2 from "./component/ContextConsumer2";
import ContextConsumer3 from "./component/ContextConsumer3";

import Context1 from "./component/Context1";
import { useState } from "react";

function App5() {
  //아 이제 대강 이해가 간다.
  //그러니까 useState를 정의할 때 setValue를 return 값으로 두고
  //setValue를 통해서 name 변수를(value) 바꿔주는 것이다.
  const [value, setValue] = useState({ name: "이순신" });

  return (
    <>
      <Context1.Provider value={value}>
        <ContextConsumer />
        <ContextConsumer2 />
        <ContextConsumer3 />
      </Context1.Provider>
      <button onClick={() => setValue({ name: "홍길동" })}>홍길동</button>
      <button onClick={() => setValue({ name: "김유신" })}>김유신</button>
    </>
  );
}

export default App5;
