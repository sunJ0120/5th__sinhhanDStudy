import styled from "styled-components"; //styled-components import
import React from "react";

const Wrapper = styled.div`
  display: flex;
  justify-content: flex-start;
  width: 100%;
  font-size: 12pt;

  padding: 12px 0; /* 위아래 여백 */
  border-bottom: 1px solid cadetblue; /* 구분선 */

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

const CheckBox = styled.input.attrs({ type: "checkbox" })`
  appearance: none;
  width: 20px;
  height: 20px;
  border: 1px solid #ccc;
  border-radius: 3px;
  background-color: aliceblue;
  cursor: pointer;
  position: relative; /* ← 추가 */

  &:checked {
    background-color: steelblue;
  }

  /* ::after 공통 스타일은 미리 정의 */
  &::after {
    content: "";
    position: absolute; /* ← 절대 위치 */
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    display: flex; /* ← 중앙 정렬을 위해 flex 사용 */
    align-items: center;
    justify-content: center;
    pointer-events: none; /* ← 클릭 이벤트는 input에 전달 */
  }

  /* 체크됐을 때 아이콘만 바꿔줌 */
  &:checked::after {
    content: "👽";
    font-size: 15px;
    color: white;
  }

  &:focus {
    outline: none; /* ← 포커스 아웃라인 제거(필요 시) */
  }
`;

const Title = styled.div`
  display: flex;
  justify-content: flex-start;
  text-align: start;
  flex: 1;
`;

const DateCol = styled.div`
  color: gray;
`;

const BtnCol = styled.div``;

const Btn = styled.button`
  cursor: pointer;
  color: white;
  background-color: cadetblue;
  border: none;
  border-radius: 5px;
  padding: 5px;
`;

//todo라는 요소를 구조분해 할당해서 담는다.
//it에는 todo에 있는 객체들이 담긴다.
//결국, onUpdate를 여기 item까지 가게 하는게 목적이기 때문에 여기까지 전달해야 한다.
function TodoItem({ id, content, isDone, createDate, onUpdate, onDelete }) {
  console.log(`${id} 👽 아마겟돈~`); //이를 통해 최적화가 잘 되었는지 살펴보자.

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

export default React.memo(TodoItem);
