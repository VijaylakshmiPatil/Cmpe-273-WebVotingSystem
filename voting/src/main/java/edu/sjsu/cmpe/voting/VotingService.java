package edu.sjsu.cmpe.voting;


import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

import edu.sjsu.cmpe.voting.config.VotingServiceConfiguration;






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
	/** Root API */
	
	
	
    }
}

