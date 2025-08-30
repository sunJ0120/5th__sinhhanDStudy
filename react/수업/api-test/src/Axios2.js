/**
 * get 방식으로 불러오는 실습
 */

import axios from "axios";
import { useEffect, useState } from "react";

function Axios1() {
  const [data, setData] = useState(null);

  //변수 방식으로 쓸 경우
  const getApi = async () => {
    const res = await axios.get("https://jsonplaceholder.typicode.com/posts");
    console.log(res);

    setData(res.data.list);
  };

  //mount 될 때 한번만 api를 호출하기 위함이다.
  useEffect(() => {
    getApi();
  }, []);

  return (
    <>
      <div>{data && data.map((e, i) => <li key={i}>{e.name}</li>)}</div>
    </>
  );
}

export default Axios1;
