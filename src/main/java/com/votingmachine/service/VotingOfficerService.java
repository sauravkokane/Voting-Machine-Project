package com.votingmachine.service;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.votingmachine.dao.mongodb.VotingOfficerRepository;
import com.votingmachine.models.VotingOfficer;
import com.votingmachine.security.PasswordTranslator;

@Service
public class VotingOfficerService {

	private VotingOfficerRepository databaseService;
	private PasswordTranslator translator;

	public VotingOfficerService(@Qualifier("Mongo") VotingOfficerRepository databaseService,
			PasswordTranslator translator) {
		this.databaseService = databaseService;
		this.translator = translator;
	}
	

	public ResponseEntity<?> insertVotingOfficer(UUID id, String username, String rawPassword, String nameOfOfficer) {
//		if(username)
		
		if(databaseService.existsByUsernameOrId(username, id)) {
			return ResponseEntity.badRequest().body("Already used username or user id, please choose another one");
		}
		
		
		
//		databaseService.save(officerToInsert);
		return null;
	}
	

	public ResponseEntity<?> insertVotingOfficer(VotingOfficer officer) {
		UUID id = UUID.randomUUID();
		return insertVotingOfficer(id, officer.getUsername(), officer.getHashedPassword(),
				officer.getNameOfVotingOfficer());
	}
	
	

	public List<VotingOfficer> getAllVotingOfficers() {
		return databaseService.findAll();
	}

}
