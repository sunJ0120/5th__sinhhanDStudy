import styled from "styled-components"; //styled-components import

//title 하나 생성해보기
const Title = styled.h1`
  font-size: 1.5em;
  text-align: center;
  color: #bf4f74;
`;

function Test() {
  return (
    <>
      <Title>Hello world!</Title>
    </>
  );
}

export default Test;
