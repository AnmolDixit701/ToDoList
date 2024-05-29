package com.ToDoList.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Tasks")
public class Tasks {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name = "Heading",nullable = false)
	private String Heading;
	
	@Column(name = "Description",nullable = false)
	private String Description;
	
	@Column(name = "Date",nullable = false)
	private String Date;
	
	@ManyToOne
    private User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHeading() {
		return Heading;
	}

	public void setHeading(String heading) {
		Heading = heading;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Tasks [id=" + id + ", Heading=" + Heading + ", Description=" + Description + ", Date=" + Date
				+ ", user=" + user + ", getId()=" + getId() + ", getHeading()=" + getHeading() + ", getDescription()="
				+ getDescription() + ", getDate()=" + getDate() + ", getUser()=" + getUser() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	



	
	
	
	
	
	

}
