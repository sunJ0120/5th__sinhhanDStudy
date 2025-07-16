import { useState, useCallback } from "react";
import BoardList from "./BoardList";

//이렇게 해서 3000개의 book data를 만든다.
function createData() {
  const arr = [];
  //3000개 너무 오래걸림... 1000개로 할래
  for (let i = 0; i < 1000; i++) {
    arr.push({ no: i, title: "제목" + i, checked: false });
  }
  return arr;
}

const Board = () => {
  const [board, setBoard] = useState(createData);
  // 아 이게 보니까 no 같은거 check 하는 함수이다.

  // useCallback으로 함수 레퍼런스 고정 useCallback을 쓰면 함수 객체(identity)가 한 번만 만들어져서 고정되고, 덕분에 React.memo가 제대로 동작가능
  const toggleChecked = useCallback((no) => {
    // 데이터 & setter 함수 실행 이런식으로 구분을 잘 지을 수 있어야 한다.
    // 안에 있는 3000개의 객체마다 콜백 함수를 실행하는 것이다.
    // { ...b, checked: !b.checked } 이렇게 중괄호로 묶었다는 것은, 새로운 객체라는 것이다.
    // board.map이 새로운 객체를 만들어서 board2에 담긴 것이다.
    const board2 = (board) =>
      board.map((b) => (b.no === no ? { ...b, checked: !b.checked } : b));
    setBoard(board2);
  }, []);

  // // 성능개선 없는 단계1) useCallback 제거 → 매 렌더링마다 새로운 함수 생성
  // const toggleChecked = (no) => {
  //   setBoard((prev) =>
  //     prev.map((b) =>
  //       b.no === no ? { ...b, checked: !b.checked } : b
  //     )
  //   );
  // };

  //그 다음에 check 된 것을 바탕으로 boardList를 그린다.
  return (
    <>
      {board.map((e, i) => (
        <BoardList key={i} board={e} toggleChecked={toggleChecked} />
      ))}
    </>
  );
};

export default Board;
