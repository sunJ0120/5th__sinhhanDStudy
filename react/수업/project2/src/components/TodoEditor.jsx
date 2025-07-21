import { useState } from "react";
import styled from "styled-components"; //styled-components import

const EditorWrapper = styled.div`
  width: 100%;
  display: flex;
  gap: 10px;
`;

const Input = styled.input`
  flex: 1;
  box-sizing: border-box;
  border: 1px solid, rgb(220, 220, 220);
  background-color: lightgray;

  &:focus {
    outline: none;
    border-bottom: 1pt solid cadetblue;
  }
`;

const Button = styled.button`
  cursor: pointer;
  width: 80px;
  border: none;
  background-color: cadetblue;
  color: white;
  border-radius: 5px;
`;

const Wrapper = styled.div`
  width: 100%;
  display: flex;
  justify-content: flex-start;
  flex-direction: column;
`;

const Title = styled.h3`
  display: flex;
  justify-content: flex-start;
  width: 100%;
`;
//중괄호 안에 넣으면 구조분해 할당이 된다.
function TodoEditor({ onCreate }) {
  const [content, setContent] = useState("");
  //바뀔 경우, 이렇개 content를 setting 하도록 한다.
  const onChangeContent = (e) => {
    setContent(e.target.value);
  };

  //제출을 위한 함수이다. content를 전달한다.
  const onSubmit = () => {
    //입력값이 없을 경우, focus를 주는 방식으로 빈값 입력을 막는다.
    if (!content) {
      inputRef.current.focus();
      return;
    }
    onCreate(content);
    setContent(""); //새 아이템을 추가하고 난 후, 빈칸으로 초기화한다.
  };

  //enter를 누르면 제출되도록 한다.
  const onKeyDown = (e) => {
    if (e.keyCode === 13) {
      onSubmit();
    }
  };

  return (
    <>
      <Wrapper>
        <Title>새로운 Todo 작성하기 👈🏻</Title>
        <div>
          <EditorWrapper>
            <Input
              value={content}
              onChange={onChangeContent}
              onKeyDown={onKeyDown}
              placeholder="새로운 Todo..."
            />
            <Button onClick={onSubmit}>추가</Button>
          </EditorWrapper>
        </div>
      </Wrapper>
    </>
  );
}

export default TodoEditor;
