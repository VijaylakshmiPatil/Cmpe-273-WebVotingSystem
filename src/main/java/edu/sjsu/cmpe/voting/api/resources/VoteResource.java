package edu.sjsu.cmpe.voting.api.resources;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.yammer.dropwizard.jersey.params.LongParam;
import com.yammer.metrics.annotation.Timed;




//import edu.sjsu.cmpe.voting.domain;
import edu.sjsu.cmpe.voting.dto.LinkDto;
import edu.sjsu.cmpe.voting.dto.LinksDto;
import edu.sjsu.cmpe.voting.domain.Poll;
import edu.sjsu.cmpe.voting.repository.VotingRepositoryInterface;

@Path("/v1/poll")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class VoteResource {
	private final VotingRepositoryInterface voteRepository;

	public VoteResource(VotingRepositoryInterface voteRepository) {
		this.voteRepository = voteRepository;
	    }
	@POST
    @Timed(name = "create-poll")
    
    
    public Response createPoll(Poll request)
    {
	// Store the new book in the BookRepository so that we can retrieve it.
	Poll savedVote = voteRepository.saveVote(request);

	String location = "/poll/" + savedVote.uniqueKey();
	LinksDto voteResponse = new LinksDto();
	voteResponse.addLink(new LinkDto("view-poll", location, "GET"));
	voteResponse.addLink(new LinkDto("update-poll", location, "PUT"));
	
	voteResponse.addLink(new LinkDto("create-poll", location, "POST"));
	// Add other links if needed

	return Response.status(201).entity(voteResponse).build();
    }
    
	    
}
