package com.example.home_automation;

import java.io.FileOutputStream;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.view.Menu;
import android.view.Window;
import android.widget.ImageView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_main);

		ImageView myAnimation = (ImageView)findViewById(R.id.myanimation);
		final AnimationDrawable myAnimationDrawable
		= (AnimationDrawable)myAnimation.getDrawable();
		

		
		
		final Handler handler = new Handler();
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				  myAnimationDrawable.start();

				start_program(); // transitioning to the list view of the plug loads
				finish();
				 myAnimationDrawable.stop();
			}
			
		}, 3000);
	}


	
	//http://stackoverflow.com/questions/11455455/splash-screen-alpha-animation-in-android/11456132#11456132
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	


	
	private void start_program()
	{

		Intent intent = new Intent(this, ListActivity.class);

		startActivity(intent);
	}

}
