import React, { useReducer } from "react";

const Counter2 = () => {
  function reducer(number, action) {
    if (action.type === "INCREASE") {
      return { value: number.value + 1 };
    } else if (action.type === "DECREASE") {
      return { value: number.value - 1 };
    } else {
      return number;
    }
  }

  // value 초기 값을 0으로 준다.
  // 구조분해의 첫번째 요소는, number라는 변수에 들어간다.
  // 근데 이해가 안가는데, number는 또 뭐임
  // number에는 결국 { value: 0 } 초기값이 들어오고, 결국 dispatch({ type: "INCREASE" }) 이거는 action에 해당한다.
  // reducer(number, action)에 명시되어 있는거 보면 알 수 있음.
  const [number, dispatch] = useReducer(reducer, { value: 0 });

  // dispatch에 type을 줄 수 있다.
  // 버튼을 누르면, dispatch실행 -> 그 안에 action이 잇음

  return (
    <>
      <div>
        {number.value}
        <button onClick={() => dispatch({ type: "INCREASE" })}>+1</button>
        <button onClick={() => dispatch({ type: "DECREASE" })}>-1</button>
      </div>
    </>
  );
};

export default Counter2;
