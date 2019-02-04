package com.codingdojo.tasks.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.codingdojo.tasks.models.Note;


public interface NoteRepository extends CrudRepository <Note, Long> {
	List<Note> findAll();
	@Query(value="SELECT * from notes WHERE task_id = ?1 ORDER BY created_at desc", nativeQuery=true)
    List<Note> findByCreatedAtDesc(long id);
}
