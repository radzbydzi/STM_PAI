package pl.stm.cw1.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data

//@JsonIgnoreProperties({"user"})
public class Task {


	@Id
	@GeneratedValue
	Long taskId;
	String title;
	String description;
	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	LocalDateTime dateAdded = LocalDateTime.now();
	TaskType type;
	TaskStatus status;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id")
	User user;
	

	public Task(String title, String description, TaskType type, TaskStatus status, User user) {
		this.title=title;
		this.description=description;
		this.type=type;
		this.status=status;
		this.user=user;
	}
}
