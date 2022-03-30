package com.daymax86.shakeanumber;

import java.util.Random;

import android.widget.ImageView;
import com.daymax86.shakeanumber.R;

public class Dice
{
	/*public enum diceNumbers
	{
		ONE,TWO,THREE,FOUR,FIVE,SIX,SEVEN,EIGHT,NINE,TEN,BLANK;
	}*/

	private int diceScore;
	//getter
	public int getDiceScore()
	{
		return diceScore;
	}
	//setter
	public void setDiceScore(int pDiceScore)
	{
		diceScore = pDiceScore;
		// Update on screen image to match new score in inherited classes
		updateImageViews(this);
	}
	
	
	public void updateImageViews(Dice dice)
	{
		if (dice.getClass() == NumberedDice.class)
		{
			switch (dice.getDiceScore())
			{
				case 0: getImageView().setBackgroundResource(R.drawable.blank_numbered_dice); break;
				case 1: getImageView().setBackgroundResource(R.drawable.one_numbered_dice); break;
				case 2: getImageView().setBackgroundResource(R.drawable.two_numbered_dice); break;
				case 3: getImageView().setBackgroundResource(R.drawable.three_numbered_dice); break;
				case 4: getImageView().setBackgroundResource(R.drawable.four_numbered_dice); break;
				case 5: getImageView().setBackgroundResource(R.drawable.five_numbered_dice); break;
				case 6: getImageView().setBackgroundResource(R.drawable.six_numbered_dice); break;
				case 7: getImageView().setBackgroundResource(R.drawable.seven_numbered_dice); break;
				case 8: getImageView().setBackgroundResource(R.drawable.eight_numbered_dice); break;
				case 9: getImageView().setBackgroundResource(R.drawable.nine_numbered_dice); break;
				case 10: getImageView().setBackgroundResource(R.drawable.ten_numbered_dice); break;
			}
		}
		else
		{
			switch(dice.getDiceScore())
			{
			case 1:dice.getImageView().setImageResource(R.drawable.one_dot_dice);break;
			case 2:dice.getImageView().setImageResource(R.drawable.two_dot_dice);break;
			case 3:dice.getImageView().setImageResource(R.drawable.three_dot_dice);break;
			case 4:dice.getImageView().setImageResource(R.drawable.four_dot_dice);break;
			case 5:dice.getImageView().setImageResource(R.drawable.five_dot_dice);break;
			case 6:dice.getImageView().setImageResource(R.drawable.six_dot_dice);break;
			}
		}
		
	}
	
	
	private ImageView imgView;
	//getter
	public ImageView getImageView()
	{
		return imgView;
	}
	//setter
	public void setImageView(ImageView pImageView)
	{
		imgView = pImageView;
	}
	
	private int diceId;
	//getter
	public int getDiceId()
	{
		return diceId;
	}
	//setter
	public void setDiceId(int pId)
	{
		diceId = pId;
	}
	
	private boolean highlighted;
	//getter
	public boolean isHighlighted()
	{
		return highlighted;
	}
	//setter
	public void setHighlight(boolean b)
	{
		highlighted = b;
	}

	protected int[] diceSquares = new int[6];

	//-------------------------------------------------\\
	protected Dice (int id, ImageView iv)
	{
		this.diceId = id;
		this.imgView = iv;
	}

	//-------------------------------------------------\\
	public int roll()
	{
		// Get random face
		Random rand = new Random();
		int face = rand.nextInt(6);
		
		// Use face's value as score
		this.setDiceScore(diceSquares[face]);

		return getDiceScore();
	}
}
