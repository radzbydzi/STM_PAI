package pl.stm.cw1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import pl.stm.cw1.model.Task;
import pl.stm.cw1.model.TaskStatus;
import pl.stm.cw1.model.TaskType;
import pl.stm.cw1.model.User;
import pl.stm.cw1.repo.TaskRepo;
import pl.stm.cw1.repo.UserRepo;

@Service
public class TaskService {
	@Autowired
	TaskRepo taskRepo;
	@Autowired
	UserRepo userRepo;
	
	//metoda do tworzenia nowego zadania przez użytkownika
	public boolean makeNewTaskByUser(Task task)
	{
		Optional<User> usr = userRepo.findById(task.getUser().getUserId());
		if(usr.isPresent())
		{	
			taskRepo.save(task);
			return true;
		}
		return false;
	}
	
	//metoda pobierająca wszystkie zadania uporządkowane po dacie dodania malejąco
	public List<Task> showAllDesc()
	{
		return taskRepo.findAll(Sort.by(Sort.Direction.DESC, "dateAdded"));
	}
	
	// metoda wyszukująca zadania po nazwie, statusie lub typie
	public List<Task> selectByTitleStatusOrTypes(String value){
		if(value.equalsIgnoreCase(TaskStatus.NEW.name()) 
				|| value.equalsIgnoreCase(TaskStatus.IN_PROGRESS.name())
				|| value.equalsIgnoreCase(TaskStatus.DONE.name())
				)
		{
			TaskStatus status = TaskStatus.valueOf(value.toUpperCase());
			return taskRepo.findTasksByStatus(status);
		}else if(value.equalsIgnoreCase(TaskType.BUG.name()) 
				|| value.equalsIgnoreCase(TaskType.FEATURE.name()) 
				|| value.equalsIgnoreCase(TaskType.TASK.name())
				)
		{
			TaskType types = TaskType.valueOf(value.toUpperCase());
			return taskRepo.findTasksByType(types);
		}else{
			return taskRepo.findTasksByTitle(value);
		}
	}
	
	//metoda zmieniająca status zadania na inny dozwolony status
	public boolean changeStatusByTaskId(long taskId, TaskStatus status)
	{
        Optional<Task> taskOpt = taskRepo.findById(taskId);
        if(taskOpt.isPresent()){
            Task task = taskOpt.get();
            if(task.getStatus() != (status)) {
            	task.setStatus(status);
                taskRepo.save(task);
                return true;
            }
        }
        return false;
	}
	//metoda usuwająca zadanie, ale nie usuwająca użytkownika przypisanego do zadania
	public boolean deleteByTaskId(long taskId){
        Optional<Task> taskOpt = taskRepo.findById(taskId);
        if(taskOpt.isPresent()){
        	taskRepo.deleteById(taskId);
        	return true;
        }
        return false;
    }
}
