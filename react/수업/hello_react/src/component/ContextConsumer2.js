/**
 * 값이 동적으로 바뀌게끔 해보자.
 */

import Context1 from "./Context1";

// .Consumer 이건 대체 뭐지....?
const ContextConsumer2 = () => {
  return (
    <>
      <Context1.Consumer>{(obj) => <div>{obj.name}</div>}</Context1.Consumer>
    </>
  );
};

export default ContextConsumer2;
