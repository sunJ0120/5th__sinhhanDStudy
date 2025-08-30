import styled from "styled-components";
import { Link } from "react-router-dom";

const FooterWrapper = styled.footer`
  display: flex;
  justify-content: center;
  gap: 10px;
  width: 100%;
  padding: 16px 0;
  margin-top: 20px;
  border-top: 1px solid #e2e2e2;
`;

function Footer() {
  return (
    <>
      <FooterWrapper>
        <Link to={"/"}>Home</Link>
        <Link to={"/new"}>New</Link>
        <Link to={"/diary"}>Diary</Link>
        <Link to={"/edit"}>Edit</Link>
      </FooterWrapper>
    </>
  );
}

export default Footer;
