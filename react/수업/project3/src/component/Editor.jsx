import { useEffect, useState } from "react";
import { emotionsList, getFormattedDate } from "../util";
import styled from "styled-components";
import { useNavigate } from "react-router-dom";
import EmotionItem from "./EmotionItem";
import Button from "../component/Button";
import MyDatePicker from "./MyDatePicker";

const EditorWrapper = styled.div`
  width: 100%;
`;

const EditorSection = styled.div`
  /* textarea 와 input 요소 전부에 공통 스타일 */
  textarea,
  input {
    border: none;
    border-radius: 5px;
    background-color: #ececec;
    padding: 20px;
    font-size: 20px;
  }

  /* input 만 추가 스타일 */
  input {
    padding: 10px 0;
    cursor: pointer;
  }

  /* textarea 만 추가 스타일 */
  textarea {
    color: grey;
    width: 100%;
    min-height: 200px;
    box-sizing: border-box;
    resize: vertical;
    font-family: "Ownglyph_corncorn-Rg";
  }
`;

const EditorBottomWrapper = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: start;
`;

const DateSelect = styled.input.attrs({ type: "date" })`
  /* 네이티브 스타일 다 지우기 */
  -webkit-appearance: none;
  -moz-appearance: none; /* Firefox */
  appearance: none;

  font-family: "Ownglyph_corncorn-Rg";
  background-color: lightcyan;
  color: darkslategray;
  padding: 10px 20px;
`;

const EmotionListWrapper = styled.div`
  flex-wrap: wrap;
  width: 100%;
  display: flex;
  justify-content: space-around;
`;

function Editor({ initData, onSubmit }) {
  const [state, setState] = useState({
    // date: getFormattedDate(new Date()),
    date: new Date(),
    emotionId: 3,
    content: "",
  });

  //initData가 변할 때마다 추적한다.
  //또한, initData가 있으면 setState 한다.
  useEffect(() => {
    if (initData) {
      setState({
        ...initData,
        // date: getFormattedDate(new Date(parseInt(initData.date))),
        date: new Date(parseInt(initData.date)),
      });
    }
  }, [initData]);

  //date를 전부 target.value로 만든다.
  const handleChangeDate = (date) => {
    setState((prev) => ({
      ...prev,
      date,
    }));
  };

  const handleChangeContent = (e) => {
    setState({
      ...state,
      content: e.target.value,
    });
  };

  const handleSubmit = () => {
    onSubmit({ ...state, date: getFormattedDate(state.date) }); // YYYY-MM-DD 문자열
  };

  const navigate = useNavigate(); //useNavigate()를 불러온다.

  //이걸 이용하면 뒤로갈 수 있다.
  const handleOnGoBack = () => {
    navigate(-1);
  };

  const handleChangeEmotion = (emotionId) => {
    setState({
      ...state,
      emotionId,
    });
  };

  return (
    <>
      <EditorWrapper>
        <EditorSection>
          <h4>오늘의 날짜</h4>
          <MyDatePicker
            date={state.date} /* Date 객체만 넘김 */
            onChange={handleChangeDate} /* Date → setState */
          />
        </EditorSection>
        <EditorSection>
          <h4>오늘의 감정</h4>
          <EmotionListWrapper>
            {emotionsList.map((it) => (
              <EmotionItem
                key={it.id}
                {...it}
                onClick={handleChangeEmotion}
                isSelected={state.emotionId === it.id}
              />
            ))}
          </EmotionListWrapper>
        </EditorSection>
        <EditorSection>
          <h4>오늘의 일기</h4>
          <textarea
            placeholder="오늘은 어땠나요?"
            value={state.content}
            onChange={handleChangeContent}
          ></textarea>
          <EditorBottomWrapper>
            <Button text={"취소하기"} onClick={handleOnGoBack} />
            <Button
              text={"작성 완료"}
              type={"positive"}
              onClick={handleSubmit}
            />
          </EditorBottomWrapper>
        </EditorSection>
      </EditorWrapper>
    </>
  );
}

export default Editor;
