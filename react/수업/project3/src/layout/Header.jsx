import styled from "styled-components";

const HeaderWrapper = styled.div`
  width: 100%;
  padding-top: 20px;
  padding-bottom: 20px;

  display: flex;
  align-items: center;
  border-bottom: 1px solid #e2e2e2;

  /* HeaderWrapper의 직계 자식 div 모두에 display:flex 적용 */
  & > div {
    display: flex;
  }
`;

const HeaderLeftChild = styled.div`
  flex: 1;
  justify-content: start;
`;

const HeaderRightChild = styled.div`
  flex: 1;
  justify-content: end;
`;

const HeaderTitle = styled.div`
  flex: 2;
  justify-content: center;
`;

function Header({ title, leftChild, rightChild }) {
  return (
    <>
      <HeaderWrapper>
        <HeaderLeftChild>{leftChild}</HeaderLeftChild>

        <HeaderTitle>{title}</HeaderTitle>

        <HeaderRightChild>{rightChild}</HeaderRightChild>
      </HeaderWrapper>
    </>
  );
}

export default Header;
