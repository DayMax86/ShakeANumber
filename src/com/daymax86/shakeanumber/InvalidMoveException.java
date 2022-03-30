package com.daymax86.shakeanumber;

@SuppressWarnings("serial")
public class InvalidMoveException extends Exception
{

	String errorMessage;
	public InvalidMoveException(String pMessage)
	{
		errorMessage = pMessage;
	}
	
	public String getErrorMessage()
	{
		return errorMessage;
	}
	
}
