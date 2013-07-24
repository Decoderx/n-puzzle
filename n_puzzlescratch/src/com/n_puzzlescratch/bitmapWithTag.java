package com.n_puzzlescratch;

import android.graphics.Bitmap;

public class bitmapWithTag {
	 Bitmap slide;
	 int originalpos;
	 int currentpos;
	 bitmapWithTag(){
		 
		 
		 
	 } 
	 bitmapWithTag(Bitmap b,int pos1,int pos2){
		 this.slide=b;
		 this.originalpos=pos1;
		 this.currentpos=pos2;
	
	
}	 
 public boolean compareTags(int pos){
	return(this.currentpos==pos);
		
	}
 public boolean checkPosition(){
	 return(originalpos==currentpos);
	 
	 
 }
}