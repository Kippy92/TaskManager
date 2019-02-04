package com.codingdojo.tasks.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.codingdojo.tasks.models.Task;

public interface TaskRepository extends CrudRepository <Task, Long> {
	
	List<Task> findAll();
	
	@Query(value="SELECT * from tasks WHERE user_id = ?1 AND completed = 0 ORDER BY start_date desc", nativeQuery=true)
    List<Task> findTasksByStartDesc(long id);
	
	@Query(value="SELECT * from tasks WHERE user_id = ?1 AND completed = 1 ORDER BY start_date desc", nativeQuery=true)
    List<Task> findCompletedTasksByStartDesc(long id);
	
	Long deleteByTitleStartingWith(String search);
}
