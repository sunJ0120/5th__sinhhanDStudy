import styled from "styled-components";

const Title = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  /* border: 1px solid #99a99b; */
  background-color: #f5f3ea;
  height: 48px;
  box-sizing: border-box;

  color: black; //text 표현을 위한 임시
`;

const TitleLeft = styled.div`
  font-size: 20px;
  font-weight: bold;
`;

//OrderTitle 이라는 상위 title 요소를 만들고, titleLeft / titleRight 라는 것을 만든다.
function OrderTitle({ titleLeft, titleRight }) {
  return (
    <>
      <Title>
        <TitleLeft>{titleLeft}</TitleLeft>
        {titleRight}
      </Title>
    </>
  );
}

export default OrderTitle;
