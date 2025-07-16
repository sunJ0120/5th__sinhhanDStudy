import styled from "styled-components";

const HeaderWrapper = styled.header`
  height: 30px;
  padding: 20px 15px 20px 15px;
  border-bottom: solid 1px #717171;
  color: black; //text 표현을 위한 임시
`;

function Header() {
  return (
    <>
      <HeaderWrapper>여기는 header 영역입니당</HeaderWrapper>
    </>
  );
}

export default Header;
