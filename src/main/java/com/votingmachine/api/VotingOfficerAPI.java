package com.votingmachine.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.votingmachine.models.VotingOfficer;
import com.votingmachine.service.VotingOfficerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/person")
public class VotingOfficerAPI {
	
	private final VotingOfficerService voService;
	
	public VotingOfficerAPI(VotingOfficerService voService) {
		this.voService = voService;
	}
	
	@PostMapping
	public ResponseEntity<String> insertVotingOfficer(@NonNull @Valid @RequestBody VotingOfficer officer) {
		int result = voService.insertVotingOfficer(officer);
		if(result==-1)
			return ResponseEntity
	                .status(HttpStatus.BAD_REQUEST)
	                .body("Already used username, please choose another one");
		return ResponseEntity.ok("Voting officer has registered successfully");
	}
	
	@GetMapping
	public List<VotingOfficer> getAllVotingOfficers(){
		return voService.getAllVotingOfficers();
	}
}
