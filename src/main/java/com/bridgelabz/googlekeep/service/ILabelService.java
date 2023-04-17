package com.bridgelabz.googlekeep.service;

import java.util.List;

import com.bridgelabz.googlekeep.dto.LabelDto;
import com.bridgelabz.googlekeep.model.Label;



public interface ILabelService {

	Label createLabel(LabelDto labelDto);

	Label updateLabel(int labelId, LabelDto labelDto);

	String deleteLabel(int labelId);

	 Label getLabelById(int labelId);

	List<Label> getAllLabels();

	

}
