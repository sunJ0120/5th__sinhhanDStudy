import styled from "styled-components";

// <body>는 HTML 문서에서 오직 하나만 존재해야 하는 최상위 루트 태그라서, 중첩된 <body> 태그는 유효하지 않은(Invalid) 구조가 된다.
const BodyWrapper = styled.div`
  display: flex;
  justify-content: flex-start;
`;

function Body({ children }) {
  return (
    <>
      <BodyWrapper>{children}</BodyWrapper>
    </>
  );
}

export default Body;
