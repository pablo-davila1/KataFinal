package co.com.sofka.crud.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class RequestTodo implements Serializable {

    private Long id;
    @NotBlank
    private String name;
    @NotNull
    private boolean completed;
    @NotNull
    private RequestListTodo listTodo;

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

    public RequestListTodo getListTodo() {
        return listTodo;
    }

    public void setListTodo(RequestListTodo listTodo) {
        this.listTodo = listTodo;
    }
}
