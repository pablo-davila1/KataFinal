package co.com.sofka.crud.services;

import co.com.sofka.crud.DTO.RequestListTodo;
import co.com.sofka.crud.DTO.ResponseListTodo;
import co.com.sofka.crud.DTO.ResponseTodo;
import co.com.sofka.crud.moldels.ListTodo;
import co.com.sofka.crud.repositories.ListTodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListTodoService {

    @Autowired
    public ListTodoRepository listtodorepository;

    public ArrayList<ListTodo> obtenerListTodo() {
        return (ArrayList<ListTodo>) listtodorepository.findAll();
    }

    public List<ResponseListTodo> list() {
        //Lista para obtener resultados desde la BD
        Iterable<ListTodo> listTodos = new ArrayList<>();
        listTodos = listtodorepository.findAll();

        //listar para devolver los resulados al front
        List<ResponseListTodo> response = new ArrayList<>();

        //Recorremos lista de entidad listTodo
        for (ListTodo listTodo : listTodos) {
            ResponseListTodo responseListTodo = new ResponseListTodo();

            responseListTodo.setId(listTodo.getId());
            responseListTodo.setName(listTodo.getName());

            response.add(responseListTodo);

        }
        return response;
    }

    public ResponseListTodo save(RequestListTodo request) {
        ListTodo listTodo = new ListTodo();
        listTodo.setId(request.getId());
        listTodo.setName(request.getName());

        ListTodo listTodoSave = new ListTodo();
        listTodoSave = listtodorepository.save(listTodo);

        ResponseListTodo responseListTodo = new ResponseListTodo();

        responseListTodo.setId(listTodoSave.getId());
        responseListTodo.setName(listTodoSave.getName());

        return responseListTodo;
    }

    public void delete(Long id) {
        listtodorepository.delete(listtodorepository.findById(id).orElseThrow());
    }

    public ResponseListTodo get(Long id) {

        ListTodo listTodo = new ListTodo();
        ResponseListTodo response = new ResponseListTodo();
        listTodo = listtodorepository.findById(id).orElseThrow();

        //mapeo los valores en el reponse

        response.setId(listTodo.getId());
        response.setName(listTodo.getName());


        return response;
    }

}
