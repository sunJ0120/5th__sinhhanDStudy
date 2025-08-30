import styled from "styled-components";
import Layout from "../layout/Layout";

import Header from "../layout/Header";
import Button from "../component/Button";

/**
 * 새로운 일기를 작성하는 New 페이지
 */

function New() {
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
        New 페이지 입니당~
      </Layout>
    </>
  );
}

export default New;
