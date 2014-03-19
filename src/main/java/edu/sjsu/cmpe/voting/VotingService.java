package edu.sjsu.cmpe.voting;

import java.util.concurrent.ConcurrentHashMap;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

import edu.sjsu.cmpe.voting.domain.*;
import edu.sjsu.cmpe.voting.api.resources.RootResource;
import edu.sjsu.cmpe.voting.api.resources.VoteResource;
import edu.sjsu.cmpe.voting.config.VotingServiceConfiguration;
import edu.sjsu.cmpe.voting.repository.VotingRepository;
import edu.sjsu.cmpe.voting.repository.VotingRepositoryInterface;

public class VotingService extends Service<VotingServiceConfiguration> {

	public static void main(String[] args) throws Exception {
		new VotingService().run(args);
	}

	@Override
	public void initialize(Bootstrap<VotingServiceConfiguration> bootstrap) {
		bootstrap.setName("Voting-service");
	}

	@Override
	public void run(VotingServiceConfiguration configuration,
			Environment environment) throws Exception {
		/**Root API */
		environment.addResource(RootResource.class);
		/** Poll APIs */
		VotingRepositoryInterface voteRepository = new VotingRepository(
				new ConcurrentHashMap<String, Poll>());
			environment.addResource(new VoteResource(voteRepository));
	}
}
