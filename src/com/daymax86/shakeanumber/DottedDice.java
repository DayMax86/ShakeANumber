package com.daymax86.shakeanumber;

import android.graphics.Picture;
import android.widget.ImageView;

public class DottedDice extends Dice
{
	//-------------------Properties--------------------\\
	
	public Picture dicePicture;
	//getter
	public Picture getDicePicture()
	{
		return dicePicture;
	}
	//setter
	public void setDicePicture(Picture pDicePicture)
	{
		dicePicture = pDicePicture;
	}
	
	//-------------------------------------------------\\
	
	//constructor
	public DottedDice(int id, ImageView iv)
	{
		super (id, iv);
		
		this.diceSquares[0] = 1;
		this.diceSquares[1] = 2;
		this.diceSquares[2] = 3;
		this.diceSquares[3] = 4;
		this.diceSquares[4] = 5;
		this.diceSquares[5] = 6;
	}
}
