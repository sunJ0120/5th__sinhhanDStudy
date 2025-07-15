/**
 * 스타일은 유지하고, 태그 속성을 바꾸는 연습을 해보자!
 */

import styled from "styled-components";

const Button = styled.button`
  color: #bf4f74;
  font-size: 1em;
  margin: 1em;
  padding: 0.25em 1em;
  border: 2px solid #bf4f74;
  border-radius: 3px;
`;

const TomatoButton = styled(Button)`
  color: tomato;
  border-color: tomato;
`;

function Test3() {
  return (
    <>
      <Button>Normal Button</Button>
      <TomatoButton>TomatoButton</TomatoButton>
    </>
  );
}

export default Test3;
