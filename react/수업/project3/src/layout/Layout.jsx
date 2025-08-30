import Body from "../layout/Body";
import Footer from "../layout/Footer";
import styled from "styled-components";

const LayoutWrapper = styled.div`
  width: 600px;
  padding: 20px;
  display: flex;
  margin: 0 auto;
  flex-direction: column;
  /* 가로 가운데 정렬 */
  align-items: center;
  /* 세로(메인축) 정렬 */
  justify-content: flex-start;
`;

function Layout({ header, children }) {
  return (
    <>
      <LayoutWrapper>
        {header}
        <Body>{children}</Body>
        <Footer />
      </LayoutWrapper>
    </>
  );
}

export default Layout;
