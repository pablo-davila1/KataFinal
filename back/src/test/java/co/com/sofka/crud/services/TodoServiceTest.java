package co.com.sofka.crud.services;

import co.com.sofka.crud.moldels.ListTodo;
import co.com.sofka.crud.moldels.Todo;
import co.com.sofka.crud.repositories.TodoRepository;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class TodoServiceTest {

    @Autowired
    TodoRepository todoRepository;

    @Test
    public void savetodo(){

        ListTodo listTodoModel = new ListTodo(2L, "ListaEjemplo");
        Todo todoModel = new Todo();
        //todoModel.setId(100L);
        todoModel.setName("Tarea de ejemplo");
        todoModel.setCompleted(false);
        todoModel.setListTodo(listTodoModel);

        Todo todoSave = todoRepository.save(todoModel);
        Assert.assertNotNull(todoSave);
    }

}
