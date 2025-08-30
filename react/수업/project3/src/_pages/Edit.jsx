import styled from "styled-components";
import Editor from "../component/Editor";
import Layout from "../layout/Layout";

import Header from "../layout/Header";
import Button from "../component/Button";

/**
 * 작성한 일기를 수정할 수 있는 Edit 페이지
 */

function Edit() {
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
        <Editor
          initData={{
            date: new Date().getTime(),
            emotionId: 1,
            content: "이전에 작성했던 일기",
          }}
          onSubmit={() => {
            alert("작성 완료 버튼을 클릭했음");
          }}
        />
      </Layout>
    </>
  );
}

export default Edit;
