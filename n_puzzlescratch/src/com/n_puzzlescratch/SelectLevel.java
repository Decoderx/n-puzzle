package com.n_puzzlescratch;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

public class SelectLevel extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d("select level ","inside");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_level);
		
		Button easy =(Button)findViewById(R.id.radio0);
		Button medium =(Button)findViewById(R.id.radio1);
		Button hard =(Button)findViewById(R.id.radio2);
		
		
		easy.setOnClickListener(respondClick);//.setOnClickListener(respondStart);
		medium.setOnClickListener(respondClick);
		hard.setOnClickListener(respondClick);
		//exit.setOnClickListener(respondClick);
		// Show the Up button in the action bar.
		setupActionBar();
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.select_level, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

private OnClickListener respondClick= new OnClickListener (){
		
		public void onClick(View v){
			Context ctx=getApplicationContext();
			CharSequence text;
			switch(v.getId())
			{case R.id.radio0:text="StartGame Pressed";ImageSelection.dimension=3;
			//create a new intent to display the activity showing list of images
			ImageSelection.mode="Easy";
		    break;
			case R.id.radio1:text="StartGame Pressed";ImageSelection.dimension=4;
			//create a new intent to display the activity showing list of images
			ImageSelection.mode="Medium";
		    break;
			case R.id.radio2:text="StartGame Pressed";ImageSelection.dimension=5;
			//create a new intent to display the activity showing list of images
			ImageSelection.mode="Hard";
		    break;
		default:ImageSelection.mode="Medium";ImageSelection.dimension=3;
			
			}
			
		
		}
	};
	
	
}
