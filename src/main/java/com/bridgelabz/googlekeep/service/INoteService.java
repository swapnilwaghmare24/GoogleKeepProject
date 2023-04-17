package com.bridgelabz.googlekeep.service;

import java.util.List;

import com.bridgelabz.googlekeep.dto.LabelDto;
import com.bridgelabz.googlekeep.dto.NoteDto;
import com.bridgelabz.googlekeep.dto.ReminderDto;
import com.bridgelabz.googlekeep.model.Note;

public interface INoteService {

	public Note create(NoteDto noteDto, String token);

	public Note update(NoteDto noteDTO, String token, int id);

	public String delete(int id);

	public Note get(int id);

	public List<Note> getAllNotes();

	public Note doPin(int id);

	public Note doTrash(int id);

	public Note doArchive(int id);

	public Note addReminder(int id, ReminderDto reminderDto);

	public Note deleteReminder(int id);

	public Note addLabel(int id, int labelId);

	public Note deleteLabel(int id, int labelId);

}
