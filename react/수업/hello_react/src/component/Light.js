// 여기서 toggle을 이렇게 {} 로 비구조화 해야 Object가 아니라 순수 그 형태대로 function으로 받는다.

function Light({ toggle }) {
  console.log("빛이나용 반짝반짝");
  console.log("자식 렌더링~~~");

  return (
    <>
      <button onClick={toggle}>자식에서 토글</button>
    </>
  );
}

export default Light;
