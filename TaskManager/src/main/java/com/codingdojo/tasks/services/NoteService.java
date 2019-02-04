package com.codingdojo.tasks.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.tasks.models.Task;
import com.codingdojo.tasks.models.Note;
import com.codingdojo.tasks.repositories.NoteRepository;

@Service
public class NoteService {
	private final NoteRepository noteRepository;
	public NoteService(NoteRepository noteRepository) {
		this.noteRepository = noteRepository;
	}
	
	public List<Note> allNotes() {
		return noteRepository.findAll();
	}
	
	public List<Note> descNotes(long id) {
		return noteRepository.findByCreatedAtDesc(id);
	}
	
	public Note createNote(Note n) {
		return noteRepository.save(n);
	}
	
	public Note findNote(Long id) {
		Optional<Note> optionalNote = noteRepository.findById(id);
		if(optionalNote.isPresent()) {
			return optionalNote.get();
		} else {
			return null;
		}
	}
	
	public void addNoteToTask(Note n, Task t) {	
		n.setTask(t);
		this.noteRepository.save(n);
	}
	
	
	public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }
}
