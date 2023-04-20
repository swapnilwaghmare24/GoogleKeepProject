package com.bridgelabz.googlekeep.service;

import java.util.List;

import com.bridgelabz.googlekeep.dto.NoteDto;
import com.bridgelabz.googlekeep.dto.ReminderDto;
import com.bridgelabz.googlekeep.model.Note;

public interface INoteService {

	public Note create(NoteDto noteDto, String token);

	public Note update(NoteDto noteDTO, String token, int noteId);

	public String delete(String token,int noteId);

	public Note get(String token,int noteId);

	public List<Note> getAllNotes(String token);

	public Note doPin(String token,int noteId);

	public Note doTrash(String token,int noteId);

	public Note doArchive(String token,int noteId);

	public Note addReminder(String token,int noteId, ReminderDto reminderDto);

	public Note deleteReminder(String token,int noteId);

	//public Note addLabel(String token,int noteId, int labelId);

	//public Note deleteLabel(String token,int noteId, int labelId);

}
