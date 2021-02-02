package pl.stm.cw1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.stm.cw1.model.Task;
import pl.stm.cw1.model.TaskStatus;
import pl.stm.cw1.model.TaskType;
import pl.stm.cw1.model.User;
import pl.stm.cw1.service.TaskService;
import pl.stm.cw1.service.UserService;

@Controller
public class StmWebController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	TaskService taskService;
	
	public StmWebController()
	{		
	}
	//a
	@GetMapping("/web/user/add")
	public String addUserForm(Model model)
	{
		model.addAttribute("user", new User());
		return "addUser";
	}
	@PostMapping("/web/user/add")
	public String addUser(@ModelAttribute User user)
	{
		userService.addUser(user);
		return "redirect:/web/user/showAll";
	}
	
	//b
	@GetMapping("/web/user/showAll")
	public String showAllUsers(Model model)
	{
		model.addAttribute("users", userService.getAllUsers());
		return "showAllUsers";
	}
	
	//c
	@GetMapping("/web/user/showByPKOrEmail")
	public String showUserByPKorEmail(@RequestParam (required = false) String pasedValue, Model model)
	{
		if(pasedValue != null)
		{
			Optional<User> usr = userService.getUserByIdOrEmail(pasedValue);
			if(usr.isPresent())
				model.addAttribute("user", usr.get());
		}
		return "findUserByPrimKeyOrEmail";
	}
	
	//d
	@GetMapping("/web/user/opositeStatus")
	public String opositeUserStatus(@RequestParam (required = false) Long userId, Model model)
	{
		model.addAttribute("users", userService.getAllUsers());
		if(userId != null)
		{
			Optional<User> usr = userService.getAllUsers().stream().filter(x-> x.getUserId()==userId).findFirst();
			if(usr.isPresent())
			{
				usr.get().setStatus(!usr.get().isStatus());
				userService.addUser(usr.get());
			}
		}
		return "opositeUserStatus";
	}
	//e
	@GetMapping("/web/user/delete")
	public String deleteUser(@RequestParam (required = false) Long userId, Model model)
	{
		if(userId != null)
		{
			userService.deleteUserByIdWithRelations(userId);
		}
		model.addAttribute("users", userService.getAllUsers());		
		return "deleteUser";
	}
	//f
	@GetMapping("/web/task/add")
	public String addTaskForm(Model model)
	{
		model.addAttribute("task", new Task());
		model.addAttribute("usr", userService.getAllUsers());
		model.addAttribute("tasktypes", TaskType.values());
		model.addAttribute("taskstatuses", TaskStatus.values());
		
		return "addTask";
	}
	@PostMapping("/web/task/add")
	public String addTask(@ModelAttribute Task task, @RequestParam (required = true) Long userToTask,
													@RequestParam (required = true) String type,
													@RequestParam (required = true) String status)
	{

		Optional<User> usr = userService.getAllUsers().stream().filter(x-> x.getUserId() == userToTask).findFirst();
		TaskStatus ts = TaskStatus.valueOf(status);
		TaskType tt = TaskType.valueOf(type);
		if(usr.isPresent() && ts != null && tt != null)
		{
			task.setUser(usr.get());
			task.setStatus(ts);
			task.setType(tt);
			taskService.makeNewTaskByUser(task);
		}
		return "redirect:/web/task/add";
	}
	//g
	@GetMapping("/web/task/showAllDesc")
	public String showAllTasksDesc(Model model)
	{
		model.addAttribute("tasks", taskService.showAllDesc());
		return "showAllTaskByDate";
	}
	//h
	@GetMapping("/web/task/showByNameStatusType")
	public String showTaskByNameOrStatusOrType(@RequestParam (required = false) String pasedValue, Model model)
	{
		if(pasedValue != null)
		{
			model.addAttribute("tasks", taskService.selectByTitleStatusOrTypes(pasedValue));
		}
		return "showTaskByNameOrStatusOrType";
	}
	//i
	@GetMapping("/web/task/changeTaskStatus")
	public String changeTaskStatus(@RequestParam (required = false) String status, @RequestParam (required = false) Long taskId, Model model)
	{
		model.addAttribute("tasks", taskService.showAllDesc());
		model.addAttribute("taskstatuses", TaskStatus.values());
		if(status != null && taskId != null)
		{
			Optional<Task> tsk = taskService.showAllDesc().stream().filter(x-> x.getTaskId()==taskId).findFirst();
			if(tsk.isPresent())
			{
				tsk.get().setStatus(TaskStatus.valueOf(status));
				taskService.makeNewTaskByUser(tsk.get());
			}
		}
		return "changeTaskStatus";
	}
	//j
	@GetMapping("/web/task/delete")
	public String deleteTask(@RequestParam (required = false) Long taskId, Model model)
	{
		if(taskId != null)
		{
			taskService.deleteByTaskId(taskId);
		}
		model.addAttribute("tasks", taskService.showAllDesc());		
		return "deleteTask";
	}
}
