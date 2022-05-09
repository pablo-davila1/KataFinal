import React, { useState } from "react";
import ListToList from "../components/ListToList";

const ListTodo = () => {
  const [nombreLista, setNombreLista] = useState("");
  const [listaId, setListaId] = useState(0);
  console.log(listaId);
  const handleSubmitNombreLista = (e) => {
    setNombreLista(e.target.value);
  };

  const crearLista = (event) => {
    
    const request = {
      name: nombreLista,
      id: null,
    };

    //Aca nos conectamos con el api
    if (
      request.name === undefined ||
      request.name === null ||
      request.name === ""
    ) {
        event.preventDefault();
      console.log("Debes ingresar un nombre");
    } else {
      fetch("http://localhost:8080/api/list/listtodo", {
        method: "POST",
        body: JSON.stringify(request),
        headers: {
          "Content-Type": "application/json",
        },
      })
        .then((response) => response.json())
        .then((todo) => {
          setListaId(todo.id);
        });
    }
    const myForm = document.querySelector("#myform");
    myForm.reset();
  };

  return (
    <>
      <form id="myform">
        <input
          id="nombrelista"
          placeholder="Ingresar nombre lista"
          onChange={handleSubmitNombreLista}
        />
        <button id="btnlista" onClick={crearLista}>
          Crear
        </button>
      </form>

      <ListToList/>
    </>
  );
};



export default ListTodo;
