package com.bridgelabz.googlekeep.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.googlekeep.dto.NoteDto;
import com.bridgelabz.googlekeep.dto.ReminderDto;
import com.bridgelabz.googlekeep.dto.ResponseDto;
import com.bridgelabz.googlekeep.model.Note;
import com.bridgelabz.googlekeep.service.INoteService;

@RestController
@RequestMapping("/note")
public class NotesController {

	@Autowired
	INoteService service;

	@PostMapping("/create")
	public ResponseEntity<ResponseDto> createNote(@RequestBody NoteDto noteDto, @RequestHeader String token) {
		Note note = service.create(noteDto, token);
		ResponseDto responseDto = new ResponseDto("Note created", note);
		return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
	}

	@PutMapping("/update/{noteId}")
	public ResponseEntity<ResponseDto> updateNote(@RequestBody NoteDto noteDto, @RequestHeader String token,
			@PathVariable int noteId) {
		Note note = service.update(noteDto, token, noteId);
		ResponseDto responseDto = new ResponseDto("note updated", note);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{noteId}")
	public ResponseEntity<ResponseDto> deleteNote(@RequestHeader String token, @PathVariable int noteId) {
		String response = service.delete(token, noteId);
		ResponseDto responseDto = new ResponseDto("Deletion status : ", response);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}

	@GetMapping("/get/{noteId}")
	public ResponseEntity<Object> getNote(@RequestHeader String token, @PathVariable int noteId) {
		Note note = service.get(token, noteId);
		ResponseDto responseDto = new ResponseDto("Required note is", note);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);

	}

	@GetMapping("/getall")
	public ResponseEntity<ResponseDto> getNotes(@PathVariable String token) {
		List<Note> notes = service.getAllNotes(token);

		ResponseDto responseDto = new ResponseDto("All notes are", notes);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);

	}

	@PutMapping("/pin/{noteId}")
	public ResponseEntity<ResponseDto> pinNote(@RequestHeader String token, @PathVariable int noteId) {
		Note note = service.doPin(token, noteId);
		ResponseDto response = new ResponseDto("Note pin done : ", note);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping("/trash/{noteId}")
	public ResponseEntity<ResponseDto> trashNote(@RequestHeader String token, @PathVariable int noteId) {
		Note note = service.doTrash(token, noteId);
		ResponseDto response = new ResponseDto("Note trash done : ", note);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping("/archive/{noteId}")
	public ResponseEntity<ResponseDto> archiveNote(@RequestHeader String token, @PathVariable int noteId) {
		Note note = service.doArchive(token, noteId);
		ResponseDto response = new ResponseDto("Note archive done : ", note);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping("/reminder/{noteId}")
	public ResponseEntity<ResponseDto> addReminder(@RequestHeader String token, @PathVariable int noteId,
			@RequestBody ReminderDto reminderDto) {
		Note note = service.addReminder(token, noteId, reminderDto);
		ResponseDto responseDto = new ResponseDto("Reminder added successfully ", note);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);

	}

	@DeleteMapping("/reminder/{noteId}")
	public ResponseEntity<ResponseDto> deleteReminder(@RequestHeader String token, @PathVariable int noteId) {
		Note note = service.deleteReminder(token, noteId);
		ResponseDto responseDto = new ResponseDto("Remainder deleted successfully", note);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}

	/*@PostMapping("/addlabel/{token}/{noteId}/{labelId}")
	ResponseEntity<ResponseDto> addLabel(@RequestHeader String token, @PathVariable int noteId,
			@PathVariable int labelId) {
		Note note = service.addLabel(token, noteId, labelId);
		ResponseDto responseDto = new ResponseDto("Label added successfully to note ", note);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}

	@DeleteMapping("/removelabel/{token}/{noteId}/{labelId}")
	ResponseEntity<ResponseDto> removeLabel(@RequestHeader String token, @PathVariable int noteId,
			@PathVariable int labelId) {
		Note note = service.deleteLabel(token, noteId, labelId);
		ResponseDto responseDto = new ResponseDto("Label deleted successfully from note ", note);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}*/

}
