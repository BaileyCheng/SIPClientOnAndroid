package com.FCCheng.WatcherClientOnAndroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class RegisterConfigure extends Activity {
	
	EditText mUserNameText;
    EditText mPresenceServerText;
    EditText mLat;
    EditText mLng;
    Button registerButton;
    Button unRegisterButton;
    
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.register);
		
		
        mUserNameText = (EditText) findViewById(R.id.username);
        mPresenceServerText = (EditText) findViewById(R.id.presenceServer);
        mLat = (EditText) findViewById(R.id.lat);
        mLng = (EditText) findViewById(R.id.lng);
        //registerButton = (Button) findViewById(R.id.register);
        
        Intent intent = this.getIntent();  // 取得傳來的Intent
		
		//依照Intent所夾帶的資料來設定所指定的變數
        mUserNameText.setText(intent.getStringExtra("UserName"));
        mPresenceServerText.setText(intent.getStringExtra("PresenceServer"));
        //mLat.setText((CharSequence)intent.getDoubleExtra("Lat", 1));
        //mLat.setT
        mLat.setText(intent.getStringExtra("Lat"));
        mLng.setText(intent.getStringExtra("Lng"));
        


        
        
	}
	
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		boolean result = super.onCreateOptionsMenu(menu);
		menu.add(0,1, Menu.FIRST, "Register");
		//menu.add(0, Menu.FIRST+1, "UnRegister");
		menu.add(0,2, Menu.FIRST+1, "Back");
		
		return result;
    	
    }
    
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		boolean result = super.onOptionsItemSelected(item);
		Bundle bundle = new Bundle();
		switch (item.getItemId()) {
		case 1:   //點選Register
			
			bundle.putString("UserName", mUserNameText.getText().toString());
			bundle.putString("PresenceServer", mPresenceServerText.getText().toString());
			bundle.putBoolean("Register", false);
			bundle.putDouble("Lat", Double.parseDouble(mLat.getText().toString()));
			bundle.putDouble("Lng", Double.parseDouble(mLng.getText().toString()));
	
			//設回傳的結果,  setResult(要求碼1:ok, 0:no,結果碼, Bundle資料) 
			setResult(1, null, bundle);    //onActivityResult(int requestCode, int resultCode,String data, Bundle extras) 
			finish();
			break;
		case 2:  //點選UnRegister
			//bundle.putString("UserName", mUserNameText.getText().toString());
			//bundle.putString("PresenceServer", mPresenceServerText.getText().toString());
			//bundle.putBoolean("Register", true);
			//設回傳的結果,  setResult(要求碼1:ok, 0:no,結果碼, Bundle資料) 
			//setResult(1, null, bundle);    //onActivityResult(int requestCode, int resultCode,String data, Bundle extras) 
			//finish();
			//break;
		//case (Menu.FIRST+2):  //點選Back
			setResult(0);
			finish();
			
		}
		
		return result;
    }

}
