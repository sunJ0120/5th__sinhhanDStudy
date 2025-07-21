import styled from "styled-components"; //styled-components import

const Wrapper = styled.div`
  display: flex;
  justify-content: flex-start;
  width: 100%;
  font-size: 12pt;

  padding: 12px 0; /* 위아래 여백 */
  border-bottom: 1px solid #e0e0e0; /* 구분선 */

  /* 마지막 요소면 선 없애기 */
  &:last-child {
    border-bottom: none;
  }
`;

const SubWrapper1 = styled.div`
  display: flex;
  gap: 10px;
  justify-content: flex-start;
  align-items: center;
  flex: 3;
`;

const SubWrapper2 = styled.div`
  display: flex;
  gap: 10px;
  flex: 2;
  justify-content: flex-end;
  align-items: center;
  width: 100%;
`;

const CheckBoxCol = styled.div``;

const CheckBox = styled.input.attrs({ type: "checkbox" })``;

const Title = styled.div`
  display: flex;
  justify-content: flex-start;
  flex: 1;
`;

const DateCol = styled.div`
  color: gray;
`;

const BtnCol = styled.div``;

const Btn = styled.button`
  cursor: pointer;
  color: gray;
  border: none;
  border-radius: 5px;
  padding: 5px;
`;

//todo라는 요소를 구조분해 할당해서 담는다.
//it에는 todo에 있는 객체들이 담긴다.
//결국, onUpdate를 여기 item까지 가게 하는게 목적이기 때문에 여기까지 전달해야 한다.
function TodoItem({ id, content, isDone, createDate, onUpdate, onDelete }) {
  const onChangeCheckbox = () => {
    onUpdate(id);
  };

  const onClickDelete = () => {
    onDelete(id);
  };

  return (
    <>
      <Wrapper>
        <SubWrapper1>
          <CheckBoxCol>
            <CheckBox onChange={onChangeCheckbox} checked={isDone}></CheckBox>
          </CheckBoxCol>
          <Title>{content}</Title>
        </SubWrapper1>
        <SubWrapper2>
          <DateCol>{createDate}</DateCol>
          <BtnCol>
            <Btn onClick={onClickDelete}>삭제</Btn>
          </BtnCol>
        </SubWrapper2>
      </Wrapper>
    </>
  );
}

export default TodoItem;
