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

import com.bridgelabz.googlekeep.dto.LabelDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Label {
	@Id
	@GeneratedValue
	private int labelId;
	private String labelName;
	private LocalDateTime createdDate = LocalDateTime.now();
	@ManyToOne
	@JoinColumn(name = "note_id")
	private Note note;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "note_label", joinColumns = { @JoinColumn(name = "label_id") }, inverseJoinColumns = {
			@JoinColumn(name = "note_id") })
	private Set<Note> notes = new HashSet<>();

	public Label(LabelDto labelDto) {
		this.updateLabel(labelDto);
	}

	public void updateLabel(LabelDto labelDto) {
		this.labelName = labelDto.getLabelName();
		
	}
}
