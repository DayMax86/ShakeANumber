package com.daymax86.shakeanumber;

public class Player 
{

	public enum playerType
	{
		PLAYER_ONE, PLAYER_TWO;
	}
	
	//constructor
	public Player(String name, playerType pType)
	{
		setName(name);
		setPlayerType(pType);
	}
	
	
	public int score;	
	//getter
	public int getScore()
	{
		return score;
	}
	//setter
	public void setScore(int pScore)
	{
		score = pScore;
	}
	
		
	public String name;
	//getter
	public String getName()
	{
		return name;
	}
	//setter
	public void setName(String pName)
	{
		name = pName;
	}
	
	public playerType playerType;
	//getter
	public playerType getPlayerType()
	{
		return playerType;
	}
	//setter
	public void setPlayerType(playerType pType)
	{
		playerType = pType;
	}
	
	private int pGamesWon;
	//getter
	public int getGamesWon()
	{
		return pGamesWon;
	}
	//setter
	public void setGamesWon(int value)
	{
		pGamesWon = value;
	}
	
}
