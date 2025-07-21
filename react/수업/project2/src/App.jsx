import "./App.css";
import styled from "styled-components"; //styled-components import
import Header from "./components/Header";
import TodoEditor from "./components/TodoEditor";
import TodoList from "./components/TodoList";
import { useRef, useState } from "react";

//임시 데이터인 mock dataf를 만든다.
const mockTodo = [
  {
    id: 0,
    isDone: false,
    content: "아뜨뜨 솥밥집 가기",
    createDate: new Date(Date.now()).toLocaleDateString(),
  },
  {
    id: 1,
    isDone: false,
    content: "갈릭버터 소금빵 사먹기",
    createDate: new Date(Date.now()).toLocaleDateString(),
  },
  {
    id: 2,
    isDone: false,
    content: "JPA 8강 공부 끝내기",
    createDate: new Date(Date.now()).toLocaleDateString(),
  },
];

const AppMain = styled.div`
  display: flex;
  flex-direction: column;
  gap: 30px;
  max-width: 500px;
  width: 100%;
  margin: 0 auto;
  box-sizing: border-box;
  padding: 20px;
  border: 1px solid gray;
  align-items: flex-start;
`;

// 함수를 todoEditor한테 넘겨준다. ->
function App() {
  const [todo, setTodo] = useState(mockTodo);
  const idRef = useRef(3);

  //"C"RUD
  const onCreate = (content) => {
    const newItem = {
      id: idRef.current,
      content,
      isDone: false,
      createDate: new Date(Date.now()).toLocaleDateString(),
    };
    setTodo([newItem, ...todo]); //newItem을 todo 배열 아래 넣는다.
    idRef.current += 1;
  };

  //CR"U"D
  const onUpdate = (targetId) => {
    setTodo(
      todo.map((it) => {
        //클릭한 체크박스에 해당하는 아이디일 경우
        if (it.id === targetId) {
          //같은 것일 경우, 그것에 해당하는 것만 상태 변경하고 나머지 전부 return
          return {
            ...it,
            isDone: !it.isDone,
          };
        } else {
          return it;
        }
      })
    );
  };

  //CRU"D"
  const onDelete = (targetId) => {
    setTodo(todo.filter((it) => it.id !== targetId));
  };

  return (
    <>
      <AppMain>
        <Header />
        <TodoEditor onCreate={onCreate} />
        <TodoList todo={todo} onUpdate={onUpdate} onDelete={onDelete} />
      </AppMain>
    </>
  );
}

export default App;
