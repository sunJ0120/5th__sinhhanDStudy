import Greet from "./component/Greet";

function App2() {
  let numbers = [1, 2, 3, 4];
  let Greets = [];
  return (
    <>
      {numbers.map((n, i) => {
        return (
          //중괄호를 쓴다면, return을 해서 ()로 묶어줘야 한다.
          <li key={i}>
            <Greet />
          </li>
        );
      })}
    </>
  );
}

export default App2;
