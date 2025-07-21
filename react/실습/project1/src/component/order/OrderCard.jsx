import styled from "styled-components";
import CardBnt from "./CardBnt";
import poodingImg from "../../assets/image/pooding.jpg";

const TopWrapper = styled.div`
  position: relative;
  flex: 1;
  overflow-y: auto;
  min-height: 0;
  display: flex;
  flex-direction: column;
  height: 665px;
  padding: 10pt;
  box-sizing: border-box;

  /* Firefox, IE & Edge용 스크롤 숨기기 */
  scrollbar-width: none;
  -ms-overflow-style: none;
`;

const ProductCard = styled.article`
  border: 1px solid #99a99b;
  background-color: #e5dfd5;
  border-radius: 10px;
  overflow: hidden;
  width: 100%;

  padding: 10px 15px;
  margin-bottom: 10pt;
  box-sizing: border-box;
`;

const OrderTop = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 15px;
`;

const OrderMiddle = styled.div`
  display: flex;
  align-items: center;
  width: 100%;
  margin-top: 16px;
  gap: 16px;
`;

const OrderButtonWrapper = styled.div`
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 8px;
`;

const ImageWrapper = styled.div`
  width: 120px;
  height: 90px;

  img {
    display: block;
    width: 100%;
    height: 100%;
    object-fit: cover;
  }

`;

const OrderInfoWrapper = styled.div`
  width: 100%;
  display: flex;
  align-items: center;
  flex-direction: column;
  justify-content: space-between;
`;

const OrderInfo = styled.div`
  width: 100%;
  flex: 1;
`;

const OrderInfoSub = styled.div`
  margin-bottom: 4pt;
  display: flex;
  justify-content: space-between;
`;

const OrderAmount = styled.div`
  font-size: 14px;
`;

const OrderAmountValue = styled.div`
  font-size: 14px;
  justify-content: space-between;
`;

//-------------OrderTop--------------
const HeaderRightWrapper = styled.div`
  margin-left: auto;
  display: flex;
  align-items: center;
  gap: 8px;
`;

const OrderStatus = styled.div`
  background-color: #d8a8ab;
  padding: 4px 10px;
  border-radius: 20px;
  color: white;
`;

const StoreName = styled.div`
  font-size: 18px;
  font-weight: bold;
`;

function OrderCard() {
  return (
    <>
      <TopWrapper>
        <ProductCard>
          <OrderTop>
            <StoreName>가게 이름</StoreName>
            <HeaderRightWrapper>
              <OrderStatus>픽업 완료</OrderStatus>
              2025.07.11
            </HeaderRightWrapper>
          </OrderTop>

          <hr />

          <OrderMiddle>
            <ImageWrapper>
              <img src={poodingImg} alt="이미지" />
            </ImageWrapper>
            <OrderInfoWrapper>
              <OrderInfo>
                <OrderInfoSub>
                  <OrderAmount>수량 : </OrderAmount>
                  <OrderAmountValue>1개</OrderAmountValue>
                </OrderInfoSub>

                <OrderInfoSub>
                  <OrderAmount>픽업시간 : </OrderAmount>
                  <OrderAmountValue>18:00~19:00</OrderAmountValue>
                </OrderInfoSub>

                <OrderInfoSub>
                  <OrderAmount>결제금액 : </OrderAmount>
                  <OrderAmountValue>5,500원</OrderAmountValue>
                </OrderInfoSub>

                <OrderInfoSub>
                  <OrderAmount>사용포인트 : </OrderAmount>
                  <OrderAmountValue>0P</OrderAmountValue>
                </OrderInfoSub>
              </OrderInfo>
            </OrderInfoWrapper>
          </OrderMiddle>
          <OrderButtonWrapper>
            <CardBnt />
          </OrderButtonWrapper>
        </ProductCard>
      </TopWrapper>
    </>
  );
}

export default OrderCard;
