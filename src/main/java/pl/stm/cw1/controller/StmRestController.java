package pl.stm.cw1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.stm.cw1.model.Task;
import pl.stm.cw1.model.TaskStatus;
import pl.stm.cw1.model.TaskType;
import pl.stm.cw1.model.User;
import pl.stm.cw1.service.TaskService;
import pl.stm.cw1.service.UserService;

@RestController
public class StmRestController {
	UserService userService;
    TaskService taskService;

    @Autowired
    public StmRestController(UserService userService, TaskService taskService){
        this.userService = userService;
        this.taskService = taskService;
    }
	
    //a. (1p.) metoda do tworzenia nowego użytkownika
    @PostMapping("/user/add")
    public User addUser(@RequestParam("name") String name,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("password") String password) {
    	
    	return userService.addUser(new User(name,lastName,email, password));
    }
    //b. (1p.) metoda zwracająca wszystkich użytkowników
    @GetMapping("user/getAll")
    public List<User> getAllUsers(){
    	return userService.getAllUsers();
    }
    //c. (1p.) metoda zwracająca użytkownika wyszukanego na podstawie klucza głównego lub adresu email
    @GetMapping("user/getUserByIdOrEmail")
    public Optional<User> getByIdOrEmail(@RequestParam("value") String value){
    	return userService.getUserByIdOrEmail(value);
    }
    //d. (1p.) metoda zmieniająca status użytkownika na wartość przeciwną względem aktualnej
    @PutMapping("user/changeStatus/{id}")
    public boolean changeUserStatus(@PathVariable("id") long id)
    {
    	return userService.changeUserStatus(id);
    }
    //e. (1p.) metoda usuwająca użytkownika wraz z jego relacjami
    @DeleteMapping("user/delete/{id}")
    public boolean deleteUser(@PathVariable("id") long id)
    {
    	return userService.deleteUserByIdWithRelations(id);
    }
    //f. (1p.) metoda do tworzenia nowego zadania przez użytkownika
    @PostMapping("user/addNewTask")
    public boolean addNewTask(
    		@RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("type") TaskType type,
            @RequestParam("status") TaskStatus status,
            @RequestParam("userId") User user) {
    	return taskService.makeNewTaskByUser(new Task(title,description,type,status,user));
    }
    //g. (1p.) metoda pobierająca wszystkie zadania uporządkowane po dacie dodania malejąco
    @GetMapping("task/getAllDesc")
    public List<Task> getAllDesc(){
    	return taskService.showAllDesc();
    }
    //h. (1p.) metoda wyszukująca zadania po nazwie, statusie lub typie
    @GetMapping("task/getByNameOrStatusOrType")
    public List<Task> getTaskByNameOrStatusOrType(@RequestParam("value") String value)
    {
    	return taskService.selectByTitleStatusOrTypes(value);
    }
    //i. (1p.) metoda zmieniająca status zadania na inny dozwolony status
    @PutMapping("task/changeStatus/{id}")
    public boolean changeTaskStatus(@RequestParam("task") long id,
            @RequestParam("status") TaskStatus status)
    {
    	return taskService.changeStatusByTaskId(id, status);
    }
    //j. (1p.) metoda usuwająca zadanie, ale nie usuwająca użytkownika przypisanego do zadania
    @DeleteMapping("task/delete/{id}")
    public boolean deleteTask(@PathVariable("id") long id) {
    	return taskService.deleteByTaskId(id);
    }
}
