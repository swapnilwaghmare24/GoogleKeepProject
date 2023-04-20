package com.bridgelabz.googlekeep.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.googlekeep.dto.LabelDto;
import com.bridgelabz.googlekeep.exception.GoogleKeepException;
import com.bridgelabz.googlekeep.model.Label;
import com.bridgelabz.googlekeep.model.Note;
import com.bridgelabz.googlekeep.model.User;
import com.bridgelabz.googlekeep.repository.LabelRepository;
import com.bridgelabz.googlekeep.repository.NoteRepository;
import com.bridgelabz.googlekeep.repository.UserRepository;
import com.bridgelabz.googlekeep.util.TokenUtil;




@Service
public class LabelService implements ILabelService {

	@Autowired
	LabelRepository labelRepository;
	@Autowired
	UserService userService;
	@Autowired
	NoteRepository noteRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	TokenUtil tokenUtil;

	@Override
	public Label createLabel(String token, LabelDto labelDto) throws GoogleKeepException {
		int id=tokenUtil.decodeToken(token);
		Optional<Note> note = noteRepository.findById(labelDto.getNoteId());
		Optional<User> user = userRepository.findById(id);
		
		if(user.isPresent()&& note.isPresent())
		{
			Label labelModel = new Label(labelDto);
			
			labelModel.setNote(note.get());
			labelModel.getNotes().add(note.get());
			labelRepository.save(labelModel);
			return labelModel;
		}
		throw new GoogleKeepException("Unable to create lable");
	}

	@Override
	public Label updateLabel(int labelId, LabelDto labelDto)throws GoogleKeepException {
		Label label=labelRepository.findById(labelId).get();
		
		if(label!=null)
		{
		label.updateLabel(labelDto);
		labelRepository.save(label);
		return label;
		}
		throw new GoogleKeepException("Label not present");
	}

	@Override
	public String deleteLabel(String token, int labelId, int noteId) {
		
		
		Label labelModel = labelRepository.findById(labelId).orElse(null);
		Optional<Note> notesModel = noteRepository.findById(noteId);
		
			if (notesModel.isPresent() && userService.isValidUser(token))
				if (labelModel != null)

				{
					labelModel.getNotes().remove(notesModel.get());
					labelRepository.deleteById(labelId);
				}

				else
					throw new GoogleKeepException("Invalid LabelID");
			else
				throw new GoogleKeepException("Invalid NoteID");
		
		return "Label deleted";
	}

	@Override
	public Label getLabelById(int labelId) throws GoogleKeepException {
		
		return labelRepository.findById(labelId).orElseThrow(()->new GoogleKeepException("Label not present"));
		
	}

	@Override
	public List<Label> getAllLabels() throws GoogleKeepException {
		List<Label> labels=labelRepository.findAll();
		if(!labels.isEmpty())
		return labels;
		throw new GoogleKeepException("Labels not present");
	}

	
	
}
