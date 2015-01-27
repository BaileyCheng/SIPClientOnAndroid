package com.FCCheng.WatcherClientOnAndroid;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class AndroidOverlay extends Overlay {
	
    GeoPoint p;   
    
    Paint paint1 = new Paint();
    Paint paint2 = new Paint();
    
    int presentityState = 0;
    int presentityNumber;
    
    int presentityState_Null=0;
    int presentityState_Open=1;
    int presentityState_Wait=2;
    int presentityState_Closed=3;
    
	@Override
	public void draw(Canvas canvas, MapView mapView, boolean
	shadow) { 
		super.draw(canvas, mapView, shadow);   
		
        if (presentityState==0){
            paint1.setARGB(200, 200, 200, 200);  //灰色
        	//paint1.setARGB(100, 255, 0, 0);	 //紅色
        
        }else if (presentityState==1){
        	paint1.setARGB(100, 0, 255, 0); //綠色
        }else if (presentityState==2){
        	paint1.setARGB(100, 255, 255, 0);  //黃色
        }else if (presentityState==3){
        	paint1.setARGB(100, 255, 0, 0);	 //紅色
        }else if (presentityState==4){
        	 paint1.setARGB(110, 130, 110, 200);	 //淺水藍色 當作 資料為null時
         }
        paint2.setARGB(255, 255, 255, 255);
		
        int[] xyCoordinates = new int[2];   
        //calculator.getPointXY(p, xyCoordinates);   
        canvas.drawCircle(xyCoordinates[0], xyCoordinates[1], 9, paint1);
        canvas.drawText(""+this.presentityNumber,
        		xyCoordinates[0] -4,
        		xyCoordinates[1] +4, paint2); 
		
		
	}
	
    //設定餐廳的狀態 以此依據來畫顏色
    public void setState(int presentityState){
    	this.presentityState= presentityState;
    }
    
    
    //設定餐廳的編號  此編號等同於Overlays的編號
    public void setPresentityNumber(int presentityNumber){
    	this.presentityNumber = presentityNumber;
    }
    
    
}
