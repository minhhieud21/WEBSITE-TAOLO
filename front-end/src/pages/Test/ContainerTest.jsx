import React, { useEffect } from "react";

const ContainerTest = ({ fetchApiPoke, poke }) => {
  useEffect(() => {
    fetchApiPoke();
  }, [fetchApiPoke]);

  useEffect(() => {
    console.log("tesst", poke);
  }, [poke]);

  return (
    <>
      <h1>ContainerTest</h1>
      {poke.results.map((value) => {
        return (
          <>
            <span key={value.id}>{value.name} === </span>
            {/* <img src={value.url} alt="img" /> */}
          </>
        );
      })}
    </>
  );
};

export default ContainerTest;
