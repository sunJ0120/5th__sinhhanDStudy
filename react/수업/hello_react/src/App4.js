import ContextConsumer from "./component/ContextConsumer";
import Context1 from "./component/Context1";

const App4 = () => {
  return (
    <>
      <Context1.Provider value={{ name: "이순신" }}>
        <ContextConsumer />
      </Context1.Provider>
    </>
  );
};

export default App4;
