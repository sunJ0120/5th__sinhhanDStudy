import React, { useState } from "react";

const Counter = (props) => {
  const [number, setNumber] = useState(0); //useState안에는 index, set함수를 정의한다.
  const increase = () => {
    setNumber(number + 1);
  };
  const decrease = () => {
    setNumber(number - 1);
  };

  const updateProps = () => {
    props.setName("피용히");
  };

  return (
    <>
      <div>
        <button onClick={increase}>증가</button>
        <button onClick={decrease}>감소</button>
      </div>
      <div>{number}</div>
      {/* <button onClick={updateProps}>props 변경</button> */}
      <button onClick={() => props.setName("김길똥")}>props 변경</button>
      <div>{props.name}</div>
    </>
  );
};

export default Counter;
