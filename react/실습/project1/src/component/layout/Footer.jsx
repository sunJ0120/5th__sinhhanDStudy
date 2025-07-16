import styled from "styled-components";

const FooterWrapper = styled.footer`
  height: 120px;
  border-top: solid 1px #717171;
  margin-top: auto;
  display: flex;
  justify-content: center;
  align-items: center;

  color: black; //text 표현을 위한 임시
`;

const BottomNavigationWrapper = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-evenly;
  width: 390px;
  height: 74px;
  background-color: #99a99b;
  border-radius: 10px;
  margin: 20pt;

  color: black; //text 표현을 위한 임시
`;

//-------BottomNavigationWrapper------ 안에 들어가는 요소

const MenuItem = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  height: 55px;
  width: 68px;
  margin: 10px;

  border-radius: 8px;
  cursor: pointer;

  //원래 active 요소인데, 일단은 여기 넣기
  background-color: #f5f3ea;
`;

function Footer() {
  return (
    <>
      <FooterWrapper>
        <BottomNavigationWrapper>
          <MenuItem>메뉴 1</MenuItem>
          <MenuItem>메뉴 2</MenuItem>
          <MenuItem>메뉴 3</MenuItem>
          <MenuItem>메뉴 4</MenuItem>
        </BottomNavigationWrapper>
      </FooterWrapper>
    </>
  );
}

export default Footer;
