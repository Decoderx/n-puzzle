package com.n_puzzlescratch;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;
import android.widget.TextView;

public class countDownDisplay extends AsyncTask<bitmapWithTag, Integer ,  Integer>{
         static int countdown=0;
         TextView rootvw;
         Context ctx;
         public  countDownDisplay(TextView vw,Context ctx){
        	 rootvw=vw;
        	 this.ctx=ctx;
        	 
         }    
    @Override    
	protected Integer doInBackground(bitmapWithTag[] params) {
    	countdown=0;
		Log.d("COUNTOWN", "INSIDE");
		while(!allimagesset(params))
		{if(isCancelled()) break;
			SystemClock.sleep(1000);++countdown;

			Log.d("WHILE", "INSIDE"+countdown);
		publishProgress(countdown);
		}
		if(isCancelled()) 
		{
			
		return (Integer)0;
		}
		else return (Integer)1;
	}
	
	public boolean allimagesset(bitmapWithTag[] params){
		Log.d("ALLIMAGES", "INSIDE");
		int total=ImageSelection.dimension*ImageSelection.dimension;
		int i;
		for(i=0;i<total;i++){
			if(!(params[i].originalpos==i))break;
			
		}
		Log.d("I===", "total"+i);
		if(i==total)return true;
		else return false;
	}
	@Override
	protected void onProgressUpdate(Integer...Count) {
		Log.d("progress", "updated count=="+Count);
	//update the timer evry second	
		//Activity a=new Activity();
		//TextView timertext=(TextView)rootvw.findViewById(R.id.timeTaken);
		rootvw.setText(Count[0]+":Secs elapsed");
		
	}
	@Override
	protected void onPostExecute(Integer result) {
	// TODO Auto-generated method stub
		
		
	super.onPostExecute(result);
	if(result==1)
	{
		Dialog d=new Dialog(ctx);
		d.setTitle("Congratulations you solved the puzzle in "+countdown+"Seconds");
		d.show();
		
	}
	Log.d("POSTEXEC", "");
	}
	
	

}
