package co.com.sofka.crud.moldels;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "listtodo")
public class ListTodo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "listTodo")
    private List<Todo> todo;

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

    public ListTodo(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ListTodo() {
    }
}
