package com.n_puzzlescratch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class gridAdapter extends BaseAdapter{
	 int total=ImageSelection.dimension*ImageSelection.dimension;
	private Context mContext;
	ImageView imgView;
	public bitmapWithTag[] cache;
	int gridDimension;
	//BitmapFactory.Options options;
	Bitmap thumb ;
	bitmapWithTag[] copy;
	public gridAdapter(Context c, bitmapWithTag[] bitmapArray,int gridDimension){
		
		Log.d("gridadap", "inside construct");
		cache = new bitmapWithTag[bitmapArray.length];
		
		
		copy=bitmapArray;
	
		mContext=c;
		this.gridDimension=gridDimension;
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		 return copy.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void  swap(int i, int j){
		
		Log.d("swap", "called i=="+i+" j=="+j);
		bitmapWithTag swap=new bitmapWithTag();
		swap.slide=cache[i].slide;
		swap.originalpos=cache[i].originalpos;
		cache[i].slide=cache[j].slide;
		cache[i].originalpos=cache[j].originalpos;
		cache[j].slide=swap.slide;
		cache[j].originalpos=swap.originalpos;
		
	}
	public void eraseGridColor(int j){
		Log.d("erase","inside erase");
		cache[j].slide.eraseColor(0);
		
		
	}
	public void shuffle(){
		
	// generate a correct permutation or generate a permutation
    //check wheter its correct or not 
    // repeat steps if not
		 int shufflecount=0;
		 
	 int middle=ImageSelection.dimension*ImageSelection.dimension/2;
	 int total=ImageSelection.dimension*ImageSelection.dimension;
		
	 while(++shufflecount<4)
	 { int i;
		     for(i=0;i<total;i++)
		     {int rand=(int)(Math.random()*100)%(total);		     	 
		 	//Log.d("shuffle","inside shuffle"+i+"    =="+rand);  
		     bitmapWithTag swap=new bitmapWithTag();
				swap.slide=cache[i].slide;
				swap.originalpos=cache[i].originalpos;
				cache[i].slide=cache[rand].slide;
				cache[i].originalpos=cache[rand].originalpos;
				cache[rand].slide=swap.slide;
				cache[rand].originalpos=swap.originalpos;
		     
		     }
		     Log.d("shuffle","count"+shufflecount);
		     if(!isSolvablepermuntation())continue;
		     else break;
		    
		     
	 }   
		   
		     
		     
		     
		     
		     
		
               		
		
	}
	
	public boolean isSolvablepermuntation(){
		
		int i,j,N=0;
		for(i=0;i<total-1;i++)
			for(j=i+1;j<total;j++){
				if(cache[i].originalpos>cache[j].originalpos)++N;
				
			}
		Log.d("issolvable","N+ImageSelection.dimension===="+(N+ImageSelection.dimension));
		if((ImageSelection.dimension)%2==0)
		{if(((N+ImageSelection.dimension-1)%2)==0)return false;
		else return true;
		}
		else{if(((N+ImageSelection.dimension-1)%2)==0)return true;
		else return false;
			
			
			
		}
		
	}
	

	
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		
		ImageView imgView;

		
		if(convertView == null) {

			// create a new view
			imgView = new ImageView(mContext);//mContext.getApplicationContext().getAssets().
		imgView.setLayoutParams(new GridView.LayoutParams(gridDimension,gridDimension));
			

		} else {
	
			// recycle an old view (it might have old thumbs in it!)
			imgView = (ImageView) convertView;
	
		}
		//Log.d("insidepositionm",""+position+"  opy==="+((copy.length)-1));
		// see if we've stored a resized thumb in cache
		try{if(cache[position] == null) {
			Log.d("insidepositionm",""+position+"  opy==="+((copy.length)-1));
			// create a new Bitmap that stores a resized
			// version of the image we want to display. 
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inSampleSize = ImageSelection.dimension;
			//Bitmap thumb = BitmapFactory.decodeFile(copy[position], options)//(mContext.getResources(), copy[position], options);

			// store the resized thumb in a cache so we don't have to re-generate it
			cache[position]=new bitmapWithTag(Bitmap.createScaledBitmap(copy[position].slide,  50, 50,true),position,position);
			//cache[position].slide = Bitmap.createScaledBitmap(copy[position].slide,  50, 50,true);
			//cache[position].slide.
			if(position==(copy.length)-1)eraseGridColor(position);
			Log.d("positionm",""+position+"  opy==="+((copy.length)-1));
		}
		}catch(Exception e){
			
			
			Log.d("maralo", "insidde exception");
		}
		// use the resized image we have in the cache
		imgView.setImageBitmap(cache[position].slide);


		// We might be tempted to do the below, but this is bad. The
		// images we've put in the drawable directory are quite large
		// and need to be scaled down to load all of them in memory to
		// display on screen. If we just use the raw images (as in the
		// below code) we would quickly get an OutOfMemory exception,
		// as the entire image would be loaded in memory and scaled 
		// down live.
		//imgView.setImageResource(images[position]);

		return imgView;		
		
	}
	
	public void swapWithBlank(int pos){
		
		if(cache[pos].originalpos!=total-1){
			if(pos-1>=0&&cache[pos-1].originalpos==total-1)swap(pos,pos-1);
			else if(pos+1<total&&cache[pos+1].originalpos==total-1)swap(pos,pos+1);
				else if(pos+ImageSelection.dimension<total&&cache[pos+ImageSelection.dimension].originalpos==total-1)swap(pos,pos+ImageSelection.dimension);
					else if(pos-ImageSelection.dimension>=0&&cache[pos-ImageSelection.dimension].originalpos==total-1)swap(pos,pos-ImageSelection.dimension);
			
			
			
		}
		
	}

}
