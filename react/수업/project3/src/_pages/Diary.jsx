import styled from "styled-components";
import { useParams } from "react-router-dom";
import Layout from "../layout/Layout";

import Header from "../layout/Header";
import Button from "../component/Button";

/**
 * 작성한 일기를 상세히 볼 수 있는 Diary 페이지
 */

function Diary() {
  const { id } = useParams();

  return (
    <>
      <Layout
        header={
          <Header
            title={"Home"}
            leftChild={
              <Button
                type="positive"
                text={"긍정 버튼"}
                onClick={() => {
                  alert("positive btn");
                }}
              />
            }
            rightChild={
              <Button
                type="negative"
                text={"부정 버튼"}
                onClick={() => {
                  alert("negative btn");
                }}
              />
            }
          />
        }
      >
        <div>{id}번 일기</div>
        Diary 페이지 입니당~
      </Layout>
    </>
  );
}

export default Diary;
