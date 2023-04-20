package com.bridgelabz.googlekeep.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedDate;

import com.bridgelabz.googlekeep.dto.NoteDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor

public class Note {

	@Id
	@GeneratedValue
	private int noteId;
	private String title;
	private String description;
	private String color;
	private boolean isPinned;
	private boolean isArchived;
	private boolean isTrashed;
	@CreatedDate
	private LocalDateTime createdDate = LocalDateTime.now();
	private LocalDateTime modifiedDate = LocalDateTime.now();
	private String reminder;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "note_user", joinColumns = { @JoinColumn(name = "note_id") }, inverseJoinColumns = {
			@JoinColumn(name = "user_id") })
	private Set<User> users = new HashSet<>();

	public Note(NoteDto noteDto) {
		this.updateNote(noteDto);
	}

	public void updateNote(NoteDto noteDto) {
		this.title = noteDto.getTitle();
		this.description = noteDto.getDescription();
		this.color = noteDto.getColor();

	}

	public Note(NoteDto noteDto, User user) {
		this.title = noteDto.getTitle();
		this.description = noteDto.getDescription();
		this.color = noteDto.getColor();
		this.users.add(user);

	}

}
