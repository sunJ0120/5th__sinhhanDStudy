import React, { useState, useMemo } from "react";

const Hooks3 = () => {
  let [list, setList] = useState([]);
  let [number, setNumber] = useState("");

  const onChange = (e) => {
    setNumber(e.target.value);
  };

  const insert = (e) => {
    console.log(document.querySelector("#number").value);
    setList([...list, parseInt(number)]);
  };

  const sum = (list) => {
    console.log("합계 계산");
    let sum = 0;
    list.forEach((element) => {
      console.log("element 값 보기 : ", element);
      sum += element;
    });
    return sum;
  };

  //useMemo를 이용해서 list에 변동이 잇을때만 -> [list] 코드를 실행하도록 할 수 있다.
  const t = useMemo(() => sum(list), [list]);

  return (
    <>
      <input type="text" value={number} id="number" onChange={onChange} />
      <button onClick={insert}>등록</button>
      <ul>
        {list.map((v, idx) => (
          <li key={idx}>{v}</li>
        ))}
      </ul>
      <div>합계 : {t}</div>
    </>
  );
};

export default Hooks3;
