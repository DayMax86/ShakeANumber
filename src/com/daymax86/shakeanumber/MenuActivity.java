package com.daymax86.shakeanumber;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import com.daymax86.shakeanumber.R;

public class MenuActivity extends Activity {

	private boolean inProgress;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_menu);
		
		inProgress = getIntent().getBooleanExtra("in_progress", true);
		
		Button button = (Button)findViewById(R.id.play_game_button);
		button.setText(inProgress ? "Resume game" : "New game");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}
	
	@Override
	public void onBackPressed()
	{
		if (inProgress)
			super.onBackPressed();
	}
	
	public void launchGame(View view)
	{		
		// If game in progress then finish without asking for names
		if (inProgress)
		{
			setResult (MainActivity.RESULT_IN_PROGRESS);
			finish();
		}
		else
		{
			// Get data through NameActivity
			Intent intent = new Intent(this,SecondaryActivity.class);
			startActivityForResult(intent, MainActivity.NAME_ACTIVITY);
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (resultCode == RESULT_OK)
		{
			setResult(resultCode, data);
			finish();
		}
   	}
	
	@Override
	 public void onConfigurationChanged(Configuration config)
	 {
	  super.onConfigurationChanged(config);
	 }
	
	public void showTutorial(View view)
	{
		// Start tutorial activity
		Intent intent = new Intent(this,TutorialActivity.class);
		startActivity(intent);
	}
}
