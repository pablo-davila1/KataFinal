package co.com.sofka.crud.controllers;

import co.com.sofka.crud.DTO.RequestTodo;
import co.com.sofka.crud.DTO.ResponseTodo;
import co.com.sofka.crud.moldels.Todo;
import co.com.sofka.crud.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TodoController {

    @Autowired
    private TodoService service;

    @GetMapping(value = "api/todos")
    public List<ResponseTodo> list(){

        return service.list();
    }
    
    @PostMapping(value = "api/todo")
    public ResponseTodo save(@Valid @RequestBody RequestTodo request){

        return service.save(request);
    }

    @PutMapping(value = "api/todo")
    public ResponseTodo update(@Valid @RequestBody RequestTodo request){
        if(request.getId() != null){
            return service.save(request);
        }
        throw new RuntimeException("No existe el id para actualziar");
    }

    @DeleteMapping(value = "api/{id}/todo")
    public void delete(@PathVariable("id")Long id){
        service.delete(id);
    }

    @GetMapping(value = "api/{id}/todo")
    public ResponseTodo get(@PathVariable("id") Long id){
        return service.get(id);
    }

}
