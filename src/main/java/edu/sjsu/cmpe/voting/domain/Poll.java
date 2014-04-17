/**
 * 
 */
package edu.sjsu.cmpe.voting.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Poll {

	//@JsonProperty("id")
	private String id;
	//@JsonProperty("question")
	private String question;
	//@JsonProperty("option1")
	private String option1;
	//@JsonProperty("option2")
	private String option2;
	
	//private String answer;
	//@JsonProperty("option1Count")
	private int option1Count;
	//@JsonProperty("option2Count")
	private int option2Count;

	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}

	/**
	 * @param question
	 *            the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}

	/**
	 * @return the yesCount
	 */
	public int getOption1Count() {
		return option1Count;
	}

	/**
	 * @param yesCount
	 *            the yesCount to set
	 */
	public void setOption1Count(int yesCount) {
		this.option1Count = yesCount;
	}

	/**
	 * @return the noCount
	 */
	public int getOption2Count() {
		return option2Count;
	}

	/**
	 * @param noCount
	 *            the noCount to set
	 */
	public void setOption2Count(int noCount) {
		this.option2Count = noCount;
	}

	
	/**
	 * @return the option1
	 */
	public String getOption1() {
		return option1;
	}

	/**
	 * @param option1
	 *            the option1 to set (Yes)
	 */
	public void setOption1(String option1) {
		this.option1 = option1;
	}

	/**
	 * @return the option2
	 */
	public String getOption2() {
		return option2;
	}

	/**
	 * @param option2
	 *            the option2 to set (No)
	 */
	public void setOption2(String option2) {
		this.option2 = option2;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

//	/**
//	 * @return the answer
//	 */
//	public String getAnswer() {
//		return answer;
//	}
//
//	/**
//	 * @param answer the answer to set
//	 */
//	public void setAnswer(String answer) {
//		this.answer = answer;
//	}

}
