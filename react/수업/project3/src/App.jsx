import { Routes, Route } from "react-router-dom";
import "./App.css";

import Home from "./_pages/Home";
import New from "./_pages/New";
import Diary from "./_pages/Diary";
import Edit from "./_pages/Edit";
import React, { useEffect, useReducer, useRef, useState } from "react";

export const DiaryStateContext = React.createContext();
//일기 State를 업데이트 하는 함수 onCreate, onUpdate, onDelete를 컴포넌트 트리에 공급하는 Context를 생성한다.
export const DiaryDispatchContext = React.createContext();

//mock 데이터
const mockData = [
  {
    id: "mock1",
    date: new Date().getTime(),
    content: "mock1",
    emotionId: 1,
  },
  {
    id: "mock2",
    date: new Date().getTime(),
    content: "mock2",
    emotionId: 2,
  },
  {
    id: "mock3",
    date: new Date().getTime(),
    content: "mock3",
    emotionId: 3,
  },
];

function reducer(state, action) {
  switch (action.type) {
    case "INIT": {
      return action.data;
    }
    case "CREATE": {
      return [action.data, ...state];
    }
    case "UPDATE": {
      return state.map((it) =>
        String(it.id) === String(action.data.id) ? { ...action.data } : it
      );
    }
    case "DELETE": {
      return state.filter((it) => String(it.id) !== String(action.targetId));
    }
    default: {
      return state;
    }
  }
}

function App() {
  const [data, dispatch] = useReducer(reducer, []);
  const [isDataLoaded, setIsDataLoaded] = useState(false);
  const idRef = useRef(0); //배열 일기를 리스트로 렌더링 할 때 아이템별로 고유한 key를 부여하기 위함이다.

  useEffect(() => {
    dispatch({
      type: "INIT",
      data: mockData,
    });
    setIsDataLoaded(true); //데이터가 로드 되었다는 것을 true로 표현한다.
  }, []);

  const onCreate = (date, content, emotionId) => {
    dispatch({
      type: "CREATE",
      data: {
        id: idRef.current,
        date: new Date(date).getTime(),
        content,
        emotionId,
      },
    });
    idRef.current += 1; //하나 증가시킨다.
  };

  const onUpdate = (targetId, date, content, emotionId) => {
    dispatch({
      type: "UPDATE",
      data: {
        id: targetId, //update할 객체 id를 설정한다.
        date: new Date(date).getTime(),
        content,
        emotionId,
      },
    });
  };

  //delete의 경우, targetId만 있으면 된다.
  const onDelete = (targetId) => {
    dispatch({
      type: "DELETE",
      targetId,
    });
  };

  return (
    <>
      <DiaryStateContext.Provider value={data}>
        <DiaryDispatchContext.Provider
          value={{
            onCreate,
            onUpdate,
            onDelete,
          }}
        >
          {/* 라우터 설정 */}
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/new" element={<New />} />
            <Route path="/diary/:id" element={<Diary />} />
            <Route path="/edit/:id" element={<Edit />} />
          </Routes>
        </DiaryDispatchContext.Provider>
      </DiaryStateContext.Provider>
    </>
  );
}

export default App;
