package pl.stm.cw1;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class User {
		@Id
		@GeneratedValue
		Long userId;
		String name;
		String lastName;
		String email;
		String password;
		@Column(columnDefinition="BOOLEAN DEFAULT false")
		boolean status;
		@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
		LocalDateTime registrationDateTime;
		
		@OneToMany(targetEntity = pl.stm.cw1.Task.class, mappedBy ="user")
		List<Task> tasks;
		
		public User(){
			
		}
		public User(Long userId, String name, String lastName, String email, String password, boolean status,
				LocalDateTime registrationDateTime) {
			super();
			this.userId = userId;
			this.name = name;
			this.lastName = lastName;
			this.email = email;
			this.password = password;
			this.status = status;
			this.registrationDateTime = registrationDateTime;
		}
		public Long getUserId() {
			return userId;
		}
		public void setUserId(Long userId) {
			this.userId = userId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public boolean isStatus() {
			return status;
		}
		public void setStatus(boolean status) {
			this.status = status;
		}
		public LocalDateTime getRegistrationDateTime() {
			return registrationDateTime;
		}
		public void setRegistrationDateTime(LocalDateTime registrationDateTime) {
			this.registrationDateTime = registrationDateTime;
		}
		
		
}
