/**
 * as를 이용해서, style은 유지하고 태그만 바꾸는 실습
 */

import styled from "styled-components";

const Button = styled.button`
  color: #bf4f74;
  font-size: 1em;
  margin: 1em;
  padding: 0.25em 1em;
  border: 2px solid #bf4f74;
  border-radius: 3px;
  display: block;
`;

const TomatoButton = styled(Button)`
  color: tomato;
  border-color: tomato;
`;

function Test4() {
  return (
    <>
      <Button>Nomal Button</Button>
      <Button as="a" href="#">
        Link with Button styles
      </Button>
      <TomatoButton as="a" href="#">
        Link with Tomato Button styles
      </TomatoButton>
    </>
  );
}

export default Test4;
