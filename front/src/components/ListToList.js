import React, { useState, useEffect } from "react";
import {
  Button,
  Modal,
  ModalBody,
  ModalHeader,
  ModalFooter,
  FormGroup,
} from "reactstrap";
const ListToList = () => {
  
  //Estados para los modales
  const [modalEditar, setModalEditar] = useState(false);
  const [modalEliminar, setModalEliminar] = useState(false);
  const [tareaSeleccionada, setTareaSeleccionada] = useState({
    id: "",
    name: "",
    completed: false,
    listTodo: {
      id: "",
      name: "",
    },
  });

  //Funciones para los modales
  const seleccionarTarea = (tarea, caso) => {
    setTareaSeleccionada(tarea);
    caso === "Editar" ? setModalEditar(true) : setModalEliminar(true);
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setTareaSeleccionada((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };
  //Se actualiza la tarea
  const editar = () => {
    fetch("http://localhost:8080/api/todo", {
      method: "PUT",
      body: JSON.stringify(tareaSeleccionada),
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((response) => response.json())
      .then((todo) => {});
    setModalEditar(false);
    window.location.reload();
  };

  //Traer las tareas de la base de datos
  const [listaTareas, setlistaTareas] = useState([]);
  const [nombreTarea, setNombreTarea] = useState("");
  useEffect(() => {
    fetch("http://localhost:8080/api/todos")
      .then((response) => response.json())
      .then((list) => {
        setlistaTareas(list);
      });
  }, []);

  //Traer las listas de la base de datos
  const [listas, setlistas] = useState([]);
  useEffect(() => {
    fetch("http://localhost:8080/api/list/listtodos")
      .then((response) => response.json())
      .then((list) => {
        setlistas(list);
      });
  }, []);

  //hundle tarea
  const handleSubmitNombreTarea = (e) => {
    setNombreTarea(e.target.value);
  };

  //Crear tarea
  const crearTarea = (id, nombreLista) => {
    /* event.preventDefault(); */
    const request = {
      name: nombreTarea,
      id: null,
      completed: false,
      listTodo: {
        id: id,
        name: nombreLista,
      },
    };
    console.log(typeof request.name);

    if (
      request.name === undefined ||
      request.name === null ||
      request.name === ""
    ) {
      console.log("Debes ingresar un nombre");
    } else {
      fetch("http://localhost:8080/api/todo", {
        method: "POST",
        body: JSON.stringify(request),
        headers: {
          "Content-Type": "application/json",
        },
      })
        .then((response) => response.json())
        .then((todo) => {});
    }
    const myformtarea = document.querySelector("#myformtarea");
    myformtarea.reset();
  };

  //Eliminar lista
  const eliminarLista = (id) => {
    fetch(`http://localhost:8080/api/list/${id}`, {
      method: "DELETE",
    }).then((list) => {});
    window.location.reload();
  };
  console.log(listaTareas);
  console.log(listas);

  //Eliminar tarea
  const eliminar = (id) => {
    fetch(`http://localhost:8080/api/${id}/todo`, {
      method: "DELETE",
    }).then((list) => {});
    window.location.reload();
  };

  //Actualizar tarea
  const onChange = (event, tarea, id, nombreLista) => {
    const request = {
      name: tarea.name,
      id: tarea.id,
      completed: event.target.checked,
      listTodo: {
        id: id,
        name: nombreLista,
      },
    };

    fetch("http://localhost:8080/api/todo", {
      method: "PUT",
      body: JSON.stringify(request),
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((response) => response.json())
      .then((todo) => {});
      window.location.reload();
  };

  return (
    <>
      {listas.map((lista, index) => {
        return (
          <div key={index}>
            <h1>{lista.name}</h1>
            <button onClick={() => eliminarLista(lista.id)}>
              Eliminar lista
            </button>
            <form id="myformlista">
              <input
                id="nombretarea"
                placeholder="Ingresar nombre de tarea"
                onChange={handleSubmitNombreTarea}
              />
              <button
                id="btntarea"
                onClick={() => crearTarea(lista.id, lista.name)}
              >
                Crear
              </button>
            </form>
            <table>
              <thead>
                <tr>
                  <td>ID</td>
                  <td>Tarea</td>
                  <td>¿Completado?</td>
                </tr>
              </thead>
              <tbody>
                {listaTareas.map((tarea, index) => {
                  const { id, name } = tarea.listTodo;
                  if (lista.id === id) {
                    return (
                      <tr key={index}>
                        <td>{tarea.id}</td>
                        <td>{tarea.name}</td>

                        <td>
                          <input
                            type="checkbox"
                            defaultChecked={tarea.completed}
                            onChange={(event) =>
                              onChange(event, tarea, id, lista.name)
                            }
                          ></input>
                        </td>

                        <td>
                          <button
                            onClick={() => seleccionarTarea(tarea, "Editar")}
                          >
                            Editar
                          </button>
                          <button
                            onClick={() => seleccionarTarea(tarea, "Eliminar")}
                          >
                            Eliminar
                          </button>
                        </td>
                      </tr>
                    );
                  }
                })}
              </tbody>
            </table>
          </div>
        );
      })}
      <Modal isOpen={modalEditar}>
        <ModalHeader>
          <div>
            <h3>Editar tarea</h3>
          </div>
        </ModalHeader>
        <ModalBody>
          <FormGroup>
            <label>ID</label>
            <input
              className="form-control"
              name="id"
              readOnly
              type="text"
              value={tareaSeleccionada && tareaSeleccionada.id}
            />
          </FormGroup>
          <FormGroup>
            <label>Nombre</label>
            <input
              className="form-control"
              name="name"
              type="text"
              value={tareaSeleccionada && tareaSeleccionada.name}
              onChange={handleChange}
            />
          </FormGroup>
          <FormGroup>
            <label>completada</label>
            <input
              className="form-control"
              name="completada"
              readOnly
              type="text"
              value={tareaSeleccionada && tareaSeleccionada.completed}
              onChange={handleChange}
            />
          </FormGroup>
          <FormGroup>
            <label>id lista</label>
            <input
              className="form-control"
              name="id"
              readOnly
              type="text"
              value={tareaSeleccionada && tareaSeleccionada.listTodo.id}
              onChange={handleChange}
            />
          </FormGroup>
          <FormGroup>
            <label>Nombre de lista</label>
            <input
              className="form-control"
              name="name"
              readOnly
              type="text"
              value={tareaSeleccionada && tareaSeleccionada.listTodo.name}
              onChange={handleChange}
            />
          </FormGroup>
        </ModalBody>
        <ModalFooter>
          <Button onClick={editar} color="primary">
            Editar
          </Button>
          <Button
            onClick={() => {
              setModalEditar(false);
            }}
            color="danger"
          >
            Salir
          </Button>
        </ModalFooter>
      </Modal>

      <Modal isOpen={modalEliminar}>
        <ModalHeader>
          <div>
            <h3>Estas seguro de Eliminar la tarea: </h3>
          </div>
        </ModalHeader>
        <ModalBody>
          <h2 className="tareaeliminar">{tareaSeleccionada.name}</h2>
        </ModalBody>
        <ModalFooter>
          <Button
            color="primary"
            onClick={() => eliminar(tareaSeleccionada.id)}
            className="btnmodaleliminar"
          >
            ELiminar
          </Button>

          <Button
            className="btnmodaleliminar"
            onClick={() => {
              setModalEliminar(false);
            }}
            color="danger"
          >
            Salir
          </Button>
        </ModalFooter>
      </Modal>
    </>
  );
};

export default ListToList;
