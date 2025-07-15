/**
 * 커스텀 component에 styled을 적용해보자.
 */

import styled from "styled-components";

//다음과 같이 props를 정의한다.
const Link = ({ className, children }) => (
  <a className={className}>{children}</a>
);

const StyledLink = styled(Link)`
  color: #bf4f74;
  font-weight: bold;
`;

// 구조를 보면, Link 이게 className, Unstyled, boring Link 이게 child가 되는 것이다.
function Test5() {
  return (
    <>
      <Link>Unstyled, boring Link</Link>
      <br />
      <StyledLink>Styled, existing Link</StyledLink>
    </>
  );
}

export default Test5;
