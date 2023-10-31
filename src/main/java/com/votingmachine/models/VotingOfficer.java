package com.votingmachine.models;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

@Document(collection = "Voting Officers")
public class VotingOfficer {
	@Id
	private final UUID VOId;
	
	
	@NotBlank
	private String email;
	
	@NotBlank
	@Indexed(unique = true)
	private final String username;
	
	@NotBlank
	private String hashedPassword;
	
	@NotBlank
	private String nameOfVotingOfficer;
	
	private String regionOfPosting;

	public VotingOfficer(@JsonProperty("id") UUID VOId, @JsonProperty("email") String email, @JsonProperty("username") String username,
			@JsonProperty("password") String hashedPassword, @JsonProperty("name") String nameOfVotingOfficer) {
//		System.out.println("Try for creating person: \n" + "Voting Officer Details:\n\tID: " + VOId + ",\n\tusername: " + username + ",\n\tName of Officer: "
//				+ nameOfVotingOfficer);
		this.VOId = VOId;
		this.email = email;
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

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
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
		return "VotingOfficer [VOId=" + VOId + ", username=" + username + ", nameOfVotingOfficer=" + nameOfVotingOfficer
				+ "]";
	}

	
}
