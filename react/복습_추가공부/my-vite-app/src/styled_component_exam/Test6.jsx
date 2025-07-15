import styled from "styled-components";

const Input = styled.input`
  padding: 0.5em;
  margin: 0.5em;
  color: ${(props) => props.$inputColor || "#BF4F74"};
  background: papayawhip;
  border: none;
  border-radius: 3px;
`;

function Test6() {
  return (
    <>
      {/* 표준 색상 */}
      <Input defaultValue="@probablyup" type="text" />
      {/* 커스텀 색상 */}
      <Input defaultValue="@geelen" type="text" $inputColor="rebeccapurple" />
    </>
  );
}

export default Test6;
