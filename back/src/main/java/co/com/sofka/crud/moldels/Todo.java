package co.com.sofka.crud.moldels;

import javax.persistence.*;

@Entity
@Table(name = "todo")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private String name;
    private boolean completed;


    @ManyToOne()
    @JoinColumn(name="lisTodoid")
    private ListTodo listTodo;


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

    public ListTodo getListTodo() {
        return listTodo;
    }

    public void setListTodo(ListTodo listTodo) {
        this.listTodo = listTodo;
    }
}
