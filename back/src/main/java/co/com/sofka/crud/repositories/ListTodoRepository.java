package co.com.sofka.crud.repositories;

import co.com.sofka.crud.moldels.ListTodo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListTodoRepository extends CrudRepository<ListTodo, Long> {
}
