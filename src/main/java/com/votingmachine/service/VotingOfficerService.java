package com.votingmachine.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.votingmachine.dao.interfaces.mongodb.VotingOfficerMongoDatabaseService;
import com.votingmachine.models.VotingOfficer;
import com.votingmachine.security.PasswordTranslator;

@Service
public class VotingOfficerService {

	private VotingOfficerMongoDatabaseService databaseService;
	private PasswordTranslator translator;

	public VotingOfficerService(@Qualifier("Mongo") VotingOfficerMongoDatabaseService databaseService,
			PasswordTranslator translator) {
		this.databaseService = databaseService;
		this.translator = translator;
	}

	public int insertVotingOfficer(UUID id, String username, String rawPassword, String nameOfOfficer) {
		VotingOfficer officer = databaseService.findOneByUsername(username);
		if(officer!=null)
			return -1;
		else {
			officer = databaseService.findOneByVOId(id);
			if(officer!=null)
				return 0;
		}
		String hashedPassword = translator.encryptPassword(rawPassword);
		System.out.println("Hashed password: " + hashedPassword);
		VotingOfficer officerToInsert = new VotingOfficer(id, username, hashedPassword, nameOfOfficer);
		databaseService.save(officerToInsert);
		return 1;
	}
	

	public int insertVotingOfficer(VotingOfficer officer) {
		UUID id = UUID.randomUUID();
		return insertVotingOfficer(id, officer.getUsername(), officer.getHashedPassword(),
				officer.getNameOfVotingOfficer());
	}
	
	

	public List<VotingOfficer> getAllVotingOfficers() {
		return databaseService.findAll();
	}

}
