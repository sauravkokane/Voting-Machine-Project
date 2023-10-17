package com.votingmachine.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<?> insertVotingOfficer(@Valid @RequestBody VotingOfficer officer) {
		return voService.insertVotingOfficer(officer);
//		int result = voService.insertVotingOfficer(officer);
//		if (result == -1)
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//					.body(resProvider.getError("Already used username, please choose another one", HttpStatus.BAD_REQUEST));
//		else if (result == 0)
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//					.body(resProvider.getError("Already used id, please choose another one", HttpStatus.BAD_REQUEST));
//		return ResponseEntity.ok(resProvider.getOKResponse("Voting officer has registered successfully", HttpStatus.OK));
	}

	@GetMapping
	public List<VotingOfficer> getAllVotingOfficers() {
		return voService.getAllVotingOfficers();
	}
}
