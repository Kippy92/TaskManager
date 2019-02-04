package com.codingdojo.tasks.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.tasks.models.Note;
import com.codingdojo.tasks.models.Task;
import com.codingdojo.tasks.models.User;
import com.codingdojo.tasks.repositories.TaskRepository;

@Service
public class TaskService {
private final TaskRepository taskRepository;
	
	public TaskService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}
	
	public List<Task> allTasks() {
		return taskRepository.findAll();
	}
	public List<Task> taskStart(Long id){
		return taskRepository.findTasksByStartDesc(id);
	}
	public List<Task> taskComplete(Long id){
		return taskRepository.findCompletedTasksByStartDesc(id);
	}
	
	public Task createTask(Task t) {
		return taskRepository.save(t);
	}
	public Task findTask(long id) {
		Optional<Task> optionalTask = taskRepository.findById(id);
		if(optionalTask.isPresent()) {
			return optionalTask.get();
		}
		else {
			return null;
		}
	}
	
	public Task updateTask(long id, int complete) {
    	Optional<Task> optionalTask = taskRepository.findById(id); 
         if(optionalTask.isPresent()) {
        	 Task task = optionalTask.get();
        	 task.setCompleted(complete);
             return taskRepository.save(task);
         } else {
             return null;
         }
    }
	
	public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
	
	public void addTaskToUser(Task t, User u) {	
		t.setUser(u);
		this.taskRepository.save(t);
	}
}
