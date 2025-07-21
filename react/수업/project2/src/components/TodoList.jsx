import styled from "styled-components"; //styled-components import
import TodoItem from "./TodoItem";
import { useState } from "react";

const SearchBar = styled.input`
  margin-bottom: 20px;
  width: 100%;
  border: none;
  border-bottom: 1px solid rgb(220, 220, 220);
  box-sizing: border-box;
  padding-top: 15px;
  padding-bottom: 15px;

  &:focus {
    outline: none;
    border-bottom: 1pt solid #1f93ff;
  }
`;

const Title = styled.h4`
  display: flex;
  justify-content: flex-start;
  width: 100%;
`;

const Wrapper = styled.div`
  display: flex;
  justify-content: flex-start;
  width: 100%;
  flex-direction: column;
`;

function TodoList({ todo, onUpdate, onDelete }) {
  const [search, setSearch] = useState("");
  //다음과 같이 setSearch로 서치어 등록
  const onChangeSearch = (e) => {
    setSearch(e.target.value);
  };

  //검색 결과를 return 하는 함수
  //search어가 포함되어 있는지를 체크한다.
  //toLowerCase()로 한 번 바꿔서 대소문자 구분하지 않도록 한다.
  const getSearchResult = () => {
    return search === ""
      ? todo
      : todo.filter((it) =>
          it.content.toLowerCase().includes(search.toLowerCase())
        );
  };

  return (
    <>
      <Wrapper>
        <Title>Todo List 👽</Title>
        <SearchBar
          value={search}
          onChange={onChangeSearch}
          placeholder="검색어를 입력하세요"
        />
        <div>
          {getSearchResult().map((it) => (
            <TodoItem
              key={it.id}
              {...it}
              onUpdate={onUpdate}
              onDelete={onDelete}
            />
          ))}
        </div>
      </Wrapper>
    </>
  );
}

export default TodoList;
