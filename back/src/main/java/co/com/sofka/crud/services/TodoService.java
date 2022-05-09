package co.com.sofka.crud.services;

import co.com.sofka.crud.DTO.RequestTodo;
import co.com.sofka.crud.DTO.ResponseListTodo;
import co.com.sofka.crud.DTO.ResponseTodo;
import co.com.sofka.crud.moldels.ListTodo;
import co.com.sofka.crud.moldels.Todo;
import co.com.sofka.crud.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository repository;

    public List<ResponseTodo> list() {
        //Lista para obtener resultados desde la BD
        Iterable<Todo> todos = new ArrayList<>();
        todos = repository.findAll();

        /*Lista para devolver los resultados al front*/
        List<ResponseTodo> response= new ArrayList<>();

        //Recorremos lista de entidad todo
        for (Todo todo: todos) {
            ResponseTodo responseAdd = getResponseTodo(todo);
            response.add(responseAdd);
        }

        return response;
    }

    public ResponseTodo save(RequestTodo request) {
        Todo todo = getTodo(request);

        Todo todoSave = new Todo();
        todoSave = repository.save(todo);

        ResponseTodo responseTodo = getResponseTodo(todoSave);

        return responseTodo;
    }

    private Todo getTodo(RequestTodo request) {
        Todo todo = new Todo();
        todo.setId(request.getId());
        todo.setName(request.getName());
        todo.setCompleted(request.isCompleted());

        ListTodo listTodo = new ListTodo(request.getListTodo().getId(), request.getListTodo().getName());
        todo.setListTodo(listTodo);
        return todo;
    }

    private ResponseTodo getResponseTodo(Todo todoSave) {
        ResponseTodo responseTodo = new ResponseTodo();

        responseTodo.setId(todoSave.getId());
        responseTodo.setName(todoSave.getName());
        responseTodo.setCompleted(todoSave.isCompleted());

        ResponseListTodo responseListTodo = new ResponseListTodo();
        responseListTodo.setId(todoSave.getListTodo().getId());
        responseListTodo.setName(todoSave.getListTodo().getName());
        responseTodo.setListTodo(responseListTodo);
        return responseTodo;
    }

    public void delete(Long id) {
        repository.delete(repository.findById(id).orElseThrow());
    }

    public ResponseTodo get(Long id) {
        Todo todo = new Todo();
        ResponseTodo response = new ResponseTodo();
        todo = repository.findById(id).orElseThrow();

        //mapeo los valores en el response
        response = getResponseTodo(todo);

        return response;
    }

}
