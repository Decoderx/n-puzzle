package com.n_puzzlescratch;






import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ImageSelection extends Activity {
   static String mode="Easy";
   static int dimension=3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_selection);
		Button startgame =(Button)findViewById(R.id.startgame);
		Button level =(Button)findViewById(R.id.level);
		Button help =(Button)findViewById(R.id.help);
		Button exit =(Button)findViewById(R.id.exit);
		
		startgame.setOnClickListener(respondClick);//.setOnClickListener(respondStart);
		level.setOnClickListener(respondClick);
		help.setOnClickListener(respondClick);
		exit.setOnClickListener(respondClick);
	}
	
	private OnClickListener respondClick= new OnClickListener (){
		
		public void onClick(View v){
			Context ctx=getApplicationContext();
			CharSequence text;
			switch(v.getId())
			{case R.id.startgame:text="StartGame Pressed";
			//create a new intent to display the activity showing list of images
			Intent i = new Intent(ImageSelection.this,StartPushed.class);
			startActivity(i);
		    break;
			case R.id.level: text = "Level pushed!"; 
			Intent j = new Intent(ImageSelection.this,SelectLevel.class);
			startActivity(j);
			break;
			case R.id.help: text = "Help pushed!";
			Intent k = new Intent(ImageSelection.this,DisplayHelp.class);
			startActivity(k);
			break;
			case R.id.exit: text = "Exit pushed!"; break;
			default: text="Dunno what was pushed!";
			
			}
			int duration =Toast.LENGTH_LONG;
			Toast pressedButton= Toast.makeText(ctx, text, duration);
			pressedButton.show();
		
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.image_selection, menu);
		return true;
	}

}
