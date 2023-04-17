package com.bridgelabz.googlekeep.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
	private LocalDateTime createdDate=LocalDateTime.now();
	
	public Label(LabelDto labelDto)
	{
		this.updateLabel(labelDto);
	}
	public void updateLabel(LabelDto labelDto)
	{
		this.labelName=labelDto.getLabelName();
	}
}
