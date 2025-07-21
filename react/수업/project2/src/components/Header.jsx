import styled from "styled-components"; //styled-components import

const Wrapper = styled.div`
  display: flex;
  gap: 10px;
  flex-direction: column;
  align-items: flex-start;
`;
const HeaderMonth = styled.h1`
  margin: 0;
  color: cadetblue;
`;

function Header() {
  return (
    <>
      <Wrapper>
        <h3>오늘의 날짜는요~ 🗓️</h3>
        <HeaderMonth>{new Date().toDateString()}</HeaderMonth>
      </Wrapper>
    </>
  );
}

export default React.memo(Header); //memo로 감싸서 최적화한다.
