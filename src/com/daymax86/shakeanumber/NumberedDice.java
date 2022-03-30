package com.daymax86.shakeanumber;

import android.widget.ImageView;
import com.daymax86.shakeanumber.R;

public class NumberedDice extends Dice
{
	//-------------------Properties--------------------\\
	
	//-------------------------------------------------\\
	public NumberedDice(int one,int two,int three,int four, int five, int six, int id, ImageView iv)
	{
		super (id, iv);

		this.diceSquares[0] = one;
		this.diceSquares[1] = two;
		this.diceSquares[2] = three;
		this.diceSquares[3] = four;
		this.diceSquares[4] = five;
		this.diceSquares[5] = six;
	}
	
	//-------------------------------------------------\\
	@Override
	public void setDiceScore(int pDiceScore)
	{
		super.setDiceScore(pDiceScore);
		
		// Update on screen image to match new score
		switch (getDiceScore())
		{
			case 0: getImageView().setBackgroundResource(R.drawable.blank_numbered_dice); getImageView().setTag(this); break;
			case 1: getImageView().setBackgroundResource(R.drawable.one_numbered_dice); getImageView().setTag(this); break;
			case 2: getImageView().setBackgroundResource(R.drawable.two_numbered_dice); getImageView().setTag(this); break;
			case 3: getImageView().setBackgroundResource(R.drawable.three_numbered_dice); getImageView().setTag(this); break;
			case 4: getImageView().setBackgroundResource(R.drawable.four_numbered_dice); getImageView().setTag(this); break;
			case 5: getImageView().setBackgroundResource(R.drawable.five_numbered_dice); getImageView().setTag(this); break;
			case 6: getImageView().setBackgroundResource(R.drawable.six_numbered_dice); getImageView().setTag(this); break;
			case 7: getImageView().setBackgroundResource(R.drawable.seven_numbered_dice); getImageView().setTag(this); break;
			case 8: getImageView().setBackgroundResource(R.drawable.eight_numbered_dice); getImageView().setTag(this); break;
			case 9: getImageView().setBackgroundResource(R.drawable.nine_numbered_dice); getImageView().setTag(this); break;
			case 10: getImageView().setBackgroundResource(R.drawable.ten_numbered_dice); getImageView().setTag(this); break;
		}
	}
}
