import styled from "styled-components";
import Layout from "../layout/Layout";
import DropdownToggle from "../common/DropdownToggle";
import OrderCard from "../order/OrderCard";
import OrderTitle from "../order/OrderTitle";

/**
 * order 페이지를 위한 UserOrder()
 * 
 * - 모든 pages에서는 Layout을 import 해서 사용한다.- 
 */

const BoxWrapper = styled.div`
  border: 1px solid #99a99b;
  overflow: hidden;
  flex: 1;
  width: 100%;
  padding-bottom: 10px;
  margin-top: 10pt; //이거 통일감을 주려면 20이 맞는거 같고..잘 모르겠다.
  box-sizing: border-box;
  flex: 1;

  color: black; //text 표현을 위한 임시
`;

function UserOrder() {
  return (
    <>
      <Layout>
        <OrderTitle
          titleLeft="전체 주문 내역"
          titleRight={<DropdownToggle />}
        />
        <BoxWrapper>
          <OrderCard />
        </BoxWrapper>
      </Layout>
    </>
  );
}

export default UserOrder;
