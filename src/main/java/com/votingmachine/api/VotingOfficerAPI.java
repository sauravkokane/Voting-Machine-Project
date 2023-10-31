package com.votingmachine.api;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.votingmachine.MyResponseProvider.ResponseProvider;
import com.votingmachine.exceptions.EmailValidationException;
import com.votingmachine.exceptions.PasswordValidationException;
import com.votingmachine.exceptions.UsernameValidationException;
import com.votingmachine.models.Check;
import com.votingmachine.models.LoginRequest;
import com.votingmachine.models.VotingOfficer;
import com.votingmachine.service.Interfaces.VotingOfficerServiceInterface;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/person")
public class VotingOfficerAPI {

	private final VotingOfficerServiceInterface voService;
	private static String SECRET_KEY = "8f74h0loerj27wuvr5phpj0xq2z9yb1o"; // System.getenv("Secret_Key_VotingSystem");

	public VotingOfficerAPI(@Qualifier("VotingOfficerService") VotingOfficerServiceInterface voService) {
		this.voService = voService;
	}

	@PostMapping
	public ResponseEntity<?> signInVotingOfficer(@Valid @RequestBody VotingOfficer officer) {
		ResponseProvider.configureResponse();
		VotingOfficer off;
		try {
			off = voService.insertVotingOfficer(officer);
		} catch (UsernameValidationException e) {
			ResponseProvider.getResponse().put("message", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseProvider.getResponse());
		} catch (EmailValidationException e) {
			ResponseProvider.getResponse().put("message", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseProvider.getResponse());
		} catch (PasswordValidationException e) {
			ResponseProvider.getResponse().put("message", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseProvider.getResponse());
		} catch (RuntimeException e) {
			ResponseProvider.getResponse().put("message", "Something went wrong in signing in");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseProvider.getResponse());
		}

		ResponseProvider.getResponse().put("message", "You have successfully registered!!!");

		HashMap<String, String> officerData = new HashMap<>();
		officerData.put("username", off.getUsername());
		officerData.put("email id", off.getEmail());
		officerData.put("id", off.getVOId().toString());
		ResponseProvider.getResponse().put("Details", officerData);
		return ResponseEntity.accepted().body(ResponseProvider.getResponse());
	}

	@PostMapping("/login")
	public ResponseEntity<?> LoginVotingOfficer(@Valid @RequestBody LoginRequest loginCredentials) {
		System.out.println(loginCredentials);
		ResponseProvider.configureResponse();
		VotingOfficer officer;
		try {
			officer = voService.getOfficerbyUsernameOrEmail(loginCredentials.getLoginIdentifier(),
					loginCredentials.getPassword());
		} catch (UsernameValidationException e) {
			ResponseProvider.getResponse().put("message", e.getMessage());
			return ResponseEntity.badRequest().body(ResponseProvider.getResponse());
		} catch (PasswordValidationException e) {
			ResponseProvider.getResponse().put("message", e.getMessage());
			return ResponseEntity.badRequest().body(ResponseProvider.getResponse());
		}
		HashMap<String, String> body = new HashMap<>();
		String loginToken = generateSessionKey(officer);
		body.put("loginToken", loginToken);
		ResponseProvider.getResponse().put("data", body);
		return ResponseEntity.accepted().body(ResponseProvider.getResponse());
	}
	
	@PostMapping("/one")
	public ResponseEntity<?> check(@RequestBody Check token){
		try {
			String output = validateSessionKey(token.getToken());
			return ResponseEntity.ok().body(output);
		}
		catch(Exception e){
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong token");
		}
	}

	@GetMapping
	public ResponseEntity<?> getAllVotingOfficers() {
		voService.getAllVotingOfficers();
		return null;
	}

	private String validateSessionKey(String jwtToken) {
		String data = "";
		Claims claims = Jwts.parser()
		        .setSigningKey(SECRET_KEY)
		        .parseClaimsJws(jwtToken)
		        .getBody();

		// Token is valid, and you can access the claims
		String subject = claims.getSubject();
		String officerData = (String) claims.get("data"); // Access your custom claim

		// Perform actions based on the token's data
		return data;
	}

	private String generateSessionKey(VotingOfficer officer) {
		String jwtToken = Jwts.builder().setSubject("user-id").claim("data", officer.toString()).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 2 * 60 * 60 * 1000))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
		return jwtToken;
	}

}
