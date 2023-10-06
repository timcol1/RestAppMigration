package avlyakulov.timur.restapp.repositories;

import avlyakulov.timur.restapp.models.ToDoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepository extends JpaRepository<ToDoModel, Long> {

}