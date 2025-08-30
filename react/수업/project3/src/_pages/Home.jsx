import styled from "styled-components";
import { useSearchParams } from "react-router-dom";
import { useContext, useState } from "react";

//DiaryStateContext를 통해 일기 데이터를 가져오기 위함이다.
import { DiaryStateContext } from "../App";
import Layout from "../layout/Layout";

import Header from "../layout/Header";
import Button from "../component/Button";

/**
 * Home 페이지~
 */

function Home() {
  const [searchParams, setSearchParams] = useSearchParams();
  //useState의 초기값으로 현재 날짜를 준다.
  const data = useContext(DiaryStateContext);
  const [pivotDate, setPivotDate] = useState(new Date()); 
  const [filteredData, setFilteredData] = useState([]); //useState를 호출해서 filteredData를 만든다. State의 초기값은 빈 배열로 설정해야 한다.

  function onIncreaseMonth() {
    setPivotDate(new Date(pivotDate.getFullYear(), pivotDate.getMonth() + 1));
  }

  function onDecreaseMonth() {
    setPivotDate(new Date(pivotDate.getFullYear(), pivotDate.getMonth() - 1));
  }

  //@@년 @@월 @@일 이라는 것을 지정한다.
  const headerTitle = `${pivotDate.getFullYear()}년 ${
    pivotDate.getMonth() + 1
  }월`;

  console.log(searchParams.get("sort"));
  return (
    <>
      <Layout
        header={
          <Header
            title={headerTitle}
            leftChild={<Button text={"<"} onClick={onDecreaseMonth} />}
            rightChild={<Button text={">"} onClick={onIncreaseMonth} />}
          />
        }
      >
        {/* 바디 영역 */}
      </Layout>
    </>
  );
}

export default Home;
