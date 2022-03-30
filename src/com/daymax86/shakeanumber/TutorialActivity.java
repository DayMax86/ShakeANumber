package com.daymax86.shakeanumber;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import com.daymax86.shakeanumber.R;

public class TutorialActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_tutorial);
		imageNumber = 0;
		nextTutImage(findViewById(R.id.next_tutorial_image));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tutorial, menu);
		return true;
	}
	
	public int imageNumber;
	
	public void nextTutImage(View view)
	{
		imageNumber++;
		ImageView iv = (ImageView) findViewById(R.id.tutorial_image);
		if (imageNumber == 1)
			iv.setImageResource(R.drawable.tutone);
		if (imageNumber == 2)
			iv.setImageResource(R.drawable.tuttwo);
		if (imageNumber == 3)
			iv.setImageResource(R.drawable.tutthree);
		if (imageNumber > 3)
		{
			imageNumber--;
			iv.setImageResource(R.drawable.tutthree);
		}
	}
	
	public void previousTutImage(View view)
	{
		imageNumber--;
		ImageView iv = (ImageView) findViewById(R.id.tutorial_image);
		if (imageNumber < 1)
		{
			imageNumber++;
			iv.setImageResource(R.drawable.tutone);
		}
		if (imageNumber == 1)
			iv.setImageResource(R.drawable.tutone);
		if (imageNumber == 2)
			iv.setImageResource(R.drawable.tuttwo);
		if (imageNumber == 3)
			iv.setImageResource(R.drawable.tutthree);
	}
}
