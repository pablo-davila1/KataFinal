package co.com.sofka.crud.DTO;

import java.io.Serializable;

public class ResponseTodo implements Serializable {

    private Long id;

    private String name;

    private boolean completed;

    private ResponseListTodo listTodo;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public ResponseListTodo getListTodo() {
        return listTodo;
    }

    public void setListTodo(ResponseListTodo listTodo) {
        this.listTodo = listTodo;
    }
}
