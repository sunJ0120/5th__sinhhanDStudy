import React, { useState, useEffect } from "react";

function Hooks2() {
  let [today, setToDay] = useState(new Date());
  //useEffect의 첫번째는 콜백 함수, 두 번째는 list이다.
  //useEffect 자체는 빈 배열([]) 덕분에 컴포넌트 마운트 때 딱 한 번만 실행된다.
  //한 번 렌더링 된 값이 계속 찍힌다. 확인해보자.

  useEffect(() => {
    setInterval(() => {
      setToDay(new Date());
      console.log(today);
    }, 1000);
  }, []); //의존 배열이 빈 배열이면 처음 랜더링 됐을 때 한 번만 실행되는 것이다.

  return <>{today.toString()}</>;
}

export default Hooks2;
