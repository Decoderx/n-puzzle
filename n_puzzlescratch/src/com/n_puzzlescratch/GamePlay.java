package com.n_puzzlescratch;


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class GamePlay extends Activity implements OnItemClickListener {
int positions[]= {0,1,2,3,4,5,6,7,8};
Timer timer;
//Bitmap bitmapArray[];
bitmapWithTag bitmapArray[];
	protected void onCreate(Bundle savedInstanceState) {
		
		//Log.VERBOSE("kya hua","oncreate");
		Log.d("oncreate","really"+ImageSelection.dimension);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_play);
//		ActionBar actionBar = getActionBar();
//	    actionBar.setDisplayHomeAsUpEnabled(true);
		Bundle extras=getIntent().getExtras();
		int imageId=(int)extras.getLong("selectedImage");
		DisplayMetrics metrics=new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		int pixelHeight=metrics.heightPixels;
		int pixelWidth=metrics.widthPixels;
		
		
		
		
//		GridView gridView=(GridView)findViewById(R.id.imageGridView);
//		
//		//gridView.setAdapter(new TileAdapter(this));
//		 try {
//	        	// load large image from resources
//	        	Bitmap background = BitmapFactory.decodeResource(this.getResources(), imageId);
//	        	// create cropped image from loaded image
//	        	Bitmap cropped = Bitmap.createBitmap(background, 0, 0, 100, 100);
//	        	// no longer need larger image
//	        	//background.recycle();
//	        	
//	        	// create ImageView to display image
//	        	ImageView imageView = new ImageView(this);
//	        	
//	        	gridView.addView(imageView);
//	        	imageView.setImageBitmap(cropped);
//	        	// add ImageView to root layout
//	        	
//	        	
//	        	int screenWidth = this.getResources().getDisplayMetrics().widthPixels;
//	        	Toast.makeText(this, String.valueOf(screenWidth), Toast.LENGTH_LONG).show();
//	        }
//	        catch (OutOfMemoryError e) {
//	        	// uh oh.
//	        }
//		
//		
//		gridView.setOnItemClickListener(this);

		GridView gridView=(GridView)findViewById(R.id.gridView);
		gridView.setNumColumns(ImageSelection.dimension);
		Bitmap background = BitmapFactory.decodeResource(this.getResources(), imageId);
		gridView.setOnItemClickListener(this);
   	// create cropped image from loaded image
	// we cannot statically provide these coordinates try to dynamically select them	
   	//Bitmap cropped = Bitmap.createBitmap(background, 0, 0, 300, 200);
		Bitmap cropped;
		int cropedWidth=pixelWidth;
		int cropedHeight=pixelHeight;
		if(cropedHeight>cropedWidth){
			//replace 10 with padding later
			cropedWidth=cropedHeight=pixelWidth;
			 cropped=	Bitmap.createScaledBitmap(background,  pixelWidth, pixelWidth,true);
			
		}
		else {
			cropedWidth=cropedHeight=pixelHeight;
			 cropped=	Bitmap.createScaledBitmap(background,  pixelHeight, pixelHeight,true);
			
		}
		
   	//  
		 int dimension=ImageSelection.dimension;
		bitmapArray=new bitmapWithTag[dimension*dimension];
		//bitmapWithTag bitmapWithTagsArray[]=new bitmapWithTag[3*3];
		int i,j;
		int tileDim=cropedWidth/dimension; System.out.print("dasdadadasd");
	try{	
		for(i=0;i<dimension;i++)
			for(j=0;j<dimension;j++){
				bitmapArray[i*dimension+j]=new bitmapWithTag(Bitmap.createBitmap(cropped, (j)*tileDim,(i)*tileDim ,tileDim, tileDim),i*dimension+j,i*dimension+j);
//			bitmapArray[i*3+j].slide=Bitmap.createBitmap(cropped, (j)*tileDim,(i)*tileDim ,tileDim, tileDim);
//			bitmapArray[i*3+j].currentpos=i*3+j;
//			bitmapArray[i*3+j].originalpos=i*3+j;
//		   // bitmapWithTagsArray[i*3+j]=new bitmapWithTag(bitmapArray[i*3+j],(i*3+j));
//			if(i*3+j==3*3-1){
//				bitmapWithTagsArray[i*3+j].slide.eraseColor(android.graphics.Color.BLACK);
//			}
			}
			
	}catch(Exception e){
		Context ctx=getApplicationContext();
		int duration =Toast.LENGTH_SHORT;
		Toast pressedButton= Toast.makeText(ctx, "in exception"+tileDim, duration);
		pressedButton.show();
//		gridView.setImageBitmap(background);
		gridView.setAdapter(new gridAdapter(this,bitmapArray,tileDim));
	}	
		
	//Context ctx=getApplicationContext();
	Context ctx=this;
	gridAdapter g1=new gridAdapter(this,bitmapArray,tileDim-(int)(tileDim*0.1));
	//g1.imgView.setLayoutParams(new GridView.LayoutParams(tileDim,tileDim));
	gridView.setAdapter(g1);
	Log.d("gridview","created");
	TextView tx=(TextView)findViewById(R.id.timeTaken);
	 timer=new Timer((long)5000,(long)1000,ctx,bitmapArray,g1,tx);
	timer.start();
	EditText timertext=(EditText)findViewById(R.id.timeTaken);
	timertext.setText(0+":Secs elapsed");
	
	Log.d("past","timer");
	
	//g1.notifyDataSetChanged();
//	try{Thread.sleep(10000);
//	
//	}catch(Exception E){
//		System.out.print("phati");
//		
//		
//	}
//	Handler handler = new Handler(); 
//    handler.postDelayed(new Runnable() { 
//         public void run() { 
//        	 System.out.print("inside");
//              shuffle(); 
//         } 
//    }, 7000);
	
	
     
   
	
	
	
	
	
	
	
    //gridView.setImageBitmap(bitmapArray[1]);	
    //gridView.setAdapter(new gridAdapter(this,bitmapArray));
    
	//gridView.setAdapter(new gridAdapter(this,bitmapArray));	
		
		
		//		TextView showimg=(TextView)findViewById(R.id.imageId);
//		showimg.setText(""+imagePos);
		
		
		//get image break it and add it to a grid view 
		
		setupActionBar();
	}
	
public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
    	
	  gridAdapter t;
    t=	(gridAdapter)parent.getAdapter();
       	t.swapWithBlank(position);
       	t.notifyDataSetChanged();
//    t.swap(position, 8);
//    Bitmap swap;
//	swap=bitmapArray[0];
//	bitmapArray[0]=bitmapArray[8];
//	bitmapArray[8]=swap;
   //t.notifyDataSetChanged();
    Log.d("item ","clicked position ==="+position);
    //parent.invalidate();
    
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
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_play, menu);
		return true;
	}
	
	public Bitmap[] shuffleBitmaps(Bitmap[] slide,int level){
		
		
		
		
		return slide;
	}
	@Override
		protected void onDestroy() {
			// TODO Auto-generated method stub
			super.onDestroy();
		countDownDisplay l=timer.C;
		l.cancel(true);
			//(countDownDisplay)(timer.C).cancel(true);
		}

}
