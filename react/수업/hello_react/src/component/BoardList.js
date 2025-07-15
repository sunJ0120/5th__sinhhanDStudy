import React from "react";

//지금 이 컴포넌트 객체 보니까, 각 하나의 구조 정의한 것 같음.

// 비구조화 객체
const BoardList = ({ board, toggleChecked }) => {
  //지금 그니까 board 안에 이것들이 들어있다는 의미인가?
  const { no, title, checked } = board;
  return (
    <>
      <div>
        <input
          type="checkbox"
          checked={checked ? "checked" : ""}
          onChange={() => toggleChecked(no)}
        />
        {no} {title}
      </div>
    </>
  );
};

export default React.memo(BoardList); //1. 성능개선 1번_React.memo로 component 메모이제이션
// export default BoardList; //성능개선 없는 단계2) React.memo 제거 → 매 렌더링마다 새로운 함수 생성
