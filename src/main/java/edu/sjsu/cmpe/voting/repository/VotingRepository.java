/**
 * 
 */
package edu.sjsu.cmpe.voting.repository;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import edu.sjsu.cmpe.voting.domain.Poll;

/**
 * @author Vijaylakshmi
 * 
 */
public class VotingRepository implements VotingRepositoryInterface {

	/** In-memory map to store polls. (Key, Value) -> (PollKey, Poll) */
	private final ConcurrentHashMap<String, Poll> pollInMemoryMap;
	static Random rnd;
	// Characters used to generate unique 4 letter key.
	static final String characters = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	// Key to be generated and associated with every poll question 
	private long pollKey;

	/** Constructor to create a Voting Repository */
	public VotingRepository(ConcurrentHashMap<String, Poll> pollMap) {
		checkNotNull(pollMap, "bookMap must not be null for BookRepository");
		pollInMemoryMap = pollMap;
		pollKey = 0;

	}

	/**
	 *  Method to generate a random unique key for each poll question posted by an user.
	 */
	private final String generatePollKey() {
		int len = 4;
		rnd = new Random();
		
		// Building a key of length 4.
    	StringBuilder sb = new StringBuilder(len);
    	for(int i=0; i<len; i++)
    		sb.append(characters.charAt(rnd.nextInt(characters.length())));
    	
    	// Checking uniqueness of the Key generated
    	Poll poll = getPollbyKey(sb.toString());
    	if(poll == null)
    		return sb.toString();
    	else
    		return generatePollKey();
	}

	/**
	 *  Method to save the polls created by users.
	 */
	public Poll savePoll(Poll newPoll) {
		checkNotNull(newPoll, "Poll instance cannot be null");
		String key = generatePollKey();
		newPoll.setUniqueKey(key);
		pollInMemoryMap.putIfAbsent(key, newPoll);
		return newPoll;
	}
	
	/**
	 *  Method that returns the poll question associated with the key.
	 */
	public Poll getPollbyKey(String key){
		checkNotNull(key,"");
		return pollInMemoryMap.get(key);
	}
	
	/**
	 *  Method called when PUT operation is performed to record the answer to the poll.
	 */
	 public void updatePoll(String key, String answer){
	    	
	    	checkNotNull(key, "Key instance cannot be null");
	    	Poll poll = pollInMemoryMap.get(key);
	    	checkNotNull(poll, "Poll instance cannot be null");
	    	// Option 1 selection (Yes) Incrementing the option count by 1, everytime it is selected
	    	if(answer.equalsIgnoreCase(poll.getOption1())){
	    		int count = poll.getOption1Count();
	    		poll.setOption1Count(++count);
	    	}
	    	// Option 2 selection (No) Incrementing the option count by 1, everytime it is selected
	    	else if(answer.equalsIgnoreCase(poll.getOption2())){
	    		int count = poll.getOption2Count();
	    		System.out.println("Count="+count);
	    		poll.setOption2Count(++count);
	    	}
	    	pollInMemoryMap.put(key, poll);
	    }

}
