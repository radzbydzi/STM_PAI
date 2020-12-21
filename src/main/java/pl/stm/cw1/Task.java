package pl.stm.cw1;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Task {

	@Id
	@GeneratedValue
	Long taskId;
	String title;
	String description;
	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	LocalDateTime dateAdded;
	TaskType type;
	TaskStatus status;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	User user;
	
	public Task() {
		
	}
	public Task(Long taskId, String title, String description, LocalDateTime dateAdded, TaskType type,
			TaskStatus status) {
		super();
		this.taskId = taskId;
		this.title = title;
		this.description = description;
		this.dateAdded = dateAdded;
		this.type = type;
		this.status = status;
	}
	public Long getTaskId() {
		return taskId;
	}
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDateTime getDateAdded() {
		return dateAdded;
	}
	public void setDateAdded(LocalDateTime dateAdded) {
		this.dateAdded = dateAdded;
	}
	public TaskType getType() {
		return type;
	}
	public void setType(TaskType type) {
		this.type = type;
	}
	public TaskStatus getStatus() {
		return status;
	}
	public void setStatus(TaskStatus status) {
		this.status = status;
	}
	
	
}
