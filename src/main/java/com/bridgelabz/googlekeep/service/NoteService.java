package com.bridgelabz.googlekeep.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.googlekeep.dto.NoteDto;
import com.bridgelabz.googlekeep.dto.ReminderDto;
import com.bridgelabz.googlekeep.exception.GoogleKeepException;
import com.bridgelabz.googlekeep.model.Label;
import com.bridgelabz.googlekeep.model.Note;
import com.bridgelabz.googlekeep.model.User;
import com.bridgelabz.googlekeep.repository.LabelRepository;
import com.bridgelabz.googlekeep.repository.NoteRepository;
import com.bridgelabz.googlekeep.repository.UserRepository;
import com.bridgelabz.googlekeep.util.TokenUtil;

@Service
public class NoteService implements INoteService {

	@Autowired
	TokenUtil tokenUtil;
	@Autowired
	UserRepository userRepository;
	@Autowired
	NoteRepository noteRepository;
	@Autowired
	LabelRepository labelRepository;
	@Autowired
	UserService userService;

	@Override
	public Note create(NoteDto noteDto, String token) {
		int userId = tokenUtil.decodeToken(token);
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			Note note = new Note(noteDto, user.get());

			noteRepository.save(note);

			return note;
		}
		throw new GoogleKeepException("user is invalid");
	}

	@Override
	public Note update(NoteDto noteDto, String token, int noteId) throws GoogleKeepException {
		Optional<Note> oldNote = noteRepository.findById(noteId);
		if (userService.isValidUser(token)) {
			if (oldNote.isPresent()) {
				// Note newNote=new Note(noteDto,user.get());
				// noteRepository.save(newNote);
				// return newNote;

				oldNote.get().updateNote(noteDto);
				return oldNote.get();

			}
		}

		throw new GoogleKeepException("Note is invalid");

	}

	@Override
	public String delete(String token, int noteId) throws GoogleKeepException {
		int userId=tokenUtil.decodeToken(token);
		Optional<User> user=userRepository.findById(userId);
		Optional<Note> note = noteRepository.findById(noteId);
		
		
			if(userService.isValidUser(token))
			{
			if (note.isPresent()) {
				note.get().getUsers().remove(user.get());
				
				noteRepository.deleteById(noteId);
				return "success";
			}
		}
		throw new GoogleKeepException("note id is not valid");
	}

	@Override
	public Note get(String token, int noteId) throws GoogleKeepException {
		if (userService.isValidUser(token))
			return noteRepository.findById(noteId).orElseThrow(() -> new GoogleKeepException("Invalid id"));
		return null;
	}

	@Override
	public List<Note> getAllNotes(String token) throws GoogleKeepException {
		if (userService.isValidUser(token))
			return noteRepository.findAll();
		throw new GoogleKeepException("Notes not present");
	}

	@Override
	public Note doPin(String token, int noteId) throws GoogleKeepException {
		Note note = noteRepository.findById(noteId).get();
		if (userService.isValidUser(token) && note != null) {
			note.setPinned(!note.isPinned());
			noteRepository.save(note);
			return note;
		}
		throw new GoogleKeepException("Note is not valid");

	}

	@Override
	public Note doTrash(String token, int noteId) throws GoogleKeepException {

		Note note = noteRepository.findById(noteId).get();
		if (userService.isValidUser(token) && note != null) {
			note.setTrashed(!note.isTrashed());
			noteRepository.save(note);
			return note;
		}
		throw new GoogleKeepException("Unable to trash note");
	}

	@Override
	public Note doArchive(String token, int noteId) throws GoogleKeepException {

		Note note = noteRepository.findById(noteId).get();
		if (userService.isValidUser(token) && note != null) {
			note.setArchived(!note.isArchived());
			noteRepository.save(note);
			return note;
		}
		throw new GoogleKeepException("Unable to archive note");
	}

	@Override
	public Note addReminder(String token, int noteId, ReminderDto reminderDto) throws GoogleKeepException {
		Note note = noteRepository.findById(noteId).get();
		if (userService.isValidUser(token) && note != null) {
			note.setReminder(reminderDto.getReminder());
			noteRepository.save(note);
			return note;
		}
		throw new GoogleKeepException("Unable to add remainder");
	}

	@Override
	public Note deleteReminder(String token, int noteId) throws GoogleKeepException {
		Note note = noteRepository.findById(noteId).get();
		if (userService.isValidUser(token) && note != null) {
			note.setReminder(null);
			noteRepository.save(note);
			return note;
		}
		throw new GoogleKeepException("Unable to remove remainder");
	}

	/*@Override
	public Note addLabel(String token, int noteId, int labelId) throws GoogleKeepException {
		Note note = noteRepository.findById(noteId).get();
		Label label = labelRepository.findById(labelId).get();
		if (userService.isValidUser(token) && note != null && label != null) {
			note.addLabel(label);
			noteRepository.save(note);
			return note;
		}
		throw new GoogleKeepException("Unable add label to note");
	}*/

	/*@Override
	public Note deleteLabel(String token, int noteId, int labelId) throws GoogleKeepException {
		Note note = noteRepository.findById(noteId).get();
		Label label = labelRepository.findById(labelId).get();
		if (userService.isValidUser(token) && note != null && label != null) {
			note.removeLabel(label);
			noteRepository.save(note);
			return note;
		}
		throw new GoogleKeepException("Unable to delete label from note");
	}*/

}
