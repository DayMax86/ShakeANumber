package com.daymax86.shakeanumber;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import com.daymax86.shakeanumber.R;

public class SecondaryActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.name_capture);
	}

	public void onPressSubmitNamesButton(View view)
	{
		String nameToReturn[] = new String[2];
		EditText et1 = (EditText) findViewById(R.id.enter_name_box_one);
		nameToReturn[0] = et1.getText().toString();
		EditText et2 = (EditText) findViewById(R.id.enter_name_box_two);
		nameToReturn[1] = et2.getText().toString();
		
		Intent data = new Intent();
		data.putExtra("names", nameToReturn);
		setResult(RESULT_OK, data);
		finish ();
	}
	
	@Override
	 public void onConfigurationChanged(Configuration config)
	 {
	  super.onConfigurationChanged(config);
	 }
}
