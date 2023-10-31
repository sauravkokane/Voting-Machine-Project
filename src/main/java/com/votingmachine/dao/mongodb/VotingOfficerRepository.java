package com.votingmachine.dao.mongodb;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.votingmachine.dao.VotingOfficerDatabase;
import com.votingmachine.models.VotingOfficer;

@Repository("Mongo")
public interface VotingOfficerRepository extends MongoRepository<VotingOfficer, UUID>, VotingOfficerDatabase{
	
	
//	boolean existByUsername(String username);
	VotingOfficer findOneByUsername(String username);
	VotingOfficer findOneByVOId(UUID id);
	VotingOfficer findOneByEmail(String email);
	
//	@Query("{'$or' : [{'username' : ?0}, {'id' : ?1}]}")
//    boolean existsByUsernameOrId(@Param("username") String username, @Param("id") UUID id);
	
	@Query("{'$or' : [{'username' : ?0}, {'email' : ?1}]}")
    VotingOfficer findOneByUsernameOrEmail(@Param("username") String username, @Param("email") String email);
}


