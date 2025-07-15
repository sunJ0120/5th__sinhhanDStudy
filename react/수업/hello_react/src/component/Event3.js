function Event3() {
  const onClick = (e) => {
    const { isTrusted, target, bubbles } = e;
    console.log("클릭 이벤트 : ", isTrusted, target, bubbles);
  };

  return (
    <>
      <div>
        <div onClick={onClick}>클릭3</div>
      </div>
    </>
  );
}

export default Event3;
