package co.com.sofka.crud.DTO;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class RequestListTodo implements Serializable {
    private Long id;
    @NotBlank
    private String name;

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
}
