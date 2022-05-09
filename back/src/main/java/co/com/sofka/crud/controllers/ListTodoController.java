package co.com.sofka.crud.controllers;

import co.com.sofka.crud.DTO.RequestListTodo;
import co.com.sofka.crud.DTO.ResponseListTodo;
import co.com.sofka.crud.moldels.ListTodo;
import co.com.sofka.crud.services.ListTodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/list")

public class ListTodoController {
    @Autowired
    private ListTodoService listtodoservice;

    @GetMapping(value = "listtodos")
    public Iterable<ResponseListTodo> list() {
        return listtodoservice.list();

    }

    @PostMapping(value = "listtodo")
    public ResponseListTodo save(@Valid @RequestBody RequestListTodo request) {
        return listtodoservice.save(request);
    }

    @PutMapping(value = "listtodo")
    public ResponseListTodo update(@Valid @RequestBody RequestListTodo request) {
        if (request.getId() != null) {
            return listtodoservice.save(request);
        }
        throw new RuntimeException("No existe el id para actualziar");
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable("id") Long id) {
        listtodoservice.delete(id);
    }

    @GetMapping(value = "api/{id}/todo")
    public ResponseListTodo get(@PathVariable("id") Long id) {
        return listtodoservice.get(id);
    }

}
