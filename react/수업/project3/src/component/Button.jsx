import styled from "styled-components";

const ButtonStyle = styled.button`
  cursor: pointer;
  border: none;
  border-radius: 5px;
  padding-top: 10px;
  padding-bottom: 10px;
  padding-left: 20px;
  padding-right: 20px;
  font-size: 18px;
  white-space: nowrap;

  background-color: ${(props) =>
    props.$type === "positive"
      ? "#4caf50"
      : props.$type === "negative"
      ? "#f44336"
      : "#e0e0e0"};

  color: ${(props) =>
    props.$type === "positive" || props.$type === "negative" ? "#fff" : "#000"};
`;

//$type={btnType} type을 전달해야 이걸로 구분이 가능하다.

function Button({ text, type, onClick }) {
  const btnType = ["positive", "negative"].includes(type) ? type : "default";
  return (
    <>
      <ButtonStyle $type={btnType} onClick={onClick}>
        {text}
      </ButtonStyle>
    </>
  );
}

export default Button;
