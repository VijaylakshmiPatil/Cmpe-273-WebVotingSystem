/**
 * 
 */
package edu.sjsu.cmpe.voting.domain;

import java.util.Random;

/**
 * @author Vijaylakshmi
 *
 */
public class Poll {

	private String question;
    private String uniqueKey;
    static final String characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static Random rnd;
    
/**
     * @return the title
     */
    public String getQuestion() {
    	return question;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setQuestion(String question) {
    	this.question = question;
    }

/**
     * @return the title
     */
    public String getUniqueKey() {
    	return uniqueKey;
    }

    /**
     * @param title
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


	

	


	public String uniqueKey() {
		// TODO Auto-generated method stub
		return null;
	}

}
