package com.bridgelabz.googlekeep.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.googlekeep.dto.LabelDto;
import com.bridgelabz.googlekeep.dto.ResponseDto;
import com.bridgelabz.googlekeep.model.Label;
import com.bridgelabz.googlekeep.service.ILabelService;

@RestController
@RequestMapping("/label")
public class LabelController {
	@Autowired
	ILabelService service;

	@PostMapping("/addlabel")
	public ResponseEntity<ResponseDto> createLabel(@RequestHeader String token, @RequestBody LabelDto labelDto) {
		Label label = service.createLabel(token,labelDto);
		ResponseDto responseDto = new ResponseDto("Label created successfully", label);
		return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
	}
	
	

	@PutMapping("/{labelId}")
	public ResponseEntity<ResponseDto> updateLabel(@PathVariable int labelId, @RequestBody LabelDto labelDto) {
		Label label = service.updateLabel(labelId, labelDto);
		ResponseDto responseDto = new ResponseDto("Label updated successfully", label);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}

	@DeleteMapping("/{labelId}/{noteId}")
	public ResponseEntity<ResponseDto> deleteLabel(@RequestHeader String token, @PathVariable int labelId, @PathVariable int noteId) {
		String label = service.deleteLabel(token, labelId, noteId);
		ResponseDto responseDto = new ResponseDto("Label deleted successfully ", label);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}
	
	@GetMapping("/id/{labelId}")
	public ResponseEntity<ResponseDto> getLabelById(@PathVariable int labelId)
	{
		Label label=service.getLabelById(labelId);
		ResponseDto responseDto=new ResponseDto("Required label is ",label);
		return new ResponseEntity<>(responseDto,HttpStatus.OK);
		
	}
	
	@GetMapping("/labels")
	public ResponseEntity<ResponseDto> getAllLabels()
	{
		List<Label> label=service.getAllLabels();
		ResponseDto responseDto=new ResponseDto("Required label is ",label);
		return new ResponseEntity<>(responseDto,HttpStatus.OK);
		
	}

}
