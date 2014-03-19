package edu.sjsu.cmpe.voting.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.sjsu.cmpe.voting.domain.PollDetails;

@JsonPropertyOrder({"poll","links"})
public class PollDto extends LinksDto {
	
	private PollDetails poll;

	public PollDto(PollDetails poll) {
		super();
		this.poll = poll;
	    }
	/**
	 * @return the poll
	 */
	public PollDetails getPoll() {
		return poll;
	}

	/**
	 * @param poll the poll to set
	 */
	public void setPoll(PollDetails poll) {
		this.poll = poll;
	}
	
	

}
