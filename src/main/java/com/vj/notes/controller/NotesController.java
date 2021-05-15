package com.vj.notes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vj.notes.entity.NotesEntity;
import com.vj.notes.exception.ResourceNotFoundException;
import com.vj.notes.repository.NotesRepository;

@Controller
public class NotesController {

	    @Autowired
	    NotesRepository noteRepository;
	    
	    @GetMapping("/getNotes")
	    public String getAllNotes(Model model) {
	        List<NotesEntity>notes= noteRepository.findAll();
	        model.addAttribute("notes",notes);
	        return "listNotes";
	    }
	    
	    @PostMapping("/postNotes")
	    public String createNote( @ModelAttribute("notes") NotesEntity note) {
	         noteRepository.save(note);
	         return  "redirect:/getNotes";
	    }
	    
	  //Add Notes
	  		@GetMapping("/showFormForAddNotes")
	  		public String showFormForAdd(Model theModel) {
	  			
	  			// create model attribute to bind form data
	  			NotesEntity notes = new NotesEntity();	
	  			theModel.addAttribute("notes", notes);
	  			
	  			
	  			return "AddNotes";
	  		}
	  		
	    @GetMapping("/getByIdnotes/{id}")
	    public String getNoteById(@PathVariable(value = "id") Long noteId,Model theModel) {
	        NotesEntity notes= noteRepository.findById(noteId)
	                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
	        theModel.addAttribute("note",notes);
	        return "notesById";
	    }
	    
	 
		
	     @GetMapping("/updateNote/{id}")
	    public String updateNote(@PathVariable(value = "id") Long noteId,
	                                             Model theModel) {

	    	NotesEntity note = noteRepository.findById(noteId)
	                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

	       theModel.addAttribute("notes",note);
	       
	       return "AddNotes";
	    }
	    
	    @GetMapping("/deleteNote/{id}")
	    public String deleteNote(@PathVariable(value = "id") Long noteId) {
	    	NotesEntity note = noteRepository.findById(noteId)
	                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

	        noteRepository.delete(note);

	        return "redirect:/getNotes";
	    }

}
