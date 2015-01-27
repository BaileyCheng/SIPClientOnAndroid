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
            paint1.setARGB(200, 200, 200, 200);  //�Ǧ�
        	//paint1.setARGB(100, 255, 0, 0);	 //����
        
        }else if (presentityState==1){
        	paint1.setARGB(100, 0, 255, 0); //���
        }else if (presentityState==2){
        	paint1.setARGB(100, 255, 255, 0);  //����
        }else if (presentityState==3){
        	paint1.setARGB(100, 255, 0, 0);	 //����
        }else if (presentityState==4){
        	 paint1.setARGB(110, 130, 110, 200);	 //�L���Ŧ� ��@ ��Ƭ�null��
         }
        paint2.setARGB(255, 255, 255, 255);
		
        int[] xyCoordinates = new int[2];   
        //calculator.getPointXY(p, xyCoordinates);   
        canvas.drawCircle(xyCoordinates[0], xyCoordinates[1], 9, paint1);
        canvas.drawText(""+this.presentityNumber,
        		xyCoordinates[0] -4,
        		xyCoordinates[1] +4, paint2); 
		
		
	}
	
    //�]�w�\�U�����A �H���̾ڨӵe�C��
    public void setState(int presentityState){
    	this.presentityState= presentityState;
    }
    
    
    //�]�w�\�U���s��  ���s�����P��Overlays���s��
    public void setPresentityNumber(int presentityNumber){
    	this.presentityNumber = presentityNumber;
    }
    
    
}
