package pl.stm.cw1.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data

@JsonIgnoreProperties({"tasks"})
public class User {
		

		@Id
		@GeneratedValue
		Long userId;
		String name;
		String lastName;
		String email;
		String password;
		@Column(columnDefinition="BOOLEAN DEFAULT false")
		boolean status=false;
		@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
		LocalDateTime registrationDateTime=LocalDateTime.now();
		
		@OneToMany(targetEntity = pl.stm.cw1.model.Task.class, mappedBy ="user",
	            cascade = CascadeType.ALL)
		List<Task> tasks;

		public User(String name, String lastName, String email, String password) {
			this.name=name;
			this.lastName=lastName;
			this.email=email;
			this.password=password;
		}
		
		
		
}
