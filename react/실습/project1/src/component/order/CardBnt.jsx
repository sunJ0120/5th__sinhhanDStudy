import styled from "styled-components";

//일단 버튼 기본으로 해두고, styled-component 이용해서 상태 바꾸는 것 까지 해본다.

const ListBtn = styled.div`
  color: #f5f3ea;
  background-color: #8b6d5c;
  border-radius: 10pt;

  height: 29pt;
  border: none;
  width: 100%;

  /*float: right;*/
  align-content: center;
  justify-content: center;
  text-align: center;

  font-size: 15px;
  margin-top: 12px;
  display: inline-block;
  cursor: pointer;
`;

function CardBnt() {
  return (
    <>
      <ListBtn>이건 버튼</ListBtn>
    </>
  );
}

export default CardBnt;
