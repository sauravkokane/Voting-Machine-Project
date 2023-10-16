package com.votingmachine.dao.interfaces.mongodb;

import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.votingmachine.dao.interfaces.VotingOfficerDatabase;
import com.votingmachine.models.VotingOfficer;

@Repository("Mongo")
public interface VotingOfficerMongoDatabaseService extends MongoRepository<VotingOfficer, UUID>, VotingOfficerDatabase{
	VotingOfficer findOneByUsername(String username);
	VotingOfficer findOneByVOId(UUID id);
	
}


