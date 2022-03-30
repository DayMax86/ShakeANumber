package com.daymax86.shakeanumber;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daymax86.shakeanumber.R;
import com.daymax86.shakeanumber.Player.playerType;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		//Start menu activity
		Intent intent = new Intent(this,MenuActivity.class);
		intent.putExtra("in_progress", false);
		startActivityForResult(intent, MENU_ACTIVITY);
	}
	
	/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	*/
	
	@Override
	 public void onConfigurationChanged(Configuration config)
	 {
	  super.onConfigurationChanged(config);
	 }
	
	@Override
	public void onBackPressed()
	{
		Intent intent = new Intent(this,MenuActivity.class);
		intent.putExtra("in_progress", true);
		startActivityForResult(intent, MENU_ACTIVITY);
	}
	
	public final static int NAME_ACTIVITY = 1;
	private final int MENU_ACTIVITY = 2;
	public final static int MAIN_ACTIVITY = 3;
	public final static int TUTORIAL_ACTIVITY = 4;
	public final static int RESULT_IN_PROGRESS = 1000;
	
	public void newGame(View view)
	{
		//the highlight needs to be removed from the dice
		ImageView view1 = (ImageView) findViewById(R.id.num_dice_image_one); removeHighlight(view1);
		ImageView view2 = (ImageView) findViewById(R.id.num_dice_image_two); removeHighlight(view2);
		ImageView view3 = (ImageView) findViewById(R.id.num_dice_image_three); removeHighlight(view3);
		ImageView view4 = (ImageView) findViewById(R.id.num_dice_image_four); removeHighlight(view4);
		ImageView view5 = (ImageView) findViewById(R.id.num_dice_image_five); removeHighlight(view5);
		ImageView view6 = (ImageView) findViewById(R.id.num_dice_image_six); removeHighlight(view6);
		ImageView view7 = (ImageView) findViewById(R.id.num_dice_image_seven); removeHighlight(view7);
		ImageView view8 = (ImageView) findViewById(R.id.num_dice_image_eight); removeHighlight(view8);
		ImageView view9 = (ImageView) findViewById(R.id.num_dice_image_nine); removeHighlight(view9);
		ImageView view10 = (ImageView) findViewById(R.id.num_dice_image_ten); removeHighlight(view10);
		selectedTotal = 0;
		alRemoved.clear();
		alLeft.clear();
		alSelected.clear();
		playerOne.setGamesWon(0);
		playerTwo.setGamesWon(0);
		// Start secondary activity
		Intent intent = new Intent(this,SecondaryActivity.class);
		startActivityForResult(intent, NAME_ACTIVITY);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (resultCode == RESULT_CANCELED || resultCode == RESULT_IN_PROGRESS)
			return;
		
		Log.d("maxtest", String.format ("request=%d, result=%d", requestCode, resultCode));
		Bundle extras = data.getExtras();
		String[] names = extras.getStringArray("names");
		String p1name = "";
		String p2name = "";
		
		if (names[0].isEmpty())
			p1name = "Player One";
		else
			p1name = names[0];
		
		if (names[1].isEmpty())
			p2name = "Player Two";
		else
			p2name = names[1];
		
		playerOne = new Player(p1name,playerType.PLAYER_ONE);
		playerTwo = new Player(p2name,playerType.PLAYER_TWO);
		
		completeNewGame();
   	}

	private void completeNewGame()
	{
		alAllDice.clear();
		alLeft.clear();
		//playerOne = new Player(playerOneName,playerType.PLAYER_ONE);
		//playerTwo = new Player(playerTwoName,playerType.PLAYER_TWO);
		currentPlayer = playerOne;
		playerOne.setScore(100);
		playerTwo.setScore(100);
		
		alAllDice.add(new NumberedDice(1,9,0,3,7,5,1, (ImageView) findViewById(R.id.num_dice_image_one)));
		alAllDice.add(new NumberedDice(6,0,4,8,10,2,2, (ImageView) findViewById(R.id.num_dice_image_two)));
		alAllDice.add(new NumberedDice(7,0,5,1,3,9,3, (ImageView) findViewById(R.id.num_dice_image_three)));
		alAllDice.add(new NumberedDice(0,7,1,5,3,9,4, (ImageView) findViewById(R.id.num_dice_image_four)));
		alAllDice.add(new NumberedDice(4,0,6,8,2,10,5, (ImageView) findViewById(R.id.num_dice_image_five)));
		alAllDice.add(new NumberedDice(0,6,8,4,2,10,6, (ImageView) findViewById(R.id.num_dice_image_six)));
		alAllDice.add(new NumberedDice(0,3,1,9,7,5,7, (ImageView) findViewById(R.id.num_dice_image_seven)));
		alAllDice.add(new NumberedDice(2,0,10,8,6,4,8, (ImageView) findViewById(R.id.num_dice_image_eight)));
		alAllDice.add(new NumberedDice(10,4,2,6,8,0,9, (ImageView) findViewById(R.id.num_dice_image_nine)));
		alAllDice.add(new NumberedDice(7,0,5,1,3,9,10, (ImageView) findViewById(R.id.num_dice_image_ten)));
		
		onPressDottedDiceButton(null);
		onPressNumberedDiceButton(null);
		for (NumberedDice dice: alAllDice)
		{
			dice.getImageView().setVisibility(View.VISIBLE);
		}
		refreshLabels();
	}
	
	public ArrayList<NumberedDice> alAllDice = new ArrayList<NumberedDice>(10);
	
	public int dot1;
	public int dot2;
	public DottedDice spotDice1;
	public DottedDice spotDice2;
	public int dottedDiceTotal;
	
	public void onPressDottedDiceButton (View view)	
	{
		Log.d("maxtest", "Dice button was pressed");
	    spotDice1 = new DottedDice(1, (ImageView) findViewById(R.id.dot_dice_image_one));
		spotDice2 = new DottedDice(2, (ImageView) findViewById(R.id.dot_dice_image_two));
		dot1 = spotDice1.roll();
		dot2 = spotDice2.roll();
		dottedDiceTotal = (dot1 + dot2);
		refreshLabels();
	}
	
	public void onPressNumberedDiceButton (View view)
	{
		for (NumberedDice nd: alAllDice)
		{
			nd.roll();
			nd.updateImageViews(nd);
			//changeNumberedDiceImage(nd,view);
			Log.d("maxtest", String.format("Numbered dice roll: %d", nd.getDiceScore()));
		}
	}
		
	public Player currentPlayer;
	public Player playerOne;
	public Player playerTwo;
	
	public void changeTurn()
	{
		if (currentPlayer == playerOne)
		{
			playerOne.score += calculateScore();
			if (checkWinner())
			{
				String message = "Well done " + currentPlayer.getName() + " - you won!";
				Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
				currentPlayer.setGamesWon(currentPlayer.getGamesWon()+1);
				completeNewGame();	
			}
			if (checkLoser())
			{
				String message = "unlucky " + currentPlayer.getName() + " - you lost!";
				Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
				if (currentPlayer == playerOne)
					playerTwo.setGamesWon(playerTwo.getGamesWon()+1);
				else 
					playerOne.setGamesWon(playerOne.getGamesWon()+1);
				completeNewGame();
			}
			currentPlayer = playerTwo;
		}
		else
		{
			playerTwo.score += calculateScore();
			if (checkWinner())
			{
				String message = "Well done " + currentPlayer.getName() + " - you won!";
				Toast.makeText(this, message, Toast.LENGTH_LONG).show();
				currentPlayer.setGamesWon(currentPlayer.getGamesWon()+1);
				completeNewGame();	
			}
			if (checkLoser())
			{
				String message = "unlucky " + currentPlayer.getName() + " - you lost!";
				Toast.makeText(this, message, Toast.LENGTH_LONG).show();
				if (currentPlayer == playerOne)
					playerTwo.setGamesWon(playerTwo.getGamesWon()+1);
				else 
					playerOne.setGamesWon(playerOne.getGamesWon()+1);
				completeNewGame();
			}
			currentPlayer = playerOne;
		}	
		for (NumberedDice dice: alRemoved)
		{
			dice.getImageView().setVisibility(View.VISIBLE);
		}
		
		//the highlight needs to be removed from the dice
		ImageView view1 = (ImageView) findViewById(R.id.num_dice_image_one); removeHighlight(view1);
		ImageView view2 = (ImageView) findViewById(R.id.num_dice_image_two); removeHighlight(view2);
		ImageView view3 = (ImageView) findViewById(R.id.num_dice_image_three); removeHighlight(view3);
		ImageView view4 = (ImageView) findViewById(R.id.num_dice_image_four); removeHighlight(view4);
		ImageView view5 = (ImageView) findViewById(R.id.num_dice_image_five); removeHighlight(view5);
		ImageView view6 = (ImageView) findViewById(R.id.num_dice_image_six); removeHighlight(view6);
		ImageView view7 = (ImageView) findViewById(R.id.num_dice_image_seven); removeHighlight(view7);
		ImageView view8 = (ImageView) findViewById(R.id.num_dice_image_eight); removeHighlight(view8);
		ImageView view9 = (ImageView) findViewById(R.id.num_dice_image_nine); removeHighlight(view9);
		ImageView view10 = (ImageView) findViewById(R.id.num_dice_image_ten); removeHighlight(view10);
		
		onPressNumberedDiceButton(null);
		onPressDottedDiceButton(null);
		
		selectedTotal = 0;
		
		alRemoved.clear();
		alLeft.clear();
		alSelected.clear();
		refreshLabels();
	}
	
	public boolean checkLoser()
	{
		if (currentPlayer.getScore() >= 200)
		{
			return true;
		}
		return false;
	}
	
	public boolean checkWinner()
	{
		if (currentPlayer.getScore() <= 0)
		{
			return true;
		}
		return false;
	}
	
	public int calculateScore()
	{
		if (alLeft.isEmpty())
			return -40;

		int totalLeftOver = 0;
		for (NumberedDice dice: alLeft)
		{
			int amountToAdd = dice.getDiceScore();
			if (amountToAdd == 0)
			{
				amountToAdd = 40;
			}
			totalLeftOver += amountToAdd;
		}
		return totalLeftOver;
	}
	
	public int selectedTotal;
	public ArrayList<NumberedDice> alSelected = new ArrayList<NumberedDice>(10);
	public ArrayList<NumberedDice> alRemoved = new ArrayList<NumberedDice>(10);
	public ArrayList<NumberedDice> alLeft = new ArrayList<NumberedDice>(10);
	
	public void removeHighlight(View view)
	{
		NumberedDice tempNumDice = (NumberedDice) view.getTag();
		((ImageView) view).setImageResource(0);
		tempNumDice.setHighlight(false);
	}
	
	public void onPressNumDice(View view)
	{
		System.out.println("Num Dice Pressed...");		
		NumberedDice tempNumDice = (NumberedDice) view.getTag();
		if (alSelected.contains(tempNumDice))
		{
			alSelected.remove(tempNumDice);
			System.out.println("dice unselected");
			removeHighlight(view);
		}
		else
		{
			alSelected.add(tempNumDice);
			tempNumDice.setImageView((ImageView) view);
			tempNumDice.setHighlight(true);	
			((ImageView) view).setImageResource(R.drawable.highlight_num_dice);	
		}
		
		selectedTotal = 0;
		for (NumberedDice dice: alSelected)
		{
			selectedTotal += dice.getDiceScore();
		}
		refreshLabels();
	}
		
	public void onPressSubmitNumberedDiceSelectionButton(View view)
	{
		//make sure that the total selected by the user is equal to the dotted dice
		//account for blanks
		int[] difference;
		difference = new int[10];
		int numberOfBlanks = 0;
		int i = 1;
		int x = 1;
		try
		{
			for (NumberedDice dice : alSelected)
			{
				if (dice.getDiceScore() == 0)
				{
					numberOfBlanks += 1;
					if (numberOfBlanks > 1){(difference[i -1])-=1;(difference[i])+=1;}
					else{difference[i] = dottedDiceTotal - selectedTotal;}
					if (difference[i] < 1) {throw new InvalidMoveException("Blanks can't be less than 1");}
					i++;
				}
				x++;
			}
		}
		catch (Exception e)
		{
			Toast.makeText(this, ((InvalidMoveException) e).getErrorMessage(), Toast.LENGTH_LONG).show();
			return;
		}
		
		if (x==i)
		{
			Toast.makeText(this, "Blanks must be used with other numbers", Toast.LENGTH_LONG).show();
			return;
		}
		
		if (difference[1] == 0)
		{
			if (dottedDiceTotal != selectedTotal)
			{
				Toast.makeText(getApplicationContext(), "Selected total doesn't equal spot dice total!", Toast.LENGTH_LONG).show();
				return;
			}
		}
		
		for (NumberedDice dice: alSelected)
		{
			dice.getImageView().setVisibility(View.INVISIBLE);
		}
		
		for (NumberedDice dice: alSelected)
		{
			alRemoved.add(dice);
		}
		
		alSelected.clear();
		onPressDottedDiceButton(view);
		
		if (alRemoved.size() == 10)	
		{
			Toast.makeText(getApplicationContext(), "Well done!", Toast.LENGTH_SHORT).show();
			changeTurn();
		}
	
		refreshLabels();
	
	}
	
	public void onPressEndTurnButton(View view)
	{
		for (NumberedDice dice1: alAllDice)
		{
			if (!alRemoved.contains(dice1))
			{
				alLeft.add(dice1);
			}
		}
		selectedTotal = 0;
		changeTurn();
	}
	
	public void refreshLabels()
	{
		TextView tv1 = (TextView) findViewById(R.id.dot_dice_total_text);
		tv1.setText("Total : " + dottedDiceTotal);
		
		TextView tv2 = (TextView) findViewById(R.id.current_player_text);
		tv2.setText(("Current player : " + currentPlayer.name));
		
		TextView tv3 = (TextView)findViewById(R.id.selected_total_text);
		tv3.setText("Selected total : " + selectedTotal);	
		
		TextView tvP1 = (TextView) findViewById(R.id.player_one_score_text);
		tvP1.setText(playerOne.getName() + "'s score : " + playerOne.getScore());
		
		TextView tvP2 = (TextView) findViewById(R.id.player_two_score_text);
		tvP2.setText(playerTwo.getName() + "'s score : " + playerTwo.getScore());
		
		TextView tvP1games = (TextView) findViewById(R.id.player_one_games_won_text);
		tvP1games.setText(playerOne.getName() + "'s games won : " + playerOne.getGamesWon());
		
		TextView tvP2games = (TextView) findViewById(R.id.player_two_games_won_text);
		tvP2games.setText(playerTwo.getName() + "'s games won : " + playerTwo.getGamesWon());
	}
	
   }

