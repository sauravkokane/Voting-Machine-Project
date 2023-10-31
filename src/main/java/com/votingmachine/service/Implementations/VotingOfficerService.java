package com.votingmachine.service.Implementations;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.votingmachine.MyResponseProvider.ResponseProvider;
import com.votingmachine.dao.mongodb.VotingOfficerRepository;
import com.votingmachine.exceptions.EmailValidationException;
import com.votingmachine.exceptions.PasswordValidationException;
import com.votingmachine.exceptions.UsernameValidationException;
import com.votingmachine.models.VotingOfficer;
import com.votingmachine.security.PasswordTranslator;
import com.votingmachine.service.Interfaces.VotingOfficerServiceInterface;

@Service
@Qualifier("VotingOfficerService")
public class VotingOfficerService implements VotingOfficerServiceInterface {

	private VotingOfficerRepository databaseService;
	private PasswordTranslator translator;

	public VotingOfficerService(@Qualifier("Mongo") VotingOfficerRepository databaseService,
			PasswordTranslator translator) {
		this.databaseService = databaseService;
		this.translator = translator;
	}

	public VotingOfficer insertVotingOfficer(UUID id, String email, String username, String rawPassword, String nameOfOfficer) throws RuntimeException{
//		if(username)
		System.out.println("insertVoting officer");
//		System.out.println("Exists or not exists: " + databaseService.ByUsername(username));
		if (databaseService.findOneByUsername(username) != null) {
			throw new UsernameValidationException("Username is already used!!! Choose another unique username!!!");
		}
		if (databaseService.findOneByEmail(email)!=null) {
			throw new EmailValidationException("You already have an account with this email id!!! Proceed to login.");
		}
		if (!isValidUsername(username)) {
			throw new UsernameValidationException("Username is not valid!!! "
					+ "username should be 5-24 alphanumeric characters long. It should not have any special characters.");
		}
		if (!isValidPassword(rawPassword)) {
			throw new PasswordValidationException(
					"Password should be at least 8 characters long. It should be combination of upper and lowercase letters. "
					+ "It should have at least one special character and two digits");
		}
		if(!isValidEmailId(email)) {
			throw new EmailValidationException("Please Enter valid email id!!!");
		}
		
		String hashedPassword = translator.encryptPassword(rawPassword);
		
		return databaseService.save(new VotingOfficer(id, email, username, hashedPassword, nameOfOfficer));
	}

	@Override
	public VotingOfficer insertVotingOfficer(VotingOfficer officer) {
		System.out.println("insertVoting officer");
		UUID id = UUID.randomUUID();
		return insertVotingOfficer(id, officer.getEmail(), officer.getUsername(), officer.getHashedPassword(),
				officer.getNameOfVotingOfficer());
	}
	
	@Override
	public VotingOfficer getOfficerbyUsernameOrEmail(String usernameOrEmail, String password) {
		VotingOfficer officer = null;
		
//		check officer having username provided is present 
		officer = databaseService.findOneByUsername(usernameOrEmail);
		System.out.println("officer1: " +  officer);
		if(officer==null) {
			officer = databaseService.findOneByEmail(usernameOrEmail);
			System.out.println("officer2: " +  officer);
		}
		if(officer==null) {
			throw new UsernameValidationException("Wrong username or email or password username!!!");
		}
		String encrypted_password = officer.getHashedPassword();
		if(!translator.checkPassword(password, encrypted_password))
			throw new PasswordValidationException("Wrong username or email or password password!!!");
		return officer;
	}

	private boolean isValidUsername(String username) {
		return username.matches("^[a-zA-Z0-9_]{5,24}$");
	}
	
	private boolean isValidPassword(String password) {
		return password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*])(?=.*[0-9].*[0-9]).{8,}$");
	}
	
	private boolean isValidEmailId(String emailId) {
		return emailId.matches("^[A-Za-z0-9+_.-]+@(.+)$");
	}

	public List<VotingOfficer> getAllVotingOfficers() {
		return databaseService.findAll();
	}

}
