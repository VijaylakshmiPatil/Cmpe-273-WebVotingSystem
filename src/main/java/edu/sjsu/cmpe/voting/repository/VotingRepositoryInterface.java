package edu.sjsu.cmpe.voting.repository;

import edu.sjsu.cmpe.voting.domain.Poll;

public interface VotingRepositoryInterface {

	Poll savePoll(Poll newPoll);

	Poll getPollbyKey(String key);
	
	void updatePoll(String key, String answer);
}