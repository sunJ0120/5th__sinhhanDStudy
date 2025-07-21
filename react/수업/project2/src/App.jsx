import "./App.css";
import styled from "styled-components"; //styled-components import
import Header from "./components/Header";
import TodoEditor from "./components/TodoEditor";
import TodoList from "./components/TodoList";
import { useRef, useState, useReducer } from "react";

//임시 데이터인 mock data를 만든다.
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

//Reducer를 사용하기 위함이다.
//switch를 이용해서, action 안에 있는 타입이 어떤 타입인지를 확인한다.
function reducer(state, action) {
  switch (action.type) {
    case "CREATE": {
      return [action.newItem, ...state];
    }
    case "UPDATE": {
      return state.map((it) =>
        it.id === action.targetId
          ? {
              ...it,
              isDone: !it.isDone,
            }
          : it
      );
    }
    //즉, 삭제 버튼을 누르지 않은 targetId가 아닌 것만 내보낸다는 의미이다.
    case "DELETE": {
      return state.filter((it) => it.id !== action.targetId);
    }
    default:
      return state;
  }
}

// 함수를 todoEditor한테 넘겨준다. ->
function App() {
  const [todo, dispatch] = useReducer(reducer, mockTodo);
  // const [todo, setTodo] = useState(mockTodo);
  const idRef = useRef(3);

  //"C"RUD
  const onCreate = (content) => {
    dispatch({
      type: "CREATE",
      newItem: {
        id: idRef.current,
        content,
        isDone: false,
        createDate: new Date(Date.now()).toLocaleDateString(),
      },
    });
    idRef.current += 1;
  };

  //CR"U"D
  //dispatch로 targetId만 전달해서, reducer에서 처리한다.
  const onUpdate = (targetId) => {
    dispatch({
      type: "UPDATE",
      targetId,
    });
  };

  //CRU"D"
  const onDelete = (targetId) => {
    dispatch({
      type: "DELETE",
      targetId,
    });
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
