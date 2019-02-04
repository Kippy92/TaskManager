package com.codingdojo.tasks.models;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.mindrot.jbcrypt.BCrypt;

import com.codingdojo.tasks.models.Task;

@Entity
@Table(name="users")
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String firstname;
	
	private String lastname;
	
	private String email;
	
	private String password;
	
	@OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private List<Task> tasks;
	
	@Column(updatable=false)
	private Date created_at;
	
	private Date updated_at;
	
	public User () {
	}
	
	public User (Map<String, String> body) {
		this.firstname = body.get("firstname");
		this.lastname = body.get("lastname");
		this.email = body.get("email");
		this.password = BCrypt.hashpw(body.get("password"), BCrypt.gensalt());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
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
	
	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	
	
	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	@PrePersist
    protected void onCreate(){
        this.created_at = new Date();
    }
    
    @PreUpdate
    protected void onUpdate(){
        this.updated_at = new Date();
    }
	
}
