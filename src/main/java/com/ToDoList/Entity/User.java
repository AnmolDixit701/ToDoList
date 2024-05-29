package com.ToDoList.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "User")
public class User {
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id 
	private int SerialNo;
	
	
	@Column(name = "Email")
	private String Email;
	
	@Column(name = "firstName")
	private String firstName;
	
	@Column(name = "LastName")
	private String LastName;
	
	@Column(name = "Description")
	private String Description;
	
	

	@Column(name = "Goal")
	private String Goal;
	
	@Column(name = "Password")
	private String Password;
	
	 @OneToMany(mappedBy="id",cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	 private List<Tasks> Tasks;

	public int getSerialNo() {
		return SerialNo;
	}

	public void setSerialNo(int serialNo) {
		SerialNo = serialNo;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getGoal() {
		return Goal;
	}

	public void setGoal(String goal) {
		Goal = goal;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public List<Tasks> getTasks() {
		return Tasks;
	}

	public void setTasks(List<Tasks> tasks) {
		Tasks = tasks;
	}

	@Override
	public String toString() {
		return "User [SerialNo=" + SerialNo + ", Email=" + Email + ", firstName=" + firstName + ", LastName=" + LastName
				+ ", Description=" + Description + ", Goal=" + Goal + ", Password=" + Password + ", Tasks=" + Tasks
				+ "]";
	}

	

	

}
