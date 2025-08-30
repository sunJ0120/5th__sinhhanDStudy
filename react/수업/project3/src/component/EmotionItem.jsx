import styled, { keyframes } from "styled-components";
import React from "react";

// 선택됐을 때 한번만 펄스 애니메이션
const pulse = keyframes`
  0%   { transform: scale(1); }
  50%  { transform: scale(1.05); }
  100% { transform: scale(1); }
`;

const EmotionItemContent = styled.div`
  cursor: pointer;
  border-radius: 5px;
  padding: 20px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  /* 트랜지션 추가 */
  transition: transform 0.2s ease, box-shadow 0.2s ease,
    background-color 0.2s ease;

  &:hover {
    /* 살짝 떠 있는 느낌 */
    transform: translateY(-4px);
    box-shadow: 0 6px 12px rgba(0, 0, 0, 0.2);
  }

  img {
    width: 60pt;
    margin-bottom: 10px;
  }

  span {
    font-size: 18px;
  }

  &.selected {
    /* 배경/글자색 변경 + 스케일 + 그림자 강조 */
    background-color: lightblue;
    color: darkslategrey;
    transform: scale(1.1);
    box-shadow: 0 8px 16px rgba(0, 0, 0, 0.3);
    /* 선택 직후에 한번 펄스 애니메이션 */
    animation: ${pulse} 0.4s ease;
  }
`;

function EmotionItem({ id, img, name, onClick, isSelected }) {
  const handleOnClick = () => {
    onClick(id);
  };

  return (
    <>
      <EmotionItemContent
        onClick={handleOnClick}
        className={isSelected ? "selected" : ""}
      >
        <img alt={`emotion${id}`} src={img} />
        <span>{name}</span>
      </EmotionItemContent>
    </>
  );
}

export default EmotionItem;
