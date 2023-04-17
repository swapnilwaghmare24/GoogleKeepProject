package com.bridgelabz.googlekeep.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.googlekeep.dto.LabelDto;
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

import net.bytebuddy.dynamic.scaffold.MethodRegistry.Handler.ForAbstractMethod;

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

	@Override
	public Note create(NoteDto noteDto, String token) {
		int id = tokenUtil.decodeToken(token);
		Optional<User> user = userRepository.findById(id);
		Note note = new Note(noteDto, user.get());
		noteRepository.save(note);

		return note;
	}

	@Override
	public Note update(NoteDto noteDto, String token, int id) {
		int userId = tokenUtil.decodeToken(token);
		Optional<User> user = userRepository.findById(userId);
		Note oldNote = noteRepository.findById(id).get();
		if (oldNote != null) {
			// Note newNote=new Note(noteDto,user.get());
			// noteRepository.save(newNote);
			// return newNote;

			oldNote.updateNote(noteDto);
			return oldNote;

		}
		return null;
	}

	@Override
	public String delete(int id) {
		Optional<Note> note = noteRepository.findById(id);
		if (note != null) {
			noteRepository.deleteById(id);
			return "success";
		}
		return "failed";
	}

	@Override
	public Note get(int id) {
		return noteRepository.findById(id).orElseThrow(() -> new GoogleKeepException("Invalid id"));
	}

	@Override
	public List<Note> getAllNotes() {

		return noteRepository.findAll();
	}

	@Override
	public Note doPin(int id) {
		Note note = noteRepository.findById(id).get();
		note.setPinned(!note.isPinned());
		noteRepository.save(note);
		return note;

	}

	@Override
	public Note doTrash(int id) {
		Note note = noteRepository.findById(id).get();
		note.setTrashed(!note.isTrashed());
		noteRepository.save(note);
		return note;
	}

	@Override
	public Note doArchive(int id) {
		Note note = noteRepository.findById(id).get();
		note.setArchived(!note.isArchived());
		noteRepository.save(note);
		return note;
	}

	@Override
	public Note addReminder(int id, ReminderDto reminderDto) {
		Note note=noteRepository.findById(id).get();
		if(note!=null)
		{
			note.setReminder(reminderDto.getReminder());
			noteRepository.save(note);
			return note;
		}
		return null;
	}

	@Override
	public Note deleteReminder(int id) {
		Note note=noteRepository.findById(id).get();
		if(note!=null)
		{
			note.setReminder(null);
			noteRepository.save(note);
			return note;
		}
		return note;
	}

	@Override
	public Note addLabel(int id, int labelId) {
		Note note=noteRepository.findById(id).get();
		Label label=labelRepository.findById(labelId).get();
		if(note!=null && label!=null)
		{
			note.setLabel(label.getLabelName());
			noteRepository.save(note);
			return note;
		}
		return null;
	}
	
	@Override
	public Note deleteLabel(int id, int labelId) {
		Note note=noteRepository.findById(id).get();
		Label label=labelRepository.findById(labelId).get();
		if(note!=null && label!=null)
		{
			note.setLabel(null);
			noteRepository.save(note);
			return note;
		}
		return null;
	}


	

}
