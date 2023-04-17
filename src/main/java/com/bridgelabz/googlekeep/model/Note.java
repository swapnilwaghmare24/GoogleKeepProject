package com.bridgelabz.googlekeep.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
	    private int id;
	    private String title;
	    private String description;
	    private String color;
	    private boolean isPinned;
	    private boolean isArchived;
	    private boolean isTrashed;
	    @CreatedDate
	    private LocalDateTime createdDate=LocalDateTime.now();
	    private LocalDateTime modifiedDate=LocalDateTime.now();
	    private String reminder;
	    @ManyToOne
	    private User user;
	    String label;
	    
	    public Note(NoteDto noteDto)
	    {
	    	this.updateNote(noteDto);
	    }
	    
	    public void updateNote(NoteDto noteDto) {
	    	this.title=noteDto.getTitle();
	    	this.description=noteDto.getDescription();
	    	this.color=noteDto.getColor();
	    	
			
		}

		public Note(NoteDto noteDto,User user)
	    {
	    	this.title=noteDto.getTitle();
	    	this.description=noteDto.getDescription();
	    	this.color=noteDto.getColor();
	    	this.user=user;
	    }

}
