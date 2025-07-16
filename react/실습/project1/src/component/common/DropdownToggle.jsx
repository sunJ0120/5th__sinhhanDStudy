import styled from "styled-components";

const CategoryBtn = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  padding-block: 10px;
  padding-inline: 10pt 10pt;
  background-color: #f5f3ea;
  color: #8b6d5c;
  border: 1px solid #8b6d5c;
  border-radius: 10px;
  font-size: 12px;
  gap: 4px;
`;

//function과 이름이 달라야 무한로딩이 안걸린다.
function DropdownToggle() {
  return (
    <>
      <CategoryBtn>전체 내역</CategoryBtn>
    </>
  );
}

export default DropdownToggle;
