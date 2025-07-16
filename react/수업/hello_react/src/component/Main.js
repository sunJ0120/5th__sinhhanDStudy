import { Link } from "react-router-dom";

// 이상하게 a태그로 했을때는 링크가 먼저 바뀌네...

function Main() {
  return (
    <>
      <div>
        <h1>Main</h1>
        <a href="intro">소개</a>
        <Link to="/intro">소개</Link>
      </div>
    </>
  );
}

export default Main;
