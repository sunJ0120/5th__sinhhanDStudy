import styled from "styled-components"; //styled-components import

// styled_component를 이용한 h1 tag 스타일링
const Title = styled.h1`
  font-size: 1.5em;
  text-align: center;
  color: #bf4f74;
`;

// styled_component를 이용한 Wrapper tag 스타일링
const Wrapper = styled.section`
  padding: 4em;
  background: papayawhip;
`;

// 다음 styled_component로 정의한 TAG들을 React의 Component로 정의
// ③ Test1 컴포넌트에서 리턴
function Test1() {
  return (
    <Wrapper>
      <Title>Hello World!</Title>
    </Wrapper>
  );
}

export default Test1;
