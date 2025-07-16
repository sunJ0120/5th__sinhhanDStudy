/**
 * useContext hooks를 이용해서 변수 할당을 깔끔하게 해보자~
 * .Context라는 것 보다는 useContext가 더 사용하기 쉽게 해두었다.
 */

import Context1 from "./Context1";
import { useContext } from "react";

// .Consumer 이건 대체 뭐지....?
const ContextConsumer3 = () => {
  //이걸 이용해서 구조분해 햘당 변수를 더 손쉽게 받아올 수 있다.
  const { name } = useContext(Context1);
  return (
    <>
      <div>{name}</div>
    </>
  );
};

export default ContextConsumer3;
