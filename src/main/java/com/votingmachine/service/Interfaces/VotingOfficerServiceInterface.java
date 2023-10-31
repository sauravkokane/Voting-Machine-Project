package com.votingmachine.service.Interfaces;

import java.util.List;


import org.springframework.http.ResponseEntity;


import com.votingmachine.models.VotingOfficer;



public interface VotingOfficerServiceInterface {

	VotingOfficer insertVotingOfficer(VotingOfficer officer);

	List<VotingOfficer> getAllVotingOfficers();

	VotingOfficer getOfficerbyUsernameOrEmail(String usernameOrEmail, String password);

}
