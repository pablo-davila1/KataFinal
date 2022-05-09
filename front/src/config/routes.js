
import ListTodo from "../pages/ListTodo";
import Todo from "../pages/Todo"

const routesUser= [
    {
      patch: "/todo",   
      component: Todo,  
    },
    {
      patch: "/",  
      component: ListTodo,  
    },
   
  ];


  const routes = [...routesUser]

  export default routes;