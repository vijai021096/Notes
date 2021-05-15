package com.vj.notes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vj.notes.entity.NotesEntity;
import com.vj.notes.exception.ResourceNotFoundException;
import com.vj.notes.repository.NotesRepository;

@RestController
@RequestMapping("/api")
public class NotesController {

	    @Autowired
	    NotesRepository noteRepository;
	    
	    @GetMapping("/notes")
	    public List<NotesEntity> getAllNotes() {
	        return noteRepository.findAll();
	    }
	    
	    @PostMapping("/notes")
	    public NotesEntity createNote( @RequestBody NotesEntity note) {
	        return noteRepository.save(note);
	    }
	    
	    @GetMapping("/notes/{id}")
	    public NotesEntity getNoteById(@PathVariable(value = "id") Long noteId) {
	        return noteRepository.findById(noteId)
	                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
	    }
	    
	    @PutMapping("/notes/{id}")
	    public NotesEntity updateNote(@PathVariable(value = "id") Long noteId,
	                                             @RequestBody NotesEntity noteDetails) {

	    	NotesEntity note = noteRepository.findById(noteId)
	                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

	        note.setTitle(noteDetails.getTitle());
	        note.setContent(noteDetails.getContent());

	        NotesEntity updatedNote = noteRepository.save(note);
	        return updatedNote;
	    }
	    
	    @DeleteMapping("/notes/{id}")
	    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {
	    	NotesEntity note = noteRepository.findById(noteId)
	                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

	        noteRepository.delete(note);

	        return ResponseEntity.ok().build();
	    }

}
