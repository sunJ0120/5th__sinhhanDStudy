import styled from "styled-components";
import Header from "./Header";
import Footer from "./Footer";

const Body = styled.div`
  margin: 0;
  padding: 0;
  height: 956px;
  display: flex;
  justify-content: center;
`;

const Wrapper = styled.div`
  width: 440px;
  height: 956px;
  background-color: #f5f3ea;
  display: flex;
  flex-direction: column;
`;

const Main = styled.main`
  flex: 1;
  min-height: 0;
  padding: 20pt;
  display: flex;
  flex-direction: column;
`;

//{children}을 통해서 다른 페이지들의 다른 요소들을 넣을 수 있다.
//titleLeft, titleRight 로 분리해서 왼쪽에는 주문내역~ 오른쪽에는 토글이 위치할 수 있도록 한다.
function Layout({ children }) {
  return (
    <>
      <Body>
        <Wrapper>
          <Header />
          <Main>{children}</Main>
          <Footer />
        </Wrapper>
      </Body>
    </>
  );
}

export default Layout;
