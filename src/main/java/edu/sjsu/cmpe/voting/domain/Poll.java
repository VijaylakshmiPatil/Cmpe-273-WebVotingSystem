/**
 * 
 */
package edu.sjsu.cmpe.voting.domain;

import java.util.Random;

public class Poll {

	private String question;
    private String uniqueKey;
    private String answer;
    private int yesCount;
    private int noCount;
    
    static final String characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static Random rnd;
    
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
     * @return the Unique Key
     */
    public String getUniqueKey() {
    	return uniqueKey;
    }

    /**
     * @param Unique Key
     *            the title to set
     */
    public void setUniqueKey(String uniqueKey) {
    	this.uniqueKey = uniqueKey;
    }
      
  
    String randomString(int len)
    {
    	rnd = new Random();
    	StringBuilder sb = new StringBuilder(len);
    	for(int i=0; i<len; i++)
    		sb.append(characters.charAt(rnd.nextInt(characters.length())));
    	return sb.toString();
    }

	/**
	 * @return the answer
	 */
	public String getAnswer() {
		return answer;
	}

	/**
	 * @param answer the answer to set
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	/**
	 * @return the yesCount
	 */
	public int getYesCount() {
		return yesCount;
	}

	/**
	 * @param yesCount the yesCount to set
	 */
	public void setYesCount(int yesCount) {
		this.yesCount = yesCount;
	}

	/**
	 * @return the noCount
	 */
	public int getNoCount() {
		return noCount;
	}

	/**
	 * @param noCount the noCount to set
	 */
	public void setNoCount(int noCount) {
		this.noCount = noCount;
	}

}
