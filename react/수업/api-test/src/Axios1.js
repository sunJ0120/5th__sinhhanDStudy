/**
 * get 방식으로 불러오는 실습
 */

import axios from "axios";
import { useState } from "react";

function Axios1() {
  const [data, setData] = useState(null);

  //   //function 방식으로 부를 경우
  //   function getApi() {
  //     axios.get("https://jsonplaceholder.typicode.com/posts").then((res) => {
  //       setData(res.data);
  //     });
  //   }

  //title만 출력해보자.
  function getApi() {
    axios.get("https://jsonplaceholder.typicode.com/posts").then((res) => {
      setData(res.data);
    });
  }

  //   //변수 방식으로 쓸 경우
  //   const getApi = async () => {
  //     const res = await axios.get("https://jsonplaceholder.typicode.com/posts");
  //     console.log(res);
  //   };

  return (
    <>
      <button onClick={getApi}>불러오기</button>
      {data && (
        <ul>
          {data.map((it) => (
            <li key={it.id}>{it.title}</li>
          ))}
        </ul>
      )}
    </>
  );
}

export default Axios1;
