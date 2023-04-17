package com.bridgelabz.googlekeep.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.googlekeep.dto.LabelDto;
import com.bridgelabz.googlekeep.exception.GoogleKeepException;
import com.bridgelabz.googlekeep.model.Label;
import com.bridgelabz.googlekeep.repository.LabelRepository;




@Service
public class LabelService implements ILabelService {

	@Autowired
	LabelRepository labelRepository;

	@Override
	public Label createLabel(LabelDto labelDto) {
		Label label=new Label(labelDto);
		labelRepository.save(label);
		return label;
	}

	@Override
	public Label updateLabel(int labelId, LabelDto labelDto) {
		Label label=labelRepository.findById(labelId).get();
		label.updateLabel(labelDto);
		labelRepository.save(label);
		return label;
	}

	@Override
	public String deleteLabel(int labelId) {
		labelRepository.deleteById(labelId);
		return "label deleted";
	}

	@Override
	public Label getLabelById(int labelId) {
		
		return labelRepository.findById(labelId).orElseThrow(()->new GoogleKeepException("Label not present"));
		
	}

	@Override
	public List<Label> getAllLabels() {
		List<Label> labels=labelRepository.findAll();
		return labels;
	}

	
	
}
