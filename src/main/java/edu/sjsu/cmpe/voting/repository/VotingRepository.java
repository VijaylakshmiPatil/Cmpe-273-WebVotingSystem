/**
 * 
 */
package edu.sjsu.cmpe.voting.repository;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;

import edu.sjsu.cmpe.voting.domain.Options;
import edu.sjsu.cmpe.voting.domain.Poll;

public class VotingRepository implements VotingRepositoryInterface {

	/** In-memory map to store polls. (Key, Value) -> (PollKey, Poll) */
	private final ConcurrentHashMap<String, Poll> pollInMemoryMap;
	static Random rnd;
	// Characters used to generate unique 4 letter key.
	static final String characters = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	// Key to be generated and associated with every poll question
	private long pollKey;

	/** Constructor to create a Voting Repository */
	public VotingRepository() {
		// checkNotNull(pollMap, "bookMap must not be null for BookRepository");
		pollInMemoryMap = null;
		pollKey = 0;

	}

	/**
	 * Method to generate a random unique key for each poll question posted by
	 * an user.
	 */
	private final String generatePollKey() {
		int len = 4;
		rnd = new Random();

		// Building a key of length 4.
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(characters.charAt(rnd.nextInt(characters.length())));
		System.out.println("Key : " + sb);
		// Checking uniqueness of the Key generated
		Poll poll = getPollbyKey(sb.toString());
		if (poll == null)
			return sb.toString();
		else
			return generatePollKey();
	}

	/**
	 * Method to save the polls created by users.
	 */
	public Poll savePoll(Poll newPoll) {
		checkNotNull(newPoll, "Poll instance cannot be null");
		String key = generatePollKey();
		newPoll.setId(key);
		DBObject pollInMemory = new BasicDBObject("_id", newPoll.getId());
		pollInMemory.put("question", newPoll.getQuestion());
		pollInMemory.put("option1", newPoll.getOption1());
		pollInMemory.put("option2", newPoll.getOption2());
		pollInMemory.put("option1Count", newPoll.getOption1Count());
		pollInMemory.put("option2Count", newPoll.getOption2Count());
		try {
			DB db = mongoConnection();
			DBCollection poll = db.getCollection("poll");
			poll.insert(pollInMemory);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newPoll;
	}

	/**
	 * Method that returns the poll question associated with the key.
	 */
	public Poll getPollbyKey(String key) {
		checkNotNull(key, "");
		DB db;

		try {
			db = mongoConnection();
			Poll poll = new Poll();
			DBCollection pollColl = db.getCollection("poll");
			DBObject pollObj = pollColl.findOne(new BasicDBObject("_id", key));
			// System.out.println("Poll object " + pollObj);

			if (pollObj != null) {
				// System.out.println(pollObj.get("_id").toString());
				poll.setId(pollObj.get("_id").toString());
				poll.setQuestion(pollObj.get("question").toString());
				poll.setOption1(pollObj.get("option1").toString());
				poll.setOption2(pollObj.get("option2").toString());
				poll.setOption1Count((Integer) pollObj.get("option1Count"));
				poll.setOption2Count((Integer) pollObj.get("option2Count"));
				// poll1.setAnswer(pollObj.get("answer").toString());
				System.out.println("Poll " + poll);
				return poll;
			} else {
				return null;
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * Method called when PUT operation is performed to record the answer to the
	 * poll.
	 */
	public void updatePoll(String key, String answer) {

		checkNotNull(key, "Key instance cannot be null");
		DB db;
		Poll poll = new Poll();
		try {
			db = mongoConnection();
			DBCollection pollColl = db.getCollection("poll");
			DBObject pollObj = pollColl.findOne(new BasicDBObject("_id", key));

			poll.setId(pollObj.get("_id").toString());
			poll.setQuestion(pollObj.get("question").toString());
			poll.setOption1(pollObj.get("option1").toString());
			poll.setOption2(pollObj.get("option2").toString());
			poll.setOption1Count((Integer) pollObj.get("option1Count"));
			poll.setOption2Count((Integer) pollObj.get("option2Count"));
			// poll.setAnswer(pollObj.get("answer").toString());
			// checkNotNull(poll, "Poll instance cannot be null");
			// Option 1 selection (Yes) Incrementing the option count by 1,
			// everytime it is selected
			if (answer.equalsIgnoreCase(poll.getOption1())) {
				int count = poll.getOption1Count();
				poll.setOption1Count(++count);
			}
			// Option 2 selection (No) Incrementing the option count by 1,
			// everytime it is selected
			else if (answer.equalsIgnoreCase(poll.getOption2())) {
				int count = poll.getOption2Count();
				System.out.println("Count=" + count);
				poll.setOption2Count(++count);
			}
			pollColl.update(
					new BasicDBObject("_id", poll.getId()),
					new BasicDBObject("$set", new BasicDBObject("option1Count",
							poll.getOption1Count()).append("option2Count",
							poll.getOption2Count())), false, false);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public DB mongoConnection() throws UnknownHostException {
		MongoClient client = new MongoClient();
		DB db = client.getDB("votingRepository");
		return db;
	}

	public ArrayList<Poll> getAllPolls() {
		try {
			ArrayList<Poll> poll = new ArrayList<Poll>();

			DB db = mongoConnection();
			DBCollection pollColl = db.getCollection("poll");
			DBCursor cursor = pollColl.find();
			// System.out.println(cursor);
			for (int i = 0; i < cursor.size(); i++) {
				if (cursor.hasNext()) {
					DBObject pollObject = cursor.next();
					if (pollObject.get("_id").toString().equals("NHG4")) {
						Poll p = new Poll();
						p.setId(pollObject.get("_id").toString());
						p.setQuestion(pollObject.get("question").toString());
						p.setOption1(pollObject.get("option1").toString());
						p.setOption2(pollObject.get("option2").toString());
						p.setOption1Count((Integer) pollObject
								.get("option1Count"));
						p.setOption2Count((Integer) pollObject
								.get("option2Count"));
						System.out.println(p.getQuestion());
						poll.add(p);
					}

				} else {
					break;
				}
			}
			for (int j = 0; j < poll.size(); j++) {
				System.out.println(poll.get(j).getId());
			}
			return poll;

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
