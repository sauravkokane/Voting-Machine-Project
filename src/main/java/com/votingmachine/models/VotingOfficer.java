package com.votingmachine.models;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.votingmachine.validators.password.ValidPassword;
import com.votingmachine.validators.username.ValidUsername;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Document(collection = "Voting Officers")
public class VotingOfficer {
	@Id
	private final UUID VOId;
	
	@NotBlank
	@ValidUsername
	@Indexed(unique = true)
	@Size(min = 5, message = "Username must be at least 5 characters long")
	private final String username;
	
	@NotBlank
	@ValidPassword
	@Size(min = 8, message = "Password must be at least 5 characters long")
	private String hashedPassword;
	
	@NotBlank
	private String nameOfVotingOfficer;
	
	private String regionOfPosting;

	public VotingOfficer(@JsonProperty("VOId") UUID VOId, @JsonProperty("username") String username,
			@JsonProperty("password") String hashedPassword, @JsonProperty("name") String nameOfVotingOfficer) {
		this.VOId = VOId;
		this.username = username;
		this.hashedPassword = hashedPassword;
		this.nameOfVotingOfficer = nameOfVotingOfficer;
	}

	public UUID getVOId() {
		return VOId;
	}

	public String getUsername() {
		return username;
	}

	public String getNameOfVotingOfficer() {
		return nameOfVotingOfficer;
	}

	public String getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	public String getRegionOfPosting() {
		return regionOfPosting;
	}

	public void setRegionOfPosting(String regionOfPosting) {
		this.regionOfPosting = regionOfPosting;
	}

	@Override
	public String toString() {
		return "Voting Officer Details:\n\tID: " + VOId + ",\n\tusername: " + username + ",\n\tName of Officer: "
				+ nameOfVotingOfficer;
	}
}
