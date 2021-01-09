package pl.stm.cw1.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.stm.cw1.model.Task;
import pl.stm.cw1.model.TaskStatus;
import pl.stm.cw1.model.TaskType;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long>{
    List<Task> findTasksByTitle(String title);

    List<Task> findTasksByStatus(TaskStatus status);

    List<Task> findTasksByType(TaskType type);
    
    //List<Task> findAllDesc();
}
