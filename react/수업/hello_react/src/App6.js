import { Routes, Route } from "react-router-dom";
import Main from "./component/Main";

//다음과 같이 Main으로 이동할 수 있도록 route를 만들어 준다.

function App6() {
  return (
    <>
      <Routes>
        <Route path="/" element={<Main />} />
      </Routes>
    </>
  );
}

export default App6;
