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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.googlekeep.dto.LabelDto;
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

	@PostMapping("/create/{token}")
	public ResponseEntity<ResponseDto> createNote(@RequestBody NoteDto noteDto, @PathVariable String token) {
		Note note = service.create(noteDto, token);
		ResponseDto responseDto = new ResponseDto("Note created", note);
		return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
	}

	@PutMapping("/update/{token}/{id}")
	public ResponseEntity<ResponseDto> updateNote(@RequestBody NoteDto noteDto, @PathVariable String token,
			@PathVariable int id) {
		Note note = service.update(noteDto, token, id);
		ResponseDto responseDto = new ResponseDto("note updated", note);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseDto> delete(@PathVariable int id) {
		String response = service.delete(id);
		ResponseDto responseDto = new ResponseDto("Deletion status : ", response);
		return new ResponseEntity(response, HttpStatus.OK);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Object> getNote(@PathVariable int id) {
		Note note = service.get(id);
		ResponseDto responseDto = new ResponseDto("Required note is", note);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);

	}

	@GetMapping("/getall")
	public ResponseEntity<ResponseDto> getNotes() {
		List<Note> notes = service.getAllNotes();

		ResponseDto responseDto = new ResponseDto("All notes are", notes);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);

	}

	@PutMapping("/pin/{id}")
	public ResponseEntity<ResponseDto> pinNote(@PathVariable int id) {
		Note note = service.doPin(id);
		ResponseDto response = new ResponseDto("Note pin done : ", note);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping("/trash/{id}")
	public ResponseEntity<ResponseDto> trashNote(@PathVariable int id) {
		Note note = service.doTrash(id);
		ResponseDto response = new ResponseDto("Note trash done : ", note);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping("/archive/{id}")
	public ResponseEntity<ResponseDto> archiveNote(@PathVariable int id) {
		Note note = service.doArchive(id);
		ResponseDto response = new ResponseDto("Note archive done : ", note);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping("/reminder/{id}")
	public ResponseEntity<ResponseDto> addReminder(@PathVariable int id, @RequestBody ReminderDto reminderDto) {
		Note note = service.addReminder(id, reminderDto);
		ResponseDto responseDto = new ResponseDto("Reminder added successfully ", note);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);

	}

	@DeleteMapping("/reminder/{id}")
	public ResponseEntity<ResponseDto> deleteReminder(@PathVariable int id) {
		Note note = service.deleteReminder(id);
		ResponseDto responseDto = new ResponseDto("Remainder deleted successfully", note);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}
	
	@PostMapping("/addlabel/{id}/{labelId}")
	ResponseEntity<RequestBody> addLabel(@PathVariable int id,@PathVariable int labelId)
	{
		Note note=service.addLabel(id,labelId);
		ResponseDto responseDto=new ResponseDto("Label added successfully to note ",note);
		return new ResponseEntity(responseDto,HttpStatus.OK);
	}
	
	@DeleteMapping("/removelabel/{id}/{labelId}")
	ResponseEntity<RequestBody> removeLabel(@PathVariable int id,@PathVariable int labelId)
	{
		Note note=service.deleteLabel(id,labelId);
		ResponseDto responseDto=new ResponseDto("Label deleted successfully from note ",note);
		return new ResponseEntity(responseDto,HttpStatus.OK);
	}

}
