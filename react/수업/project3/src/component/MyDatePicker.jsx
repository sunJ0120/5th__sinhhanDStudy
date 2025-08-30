import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import styled from "styled-components";
import React from "react";

// 1) Button을 styled-components로 추가 커스터마이징
const TriggerButton = styled.button`
  padding: 8px 16px;
  font-size: 14px;
  background: palevioletred;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;

  &:hover {
    background: #c45a7a;
  }
`;

// 2) forwardRef를 써서 react-datepicker 쪽 onClick, ref가 먹도록
const CustomInput = React.forwardRef(({ value, onClick, placeholder }, ref) => (
  <TriggerButton
    ref={ref}
    onClick={onClick}
    type="button" // HTML button 타입 지정
  >
    {value || placeholder || "날짜 선택"}
  </TriggerButton>
));
CustomInput.displayName = "CustomInput";

// 3) DatePicker에서 customInput으로 교체
function MyDatePicker({ date, onChange }) {
  return (
    <DatePicker
      selected={date}
      onChange={onChange}
      customInput={<CustomInput placeholder="날짜 선택" />}
      dateFormat="yyyy-MM-dd"
      showPopperArrow={false}
    />
  );
}

export default MyDatePicker;
