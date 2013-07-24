package com.n_puzzlescratch;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class Timer extends CountDownTimer {
	Toast pressedButton;
	bitmapWithTag bitmapArray[];
	//GridView gridView;
	gridAdapter gx;
	TextView  tx;
	Context ctx;
  public	countDownDisplay C;
	Timer(long duration,long tick,Context ctx,bitmapWithTag[] bitmapArray,gridAdapter g, TextView  tx){super(duration,tick);
	pressedButton=Toast.makeText(ctx, "Test Ur Memory", Toast.LENGTH_LONG);
	this.bitmapArray=bitmapArray;
	this.tx=tx;
//	gridView=gridView;
	gx=g;
	this.ctx=ctx;
	}
	
	@Override
	public void onFinish() {
		// TODO Auto-generated method stub
		//bitmapArray[0].eraseColor(android.graphics.Color.BLACK);
		 pressedButton.setText("Start!!");
//		 //this.onFinish("calling return");
//		 Bitmap swap;
//			swap=bitmapArray[0];
//			bitmapArray[0]=bitmapArray[8];
//			bitmapArray[8]=swap;	
			//gx=(gridAdapter)gridView.getAdapter();
			gx.shuffle();
			//gridView.
			Log.d("finish","in onfinis");
		    C=new countDownDisplay(tx,ctx);
			C.execute(gx.cache);
			gx.notifyDataSetChanged();
	}

//	public boolean onFinish(String str) {
//		// TODO Auto-generated method stub
//		
//		return true;	
//	}
	
	
	
	@Override
	public void onTick(long millisUntilFinished) {
		// TODO Auto-generated method stub
		pressedButton.setText("seconds remaining: " + millisUntilFinished / 1000 );
		pressedButton.show();
//		if((millisUntilFinished / 1000)==10) 
//		bitmapArray[0].eraseColor(0);
	}

}
