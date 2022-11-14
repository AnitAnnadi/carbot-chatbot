/**
 * A program to carry on conversations with a human user.
 * This version:
 *<ul><li>
 * 		Uses advanced search for keywords 
 *</li><li>
 * 		Will transform statements as well as react to keywords
 *</li></ul>
 * @author Laurie White
 * @version April 2012
 *
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Magpie4
{
	boolean carGame = false;
    int question = 0;
	boolean firstResponse = true;
	List<String> randomQuestions = new ArrayList<String>(Arrays.asList("What is the best-selling car of all time?", "What country consumes the most gas every year?", "What kind of car is associated with James Bond?", "What is the most popular color for a car?", "Who designed what is considered the world’s first automobile?", "What is the van in the popular cartoon show “Scooby-Doo” named?", "What was the most stolen car in America in 2022?", "The Ultimate Driving Machine” is the slogan for which car company?", "What car was the first to be mass-produced?", "What is the world’s largest automotive company?", "What is the smallest car ever made?"));

	String [] randomQuestions2 = {"What is your favorite car?", "What car does your family own?", "Whats the best looking car you have seen?", "What color is your car?"};
	int score = 0;

	/**
	 * Get a default greeting 	
	 * @return a greeting
	 */	
	public String getGreeting()
	{
		if (introduction = true) {
			return "Hello, my name is CarBot. I love cars!";
		} else {
			return "";
		}
	}
	
	/**
	 * Gives a response to a user statement
	 * 
	 * @param statement
	 *            the user statement
	 * @return a response based on the rules given
	 */

	String category = "";
	Boolean introduction = false;
	Boolean closure = false;
	Boolean randQuestion = false;

	public String getResponse(String statement)
	{

		switch(statement)  {
			case "hello":
				introduction = true;
			case "game":
				carGame = true;
				break;
			case "bored":
				carGame = true;
				break;
			case "question":
				randQuestion = true;
				break;
			case "bye":
				closure = true;
				break;
			case "":
				carGame = true;
				break;
		}
		String response = "";
		
		if (carGame == false) {
			if (statement.length() == 0)
		{
			response = "You seem bored. Lets play a car game! I ask you trivia questions and you answer them.";
			System.out.println(response);
			carGame = true;
		}

		else if(findKeyword(statement, "favorite car") >= 0) {
			response = "My favorite car is the Toyota Highlander.";
		}
		

		else if(findKeyword(statement, "favorite car") >= 0) {
			response = "My favorite car is the Toyota Highlander.";
		}

		else if (findKeyword(statement, "blue") >= 0
				|| findKeyword(statement, "black") >= 0
				|| findKeyword(statement, "red") >= 0
				|| findKeyword(statement, "white") >= 0)
		{
			response = "I love that color on cars!";
		}

		else if (findKeyword(statement, "honda") >= 0
				|| findKeyword(statement, "audi") >= 0
				|| findKeyword(statement, "bmw") >= 0
				|| findKeyword(statement, "acura") >= 0)
		{
			response = "Any car except Toyota are the opps. I am loyal to Toyota.";
		}

		else if (findKeyword(statement, "cadillac") >= 0
				|| findKeyword(statement, "mercedes") >= 0
				|| findKeyword(statement, "lamborghini") >= 0
				|| findKeyword(statement, "ferrari") >= 0)
		{
			response = "Yah I like those cars. They are really cool.";
		}

		else if (findKeyword(statement, "no") >= 0)
		{
			response = "Why so negative?";
		}

		else if (findKeyword(statement, "no") >= 0)
		{
			response = "Why so negative?";
		}

		// Responses which require transformations
		else if (findKeyword(statement, "I want to", 0) >= 0)
		{
			response = transformIWantToStatement(statement);
		}

		else
		{
			// Look for a two word (you <something> me)
			// pattern
			int psn = findKeyword(statement, "you", 0);

			if (psn >= 0
					&& findKeyword(statement, "me", psn) >= 0)
			{
				response = transformYouMeStatement(statement);
			}
			else
			{
				response = getRandomResponse();
			}
		}
		}

		if (carGame == true) {
			int randInteger = (int)(Math.random() * randomQuestions.size());
			int index = 0;
			if (firstResponse) {
				response = randomQuestions.get(randInteger);
				firstResponse = false;
			}
			else if (randomQuestions.size() <= 1) {
				response = "Game is over. You got " + score + "/10 right good job!";
				carGame = false;
		}

			else if (findKeyword(statement, "volkswagon") >= 0
				|| findKeyword(statement, "united states") >= 0
				|| findKeyword(statement, "aston martin") >= 0
				|| findKeyword(statement, "white") >= 0
				|| findKeyword(statement, "karl benz") >= 0
				|| findKeyword(statement, "mystery machine") >= 0
				|| findKeyword(statement, "honda") >= 0
				|| findKeyword(statement, "bmw") >= 0
				|| findKeyword(statement, "ford") >= 0
				|| findKeyword(statement, "toyota") >= 0
				|| findKeyword(statement, "mercedes") >= 0)
		{
			response = "Correct! " + randomQuestions.get(randInteger);
			score += 1;
			index = randomQuestions.indexOf(randomQuestions.get(randInteger));
		    randomQuestions.remove(index);
		}
		else {
			response = "Wrong! Try this question: "  + randomQuestions.get(randInteger);
			index = randomQuestions.indexOf(randomQuestions.get(randInteger));
		    randomQuestions.remove(index);
		}
		}

		if(closure == true) {
			response = "Bye! Thanks for talking with me!";
			closure = false;
		}

		return response;
	}
	
	/**
	 * Take a statement with "I want to <something>." and transform it into 
	 * "What would it mean to <something>?"
	 * @param statement the user statement, assumed to contain "I want to"
	 * @return the transformed statement
	 */
	private String transformIWantToStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		int psn = findKeyword (statement, "I want to", 0);
		String restOfStatement = statement.substring(psn + 9).trim();
		return "What would it mean to " + restOfStatement + "?";
	}

	
	
	/**
	 * Take a statement with "you <something> me" and transform it into 
	 * "What makes you think that I <something> you?"
	 * @param statement the user statement, assumed to contain "you" followed by "me"
	 * @return the transformed statement
	 */
	private String transformYouMeStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		
		int psnOfYou = findKeyword (statement, "you", 0);
		int psnOfMe = findKeyword (statement, "me", psnOfYou + 3);
		
		String restOfStatement = statement.substring(psnOfYou + 3, psnOfMe).trim();
		return "What makes you think that I " + restOfStatement + " you?";
	}
	
	

	
	
	/**
	 * Search for one word in phrase.  The search is not case sensitive.
	 * This method will check that the given goal is not a substring of a longer string
	 * (so, for example, "I know" does not contain "no").  
	 * @param statement the string to search
	 * @param goal the string to search for
	 * @param startPos the character of the string to begin the search at
	 * @return the index of the first occurrence of goal in statement or -1 if it's not found
	 */
	private int findKeyword(String statement, String goal, int startPos)
	{
		String phrase = statement.trim();
		//  The only change to incorporate the startPos is in the line below
		int psn = phrase.toLowerCase().indexOf(goal.toLowerCase(), startPos);
		
		//  Refinement--make sure the goal isn't part of a word 
		while (psn >= 0) 
		{
			//  Find the string of length 1 before and after the word
			String before = " ", after = " "; 
			if (psn > 0)
			{
				before = phrase.substring (psn - 1, psn).toLowerCase();
			}
			if (psn + goal.length() < phrase.length())
			{
				after = phrase.substring(psn + goal.length(), psn + goal.length() + 1).toLowerCase();
			}
			
			//  If before and after aren't letters, we've found the word
			if (((before.compareTo ("a") < 0 ) || (before.compareTo("z") > 0))  //  before is not a letter
					&& ((after.compareTo ("a") < 0 ) || (after.compareTo("z") > 0)))
			{
				return psn;
			}
			
			//  The last position didn't work, so let's find the next, if there is one.
			psn = phrase.indexOf(goal.toLowerCase(), psn + 1);
			
		}
		
		return -1;
	}
	
	/**
	 * Search for one word in phrase.  The search is not case sensitive.
	 * This method will check that the given goal is not a substring of a longer string
	 * (so, for example, "I know" does not contain "no").  The search begins at the beginning of the string.  
	 * @param statement the string to search
	 * @param goal the string to search for
	 * @return the index of the first occurrence of goal in statement or -1 if it's not found
	 */
	private int findKeyword(String statement, String goal)
	{
		return findKeyword (statement, goal, 0);
	}
	


	/**
	 * Pick a default response to use if nothing else fits.
	 * @return a non-committal string
	 */
	private String getRandomResponse()
	{
		final int NUMBER_OF_RESPONSES = 4;
		double r = Math.random();
		int whichResponse = (int)(r * NUMBER_OF_RESPONSES);
		String response = "";
		
		if (randQuestion) {
			if (whichResponse == 0)
		{
			response = "Interesting. " + randomQuestions2[0];
		}
		else if (whichResponse == 1)
		{
			response = "Hmmm. " + randomQuestions2[1];
		}
		else if (whichResponse == 2)
		{
			response = "Thats cool! " + randomQuestions2[2];
		}
		else if (whichResponse == 3)
		{
			response = "You don't say." + randomQuestions2[3];
		}
		randQuestion = false;
		}

		return response;
	}

}

