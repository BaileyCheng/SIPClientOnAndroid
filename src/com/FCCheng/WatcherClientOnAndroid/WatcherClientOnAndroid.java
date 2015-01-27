package com.FCCheng.WatcherClientOnAndroid;





import java.text.ParseException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.TooManyListenersException;

import javax.sip.ClientTransaction;
import javax.sip.Dialog;
import javax.sip.DialogTerminatedEvent;
import javax.sip.IOExceptionEvent;
import javax.sip.InvalidArgumentException;
import javax.sip.ListeningPoint;
import javax.sip.ObjectInUseException;
import javax.sip.PeerUnavailableException;
import javax.sip.RequestEvent;
import javax.sip.ResponseEvent;
import javax.sip.ServerTransaction;
import javax.sip.SipException;
import javax.sip.SipFactory;
import javax.sip.SipListener;
import javax.sip.SipProvider;
import javax.sip.SipStack;
import javax.sip.TimeoutEvent;
import javax.sip.Transaction;
import javax.sip.TransactionTerminatedEvent;
import javax.sip.TransportNotSupportedException;
import javax.sip.address.Address;
import javax.sip.address.AddressFactory;
import javax.sip.address.SipURI;
import javax.sip.address.URI;
import javax.sip.header.CSeqHeader;
import javax.sip.header.CallIdHeader;
import javax.sip.header.ContactHeader;
import javax.sip.header.ContentTypeHeader;
import javax.sip.header.EventHeader;
import javax.sip.header.ExpiresHeader;
import javax.sip.header.FromHeader;
import javax.sip.header.HeaderFactory;
import javax.sip.header.MaxForwardsHeader;
import javax.sip.header.SubscriptionStateHeader;
import javax.sip.header.ToHeader;
import javax.sip.header.ViaHeader;
import javax.sip.message.Message;
import javax.sip.message.MessageFactory;
import javax.sip.message.Request;
import javax.sip.message.Response;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class WatcherClientOnAndroid extends MapActivity implements SipListener,MessageProcessor{
	
	
    //JAIN-SIP Attributes
    private SipProvider sipProvider;
    private AddressFactory addressFactory;
    private MessageFactory messageFactory;
    private HeaderFactory  headerFactory;
    private SipStack    sipStack;    
    ClientTransaction   inviteTid;  
	
	
    //private SipLayer sipLayer;
    private SipLayer sipLayer;
	private EditText mUserNameText;
    private EditText mPresenceServerText;
    private Long mRowId;
    String mUserName="s11";
    String mPresenceServer="sip:s5@140.128.41.151:5065";
    boolean register;
    String ip4 = "10.0.2.2";
    String ip3= "127.0.0.1";
    String ip = "10.0.2.15";
    String ip1 = "192.168.188.75";
    int port =7777;
	public static final int change = 3000;
    private MapView mapView;
    private MapController mapConrtoller;
    
    Double DefaultLat=25.03;  //台北經緯度 new
    Double DefaultLng=121.55;
    Double DefaultLat5=25.03*1E6;  //台北經緯度 new
    Double DefaultLng5=121.55*1E6;
    Double DefaultLat3=23.697810;  
    Double DefaultLng3=120.960515;
    Double DefaultLat2=23.697810*1E6;  //台灣經緯度
    Double DefaultLng2=120.960515*1E6;
    Double DefaultLat1=25.03*1E6;  //台北經緯度
    Double DefaultLng1=121.30*1E6;
	GeoPoint DefaultPoint = new GeoPoint(DefaultLat5.intValue(), DefaultLng5.intValue());
    
    
    static WatcherClientOnAndroid tc;
    ItemizedOverlay oc; 
    PresentityManager presentityManager = new PresentityManager();
    int presentityNumber=0;  //餐廳編號
    private Hashtable numberToPresentityUserNameList; //輸入pressPresentityNumber去查對應的presentityUserName
    
   	AlertDialog presentityDialog = null;  //警告對話框
   	int pressPresentityNumber; //使用者按下的餐廳編號
   	boolean hasSubscribed;
   	
    PidfParser pidfParser;
    
    
    int overlayNumber;  //測試用
    String testString="";
    String testPidf;
    public static final int CONFIG_USER_REGISTER = 1;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        
        mapView = new MapView(this, "0oaXq3FsriiB3dBByi9Y7bKSEdTtOX-wFBWE3-w");    
        setContentView(mapView);    
        numberToPresentityUserNameList = new Hashtable(); 
        this.pidfParser = new PidfParser();
        
        mapConrtoller = mapView.getController();
        updateView(DefaultLat3,DefaultLng3,10);
  
        oc = mapView.createOverlayController();
        
        try{
        String username = "ss";	
        	
		//SipLayer sipLayer = new SipLayer(username, ip, port, DefaultLat , DefaultLng, presentityManager);
        sipCreate();
        }catch (Throwable e){
            showAlert("Error", R.drawable.icon,
    				"Problem sipCreate() not ok: "+e.getMessage(), "Ok",
    				true);
        }
        


        /*
        
		try
        {
		    String username = "defaultUserName";
		    int port = 5080;
		    //String ip = InetAddress.getLocalHost().getHostAddress();
		    String ip= "192.168.188.75";
		    Double Lat = 23.697810; //23.697810 
		    Double Lng = 120.960515; //120.960515

			SipLayer sipLayer = new SipLayer(username, ip, port, Lat , Lng, presentityManager);
			this.sipLayer = sipLayer;
			//tc = new WatcherClientOnAndroid();
		    sipLayer.setMessageProcessor(tc);
			
			//tc.show();
        } catch (Throwable e)
        {
        	showAlert("Error", R.drawable.icon,
					"Problem initializing the SIP stack.", "Ok",
					true);
           // System.out.println("Problem initializing the SIP stack.");
            e.printStackTrace();
           // System.exit(-1);
        }
        */
        /*
        setContentView(R.layout.main);
        
        mUserNameText = (EditText) findViewById(R.id.username);
        mPresenceServerText = (EditText) findViewById(R.id.presenceServer);
        
        try
        {
        ip = InetAddress.getLocalHost().getHostAddress();
        } catch (Throwable e)
        {
			showAlert("Error", R.drawable.icon,
					"can't getHostAddress", "Ok",
					true);
        }
        mPresenceServerText.setText(ip);
        */
        /*
        Button registerButton = (Button) findViewById(R.id.register);
        
        registerButton.setOnClickListener(new View.OnClickListener(){
        	
        	public void onClick(View view) {
                Bundle bundle = new Bundle();
               System.out.println(mUserNameText.getText().toString());
               // bundle.putString(NotesDbAdapter.KEY_TITLE, mTitleText.getText().toString());
              //  bundle.putString(NotesDbAdapter.KEY_BODY, mBodyText.getText().toString());
              //  if (mRowId != null) {
              //      bundle.putLong(NotesDbAdapter.KEY_ROWID, mRowId);
              //  }
               
               // setResult(RESULT_OK, null, bundle);
               // finish();
            }
        });
        */
    }

    
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		boolean result = super.onCreateOptionsMenu(menu);
		menu.add(0,1, Menu.FIRST, "Register");
		menu.add(0,2, Menu.FIRST+1, "Exit");
		//menu.add(0, Menu.FIRST+2, "Back");
		
		return result;
    	
    }
    
	//點選選項 執行特定動作後回傳結果
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			String data, Bundle extras) {
		// TODO Auto-generated method stub
		if (resultCode != 0) {
			switch (requestCode) {
			case CONFIG_USER_REGISTER: //當RegisterConfigure.class 回傳result 執行以下
				{
					mUserName = extras.getString("UserName");
					mPresenceServer = extras.getString("PresenceServer");
					register = extras.getBoolean("Register");
					DefaultLat = extras.getDouble("Lat");
					DefaultLng = extras.getDouble("Lng");
					updateView(DefaultLat,DefaultLng,20);
					//registerPresenceServer();
			        String toAddress="sip:s5@140.128.41.151:5065";
			        String toAddress1="sip:s5@10.0.2.2:5065";
			        try{
			        this.presentityNumber=0;
			        sendRegister(toAddress,register);
			        }catch (Throwable e){
			            showAlert("Error", R.drawable.icon,
			    				"Problem sendRegister() not ok: "+e.getMessage(), "Ok",
			    				true);
			        }
				}
				break;
			}
		}
	}

    
    
	//當Options選項被點選時	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		boolean result = super.onOptionsItemSelected(item);
		Intent intent = null;
		switch (item.getItemId()) {
		case 1:
			{
				try {
				intent = new Intent(this, RegisterConfigure.class);   //建立一個intent, 執行RegisterConfigure.class
				//傳入額外資料到該intent裡:  intent.putExtra
				intent.putExtra("UserName", mUserName);
				intent.putExtra("PresenceServer", mPresenceServer);
				intent.putExtra("Lat",DefaultLat.toString());
				intent.putExtra("Lng",DefaultLng.toString());
				//啟動執行一個SubActivity去執行intent 及該SubActivity所代表的數值, 回傳時就是相對應的數值
				startSubActivity(intent, CONFIG_USER_REGISTER);

				}catch (ActivityNotFoundException e) {
					showAlert("Error", R.drawable.icon,
							"Problem launching configuration interface.", "Ok",
							true);
				}

			}
			break;
		case 2:
			{
				 String toAddress="sip:s5@140.128.41.151:5065";
				 try{
				 sendRegister(toAddress,true);
				 System.exit(0);
				 }catch (Throwable e){
			        showAlert("Error", R.drawable.icon,
			    		"Can't Exit: "+e.getMessage(), "Ok",
			    		true);
				 }
				 
			}
			break;
		
		
		}
		return result;

	}
	
	public Context getUIContext() {
		return this;
	}
    
	//點擊 "subscribe"
	private OnClickListener subscribeListener = new OnClickListener() {
		public void onClick(DialogInterface dlg, int resp) {
			String presentityUserName = (String)numberToPresentityUserNameList.get(pressPresentityNumber);
			try{
				sendSubscribe(mPresenceServer,presentityUserName,"123",false);
		
			}catch (Throwable e)
        	{
				showAlert("Error",0,"Subscribe Error","ok",true);
        	}
			//showAlert("Error",0,"Do register","ok",true);
			//m_SipdroidEngine.answercall();  
		}
	};
	
	//點擊 "unSubscribe"
	private OnClickListener unSubscribeListener = new OnClickListener() {
		public void onClick(DialogInterface dlg, int resp) {
			String presentityUserName = (String)numberToPresentityUserNameList.get(pressPresentityNumber);
			try{
				//presentityManager.setPresentitySubscribed(presentityUserName, false);
				sendSubscribe(mPresenceServer,presentityUserName,"123",true);
				
			}catch (Throwable e)
        	{
				showAlert("Error",0,"Subscribe Error: "+ e,"ok",true);
        	}
			//showAlert("Error",0,"Do register","ok",true);
			//m_SipdroidEngine.answercall();  
		}
	};
	
    final Handler presentityHandler = new Handler();
    final Runnable showPresentity = new Runnable()
	{
		public void run()
		{
			if (presentityDialog != null) {
				presentityDialog.cancel();
				presentityDialog = null;
			}
			
			//顯示對話框 ,  Answer 執行onAcceptListener,  Hangup執行onRejectListener
			//presentityDialog = (AlertDialog) getUIContext().showAlert("Watcher: ", 0,
			//		"press: "+pressPresentityNumber, "Subscribe/UnSubscribe", subscribeListener,"Back", null, true, null);
			String presentityUserName = (String)numberToPresentityUserNameList.get(pressPresentityNumber);
			Presentity presentity = presentityManager.getPresentity(presentityUserName);
			if(presentityManager.hasPresentitySubscribed(presentityUserName)){
				presentityDialog = (AlertDialog) getUIContext().showAlert("Presentity: "+overlayNumber, 0, "Name: "+presentity.getUserName()+"\n營業: "+presentity.getPresentityStatus1()+
						"\n狀態: "+presentity.getPresentityStatus2()+"\n其他: "+presentity.getPresentityStatus2(),"Back", null,"Unsubscribe", unSubscribeListener, true, null);
				//presentityDialog = (AlertDialog) getUIContext().showAlert("Watcher: ", 0,
				//		"press: "+pressPresentityNumber+ "presentity presence\nstatus1: "+presentityManager.getPresentityPidf(presentityUserName)+"presentity presence status2: "+ presentityManager.getPresentityStatus2(presentityUserName), "Back", null,"Unsubscribe", null, true, null);
			}else{
				presentityDialog = (AlertDialog) getUIContext().showAlert("Watcher: ", 0,
						"press: "+pressPresentityNumber, "Subscribe", subscribeListener,"Back", null, true, null);
			}
				
		}
	};
	
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	if (keyCode == KeyEvent.KEYCODE_I) {
    		// zoom in            
    		mapConrtoller.setZoom(mapView.getZoomLevel() + 1);
    		return true;
    		}
    	else if (keyCode == KeyEvent.KEYCODE_O) {
    		// zoom out
    		mapView.getController().setZoom(mapView.getZoomLevel() - 1);
    		return true;
    		}
    	else if (keyCode == KeyEvent.KEYCODE_1){
    		pressPresentityNumber =1;
    		presentityHandler.post(showPresentity);  //handler 新增一個處理程序  mIncomingCallCallback
    		
    		return true;
    		}
    	else if (keyCode == KeyEvent.KEYCODE_2){
    		pressPresentityNumber = 2;
    		presentityHandler.post(showPresentity);
    		return true;
    		}
    	return false;
    	
    }
	
    private void updateView(Double lat, Double lng,int ZoomNumber){ 
    	lat = lat*1E6;
    	lng = lng*1E6;
    	GeoPoint point = new GeoPoint(lat.intValue(), lng.intValue());
    	mapConrtoller.setCenter(point);
    	mapConrtoller.setZoom(ZoomNumber);
    }
    
    public void processRequest(RequestEvent evt) {
       // updateView(DefaultLat,DefaultLng,5);
        
    	Request req = evt.getRequest();

    	String method = req.getMethod();
    	
    	ServerTransaction serverTransactionId =
    		evt.getServerTransaction();
    	
    	Response response = null;
    	/*
       	Double lat1=23.697810*1E6;
       	Double lng1=120.963515*1E6;
    	Point point2 = new Point(lat1.intValue(), lng1.intValue());
    	AndroidOverlay overlay = new AndroidOverlay();   
    	  
        overlay.p = point2;
        oc.add(overlay, false); 
       	mapConrtoller.centerMapTo(DefaultPoint, false);
       	*/
    	String presentityUserName= getUsername(getKey(req,"From"));

    	
    	if (method.equals("NOTIFY")){
    		
    		try { //Reply with OK
   
    			if (serverTransactionId == null) {
    				//System.out.println("subscriber:  null TID.");
    				return;
    			}
    			Dialog dialog = serverTransactionId.getDialog();
    			
    			response = messageFactory.createResponse(200,req);
    			serverTransactionId.sendResponse(response);
    			SubscriptionStateHeader subscriptionState =  (SubscriptionStateHeader) 
    						req.getHeader(SubscriptionStateHeader.NAME);
    			// Subscription is terminated.
    			if ( subscriptionState.getState().equals(SubscriptionStateHeader.TERMINATED)) {
    				dialog.delete();
    				//notify all subscriber Subscription is terminated. 移除該presentity所有資訊
    				//processNotifyAll(getUsername(getKey(request,"From")), new String(request.getRawContent()),true);
    				//testString = "do it";
    				presentityManager.setPresentitySubscribed(presentityUserName, false);
    		    	List overlays = (List)oc.getOverlays();
        	    	//int i =2;
        	    	int i = presentityManager.getPresentityNumber(presentityUserName);
        	    	//overlayNumber = i;
        	    	//int ii =1;
        	    	
        	    	AndroidOverlay overlay1 =(AndroidOverlay)overlays.get(i);
        	    	
        	       	overlay1.setState(0);
        	       	//oc.deactivate(overlay1);
        	       	oc.activate(overlay1, false);
        	       	mapConrtoller.setCenter(DefaultPoint);
    				//presentityManager.removePresentity(presentityUserName);
    				//serverSubscribeManager.serverRemovePresentity(getUsername(getKey(request,"From")));
    				return;
    			}else{
    	    	String pidf = new String(req.getRawContent());
    	    	Presentity presentity = (Presentity)presentityManager.getPresentity(presentityUserName);
    	    	if(pidf.equals("null")){
    				if(presentityManager.hasPresentity(presentityUserName)){
    				presentity.setSubscribed(true);
    				presentity.setPresentityStatus1("null");
    				presentity.setPresentityStatus2("null");
    				presentity.setPresentityNote("null");
    				//canvasUpdate(presentityUserName);
    				}
    				//overlayNumber=42;
    			}else{
    				if(presentityManager.hasPresentity(presentityUserName)){
    					//test here ok
    					//overlayNumber=50;
    		    presentityManager.setPresentitySubscribed(presentityUserName, true);
    		   // overlayNumber=51;
    		    presentityManager.setPresentityPidf(presentityUserName, pidf);
    		    //overlayNumber=52;
    		    testPidf= pidf;
    		    //testString = pidfParser.parserTag(pidf, "basic");
    		    presentityManager.setPresentityStatus2(presentityUserName, pidfParser.parserTag(pidf, "rpid:status-icon"));
    		    
    		    presentityManager.setPresentityStatus1(presentityUserName, pidfParser.parserTag(pidf, "basic"));
    		    //overlayNumber=53;
    		    
    		    //overlayNumber=54;
    		    presentityManager.setPresentityNote(presentityUserName, pidfParser.parserTag(pidf, "note"));
    		    //overlayNumber=56;
    		    //presentityManager.printPresentityList();
    		    //canvasUpdate(presentityUserName);
    		    //messageProcessor.presentityUpdate();
    				}
    				//overlayNumber=57;
    			//canvasUpdate(presentityUserName);
    			}
    	    	//overlayNumber=55;
    	    	List overlays = (List)oc.getOverlays();
    	    	//int i =2;
    	    	int i = presentityManager.getPresentityNumber(presentityUserName);
    	    	//overlayNumber = i;
    	    	//int ii =1;
    	    	
    	    	AndroidOverlay overlay1 =(AndroidOverlay)overlays.get(i);
    	    	
    	       	overlay1.setState(presentityManager.getPresentityStatus2(presentityUserName));
    	       	//oc.deactivate(overlay1);
    	       	oc.activate(overlay1, false);
    	       	mapConrtoller.setCenter(DefaultPoint);
    			}
    			//updateView(DefaultLat,DefaultLng,1);
    			//processNotifyAll(getUsername(getKey(request,"From")), new String(request.getRawContent()),false);
    			//processNotify(evt,req, serverTransactionId);
    		} catch (Throwable e) {
    		    e.printStackTrace();
    		   //messageProcessor.processError("Request.NOTIFY Can't send OK reply.");
    		}
    		
    		//updateView(DefaultLat,DefaultLng,10);
			//processNotify(evt,req, serverTransactionId);
		}else if (method.equals("MESSAGE")) {  //分割餐廳資料
    		String totalPresentityUserName = new String(req.getRawContent());
    		//System.out.println(totalPresentityUserName);
    		totalPresentityUserName = totalPresentityUserName.substring(totalPresentityUserName.indexOf("Result:")+7);
    		//System.out.println(totalPresentityUserName);
    		StringTokenizer st = new StringTokenizer(totalPresentityUserName,";");
    		//s1:23.69781,120.960515
    		int count = st.countTokens();
    		String tempPresentity="";
    		while(st.hasMoreTokens()){		
    			
    			//System.out.println(st.nextToken());
    			tempPresentity = st.nextToken().toString(); //s1:23.69781,120.960515
    			String tempPresentityUserName = tempPresentity.substring(0,tempPresentity.indexOf(":")); //s1
    			String tempPresentityLat = tempPresentity.substring(tempPresentity.indexOf(":")+1,tempPresentity.indexOf(",")); //23.69781
    			String tempPresentityLng = tempPresentity.substring(tempPresentity.indexOf(",")+1); //120.960515
    			presentityNumber++;
    			if (presentityManager.hasPresentity(tempPresentityUserName)){
    				
    			}else{
    
    			presentityManager.addPresentity(tempPresentity, tempPresentityUserName, Double.parseDouble(tempPresentityLat), Double.parseDouble(tempPresentityLng),presentityNumber, false);
    			numberToPresentityUserNameList.put(presentityNumber, tempPresentityUserName);
    			//System.out.println("tempPresentityUserName: "+ tempPresentityUserName+ " tempPresentityLat: "+ tempPresentityLat +" tempPresentityLng: "+ tempPresentityLng);
    			//messageProcessor.listAddElement(tempPresentityUserName);
    			drawPresentity(tempPresentityUserName);
    			}
    		}      		
    		
    	}
    	/*
    		updateView(DefaultLat,DefaultLng,10);
    		
    		try { //Reply with OK
    		    response = messageFactory.createResponse(200, req);
    		    ToHeader toHeader = (ToHeader) response.getHeader(ToHeader.NAME);
    		    toHeader.setTag("888"); //This is mandatory as per the spec.
    		    ServerTransaction st = sipProvider.getNewServerTransaction(req);
    		    st.sendResponse(response);
    		} catch (Throwable e) {
    		    e.printStackTrace();
    		   // messageProcessor.processError("Can't send OK reply.");
    			}
    		*/
    	

    }
    
    
    public void processResponse(ResponseEvent evt) {
        //updateView(DefaultLat,DefaultLng,10);
		Response response = (Response) evt.getResponse();
		Transaction tid = evt.getClientTransaction();

    }
    
    public void processTimeout(TimeoutEvent timeoutEvent) {
        showAlert("Error", R.drawable.icon,
				"processTimeout", "Ok",
				true);
    }
    
    /** 
     * This method is called by the SIP stack when there's an asynchronous
     * message transmission error.  
     */
    public void processIOException(IOExceptionEvent evt) {
        showAlert("Error", R.drawable.icon,
				"processIOException", "Ok",
				true);
    }

    /** 
     * This method is called by the SIP stack when a dialog (session) ends. 
     */
    public void processDialogTerminated(DialogTerminatedEvent evt) {
    }

    /** 
     * This method is called by the SIP stack when a transaction ends. 
     */
    public void processTransactionTerminated(TransactionTerminatedEvent evt) {
    }
    
    public static URI getCleanUri(URI uri) {
        if (uri instanceof SipURI) {
            SipURI sipURI=(SipURI)uri.clone();
            
            Iterator iterator=sipURI.getParameterNames();
            while (iterator!=null && iterator.hasNext()) {
                String name=(String)iterator.next();
                sipURI.removeParameter(name);
            }
            return  sipURI;
        }
        else return  uri;
    }
    
    
    //傳入request 及要找的header (From or To), 回傳該header的address
    public  String getKey(Message message,String header) {
        try{
            Address address=null;
            if (header.equals("From") ) {
                FromHeader fromHeader=(FromHeader)message.getHeader(FromHeader.NAME);
                address=fromHeader.getAddress();
                
            }
            else
                if (header.equals("To") ) {
                    ToHeader toHeader=(ToHeader)message.getHeader(ToHeader.NAME);
                    address=toHeader.getAddress();
                    
                }
                
            javax.sip.address.URI  cleanedUri=null;
            if (address==null) {
                cleanedUri= getCleanUri( ((Request)message).getRequestURI());
            }
            else {
                // We have to build the key, all
                // URI parameters MUST be removed:
                cleanedUri = getCleanUri(address.getURI());
            }
            
            if (cleanedUri==null) return null;
            
            String  keyresult=cleanedUri.toString();
            //System.out.println("DEBUG, PresenceServer, getKey(), the key is: " + 
            //keyresult);
            return keyresult.toLowerCase();
            
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    //取得對方的username
    public static String getUsername(String uri) {
    	String username = uri.substring(uri.indexOf(":") + 1,uri.indexOf("@"));	
            return  username;
        }
    
    
    public void sipCreate()  	    
    	throws PeerUnavailableException, TransportNotSupportedException,
    InvalidArgumentException, ObjectInUseException,
    TooManyListenersException 
    {
        SipFactory sipFactory = null;
        sipStack = null;
        sipProvider = null;
        sipFactory = SipFactory.getInstance();
        sipFactory.setPathName("gov.nist");
    	Properties properties = new Properties();
        properties.setProperty("javax.sip.IP_ADDRESS"
        ,ip);
        //properties.setProperty("javax.sip.OUTBOUND_PROXY"
        //,"129.6.50.176:5060/UDP");
        //properties.setProperty("javax.sip.ROUTER_PATH",
        //"src.MyRouter");
        properties.setProperty("javax.sip.STACK_NAME",
        "WatcherClientOnAndroid");
        
    	//DEBUGGING: Information will go to files 
    	//textclient.log and textclientdebug.log
        /*
    	properties.setProperty("gov.nist.javax.sip.TRACE_LEVEL", "32");
    	properties.setProperty("gov.nist.javax.sip.SERVER_LOG",
    		"WatcherClient.txt");
    	properties.setProperty("gov.nist.javax.sip.DEBUG_LOG",
    		"WatcherClientdebug.log");
        
        */
        // Create SipStack object
        sipStack = sipFactory.createSipStack(properties);
        
    	headerFactory = sipFactory.createHeaderFactory();
        addressFactory = sipFactory.createAddressFactory();
        messageFactory = sipFactory.createMessageFactory();
        //ListeningPoint lp  = sipStack.createListeningPoint(5080,"udp");
   	    //System.out.println("listening point created" + lp);
        int port = 7777;
    	ListeningPoint tcp = sipStack.createListeningPoint(port, "tcp");
    	ListeningPoint udp = sipStack.createListeningPoint(port, "udp");

    	sipProvider = sipStack.createSipProvider(tcp);
    	sipProvider.addSipListener(this);
    	sipProvider = sipStack.createSipProvider(udp);
    	sipProvider.addSipListener(this);

        
        /*
        
        try{
        	headerFactory = sipFactory.createHeaderFactory();
            addressFactory = sipFactory.createAddressFactory();
            messageFactory = sipFactory.createMessageFactory();
            ListeningPoint lp  = sipStack.createListeningPoint(5065,"udp");
       	    //System.out.println("listening point created" + lp);
            sipProvider = sipStack.createSipProvider(lp);            
                                           
            sipProvider.addSipListener(this);


                   String fromName       = "s11";
                   String fromSipAddress = "192.168.188.75:5080";
                   String fromDisplayName = "s11";
                   
                   String toSipAddress   = "192.168.188.75:5065";
                   String toUser 	 = "s5";
                   String toDisplayName  = "PresenceServer";
            
            // create >From Header
            SipURI  fromAddress =addressFactory.createSipURI(fromName, fromSipAddress);
            Address fromNameAddress =
                addressFactory.createAddress (fromAddress);
            fromNameAddress.setDisplayName(fromDisplayName);
            FromHeader fromHeader =
                headerFactory.createFromHeader(fromNameAddress,"WatcherClientclientOnAndroidv1.0");

            // create To Header
            SipURI toAddress = addressFactory.createSipURI
            (toUser, toSipAddress);
            Address toNameAddress = addressFactory.createAddress
            (toAddress);
            toNameAddress.setDisplayName(toDisplayName);
            ToHeader toHeader = headerFactory.createToHeader
            (toNameAddress,null);
            
            // create Request URI
            SipURI requestURI = addressFactory.createSipURI(toUser,toSipAddress);
        	requestURI.setTransportParam("udp");
        	
            // Create ViaHeaders
            String transport = "udp";
            
            Vector viaHeaders = new Vector();
            int port = sipProvider.getListeningPoint().getPort();
            ViaHeader viaHeader = headerFactory.createViaHeader
            (sipStack.getIPAddress(),
            sipProvider.getListeningPoint().getPort(),
            transport,null);
            
            // add via headers
            viaHeaders.addElement(viaHeader);
            
            // Create a new CallId header
            CallIdHeader callIdHeader =sipProvider.getNewCallId();
            
            
            // Create a new Cseq header
            CSeqHeader cSeqHeader =
            headerFactory.createCSeqHeader(1,Request.REGISTER);
            
            // Create a new MaxForwardsHeader
            MaxForwardsHeader maxForwards =
            headerFactory.createMaxForwardsHeader(70);
            
    		Request request = messageFactory.createRequest(requestURI,
        			Request.REGISTER, callIdHeader, cSeqHeader, fromHeader,
        			toHeader, viaHeaders, maxForwards);
    		
            // Create contact headers
            String host = sipStack.getIPAddress();
          
            
            // Create the contact name address.
            SipURI contactURI =
            addressFactory.createSipURI (fromName,host); 
            contactURI.setPort(sipProvider.getListeningPoint().getPort());
            
            Address contactAddress =
            addressFactory.createAddress(contactURI);
            
            
            // Add the contact address.
            contactAddress.setDisplayName(fromName);
            
            ContactHeader contactHeader =
            headerFactory.createContactHeader(contactAddress);
            request.addHeader(contactHeader);
            
    		ExpiresHeader expiresHeader = null;//-----------------SUB的期限
    		
    		expiresHeader = headerFactory.createExpiresHeader(600000);//----SUB的期限
    		/*
    		if(unregister== true){
    			expiresHeader = headerFactory.createExpiresHeader(0);//----UNSUB
    		}
    		else{
    			expiresHeader = headerFactory.createExpiresHeader(600000);//----SUB的期限
    		}
    		
    		request.addHeader(expiresHeader);//-----------------SUB的期限

            
            // Create ContentTypeHeader
            ContentTypeHeader contentTypeHeader =
            headerFactory.createContentTypeHeader
            ("text", "plain");
            
    		request.setContent("Watcher:"+DefaultLat+","+DefaultLng, contentTypeHeader);
			

            // Create the client transaction.
            this.inviteTid = sipProvider.getNewClientTransaction(request);
            // send the request out.
            //this.inviteTid.sendRequest();
			
            
        }catch (Exception ex){
            showAlert("Error", R.drawable.icon,
    				"Problem sipCreate1(): "+ex.getMessage(), "Ok",
    				true);
        }
        */
  
        showAlert("Error", R.drawable.icon,
				"sipCreate() OK! ", "Ok",
				true);
   

    }
    
    public void processNotify(RequestEvent requestEvent,
    		Request request,
    		ServerTransaction serverTransactionId) {
    	
    	FromHeader from = (FromHeader) request.getHeader("From");
    	//messageProcessor.processMessage(from.getAddress().toString(),
    	//	new String(request.getRawContent()));
    	String pidf = new String(request.getRawContent());
    	SipProvider sipProvider = (SipProvider) requestEvent.getSource();
    	String presentityUserName= getUsername(getKey(request,"From"));
    	List overlays = (List)oc.getOverlays();
    	//int i =2;
    	int i = presentityManager.getPresentityNumber(presentityUserName);
    	overlayNumber = i;
    	AndroidOverlay overlay1 =(AndroidOverlay)overlays.get(i);
    	//System.out.println(presentityUserName);
    		try {
    			//System.out.println("subscriber:  got a notify .");
    			if (serverTransactionId == null) {
    				//System.out.println("subscriber:  null TID.");
    				return;
    			}
    			Dialog dialog = serverTransactionId.getDialog();
    			//System.out.println("Dialog State = " + dialog.getState());
    			Response response = messageFactory.createResponse(200,request);
    			
    			serverTransactionId.sendResponse(response);
    			SubscriptionStateHeader subscriptionState =  (SubscriptionStateHeader) 
    						request.getHeader(SubscriptionStateHeader.NAME);
    			Presentity presentity = (Presentity)presentityManager.getPresentity(presentityUserName);
    			// Subscription is terminated.
    			if ( subscriptionState.getState().equals(SubscriptionStateHeader.TERMINATED)) {
    				dialog.delete();
    				overlay1.setState(0);
        	       	oc.deactivate(overlay1);
        	       	oc.activate(overlay1, false);
        	       	mapConrtoller.setCenter(DefaultPoint);
    				return;
    				//presentityManager.removePresentity(presentityUserName);
    				//System.out.println("Presentity terminated");
    			}
    			
    			if(pidf.equals("null")){
    				if(presentityManager.hasPresentity(presentityUserName)){
    				presentity.setSubscribed(true);
    				presentity.setPresentityStatus1("null");
    				presentity.setPresentityStatus2("http://www.pu.edu.tw/g9672001/0.jpg");
    				presentity.setPresentityNote("null");
    				//canvasUpdate(presentityUserName);
    				}
    				
    			}else{
    				if(presentityManager.hasPresentity(presentityUserName)){
    		    presentityManager.setPresentitySubscribed(presentityUserName, true);
    		    presentityManager.setPresentityPidf(presentityUserName, pidf);
    		    presentityManager.setPresentityStatus1(presentityUserName, pidfParser.parserTag(pidf, "basic"));
    		    presentityManager.setPresentityStatus2(presentityUserName, pidfParser.parserTag(pidf, "rpid:status-icon"));
    		    presentityManager.setPresentityNote(presentityUserName, pidfParser.parserTag(pidf, "note"));
    		    //presentityManager.printPresentityList();
    		    //canvasUpdate(presentityUserName);
    		    //messageProcessor.presentityUpdate();
    				}
    			//canvasUpdate(presentityUserName);
    		    //presentity.setSubscribed(true);
    			//presentity.setPresentityPidf(pidf);  //設定該presentity的pidf
    			//presentity.setPresentityStatus1(pidfParser.parserTag(pidf, "basic"));
    			//presentity.setPresentityStatus2(pidfParser.parserTag(pidf, "rpid:status-icon"));
    			//presentity.setPresentityNote(pidfParser.parserTag(pidf, "note"));
    			//pidfParser.parserTag(pidf, "basic");
    			//pidfParser.parserTag(pidf, "rpid:status-icon");
    			//pidfParser.parserTag(pidf, "note");
    			}

    	       	
    	       	overlay1.setState(presentityManager.getPresentityStatus2(presentityUserName));
    	       	oc.deactivate(overlay1);
    	       	oc.activate(overlay1, false);
    	       	mapConrtoller.setCenter(DefaultPoint);
    		} catch (Exception ex) {
    			ex.printStackTrace();
    			//System.exit(0);
    	        showAlert("Error", R.drawable.icon,
    					"Recieve Notify Error: "+ ex, "Ok",
    					true);
    		}
    		
    		
    	}
    
    
    public void sendRegister(String to, Boolean unregister) throws ParseException,
    InvalidArgumentException, SipException {
        String fromName       = "s11";
        String fromSipAddress2 = "192.168.188.75:5080";
        String fromSipAddress3 = "10.0.2.2:7777";
        String fromSipAddress = "140.128.41.151:7777";
        String fromSipAddress1 = "10.0.2.15:5080";
        String fromDisplayName = "s11";
        
        String toSipAddress   = "192.168.188.75:5065";
        String toUser 	 = "s5";
        String toDisplayName  = "PresenceServer";
    	SipURI from = addressFactory.createSipURI(fromName, fromSipAddress);
    		Address fromNameAddress = addressFactory.createAddress(from);
    		fromNameAddress.setDisplayName(fromDisplayName);
    		FromHeader fromHeader = headerFactory.createFromHeader(fromNameAddress,
    			"WatcherClientOnAndroid");

    		String username = to.substring(to.indexOf(":") + 1, to.indexOf("@"));
    		String address = to.substring(to.indexOf("@") + 1);

    		SipURI toAddress = addressFactory.createSipURI(username, address);
    		Address toNameAddress = addressFactory.createAddress(toAddress);
    		toNameAddress.setDisplayName(username);
    		ToHeader toHeader = headerFactory.createToHeader(toNameAddress, null);

    		SipURI requestURI = addressFactory.createSipURI(username, address);
    		requestURI.setTransportParam("udp");

    		ArrayList viaHeaders = new ArrayList();
    		ViaHeader viaHeader = headerFactory.createViaHeader(ip,
    			port, "udp", "branch1");
    		viaHeaders.add(viaHeader);

    		CallIdHeader callIdHeader = sipProvider.getNewCallId();

    		CSeqHeader cSeqHeader = headerFactory.createCSeqHeader(1,
    			Request.REGISTER);

    		MaxForwardsHeader maxForwards = headerFactory
    			.createMaxForwardsHeader(70);

    		Request request = messageFactory.createRequest(requestURI,
    			Request.REGISTER, callIdHeader, cSeqHeader, fromHeader,
    			toHeader, viaHeaders, maxForwards);
    		
    		SipURI contactURI = addressFactory.createSipURI(fromName,
    			ip);
    		contactURI.setPort(port);
    		Address contactAddress = addressFactory.createAddress(contactURI);
    		contactAddress.setDisplayName(fromName);
    		ContactHeader contactHeader = headerFactory
    			.createContactHeader(contactAddress);
    		//contactHeader.setExpires(600);
    		request.addHeader(contactHeader);
    		
    		ExpiresHeader expiresHeader = null;//-----------------SUB的期限
    		if(unregister== true){
    			expiresHeader = headerFactory.createExpiresHeader(0);//----UNSUB
    		}
    		else{
    			expiresHeader = headerFactory.createExpiresHeader(600000);//----SUB的期限
    		}
    		
    		request.addHeader(expiresHeader);//-----------------SUB的期限
    		
    		
    		ContentTypeHeader contentTypeHeader = headerFactory
    			.createContentTypeHeader("text", "plain");
    		//request.addHeader(contentTypeHeader);
    		request.setContent("Watcher:"+DefaultLat+","+DefaultLng, contentTypeHeader);
			
    		//sipProvider.sendRequest(request);
    		inviteTid = sipProvider.getNewClientTransaction(request);
    		inviteTid.sendRequest();
    		/*
            showAlert("Error", R.drawable.icon,
    				"process Register1()", "Ok",
    				true);
    				*/
    }
    
    public void sendSubscribe(String to,String presentityUserName, String message, Boolean unsubscribe) throws ParseException,
    InvalidArgumentException, SipException {
    	String fromName       = "s11";
    	String fromSipAddress = "140.128.41.151:7777";
    	String fromDisplayName = "s11";
    	SipURI from = addressFactory.createSipURI(fromName, fromSipAddress);
    	Address fromNameAddress = addressFactory.createAddress(from);
    	fromNameAddress.setDisplayName(fromDisplayName);
    	FromHeader fromHeader = headerFactory.createFromHeader(fromNameAddress,
    	"WatcherClientclientv1.0");

    	String username = presentityUserName;
    	String address = to.substring(to.indexOf("@") + 1);

    	SipURI toAddress = addressFactory.createSipURI(username, address);
    	Address toNameAddress = addressFactory.createAddress(toAddress);
    	toNameAddress.setDisplayName(username);
    	ToHeader toHeader = headerFactory.createToHeader(toNameAddress, null);

    	SipURI requestURI = addressFactory.createSipURI(username, address);
    	requestURI.setTransportParam("udp");

    	ArrayList viaHeaders = new ArrayList();
		ViaHeader viaHeader = headerFactory.createViaHeader(ip,
    			port, "udp", "branch1");
    	viaHeaders.add(viaHeader);

    	CallIdHeader callIdHeader = sipProvider.getNewCallId();

    	CSeqHeader cSeqHeader = headerFactory.createCSeqHeader(1,
    			Request.SUBSCRIBE);

    	MaxForwardsHeader maxForwards = headerFactory
    	.createMaxForwardsHeader(70);

    	Request request = messageFactory.createRequest(requestURI,
    			Request.SUBSCRIBE, callIdHeader, cSeqHeader, fromHeader,
    			toHeader, viaHeaders, maxForwards);

		SipURI contactURI = addressFactory.createSipURI(fromName,
    			ip);
		contactURI.setPort(port);
		Address contactAddress = addressFactory.createAddress(contactURI);
		contactAddress.setDisplayName(fromName);
		ContactHeader contactHeader = headerFactory
		.createContactHeader(contactAddress);
		request.addHeader(contactHeader);
		EventHeader eventHeader = headerFactory.createEventHeader("state");//--SUB
		request.addHeader(eventHeader);//---SUB
		ExpiresHeader expiresHeader = null;//-----------------SUB的期限
		if(unsubscribe== true){
			expiresHeader = headerFactory.createExpiresHeader(0);//----UNSUB
			presentityManager.setPresentitySubscribed(presentityUserName, false);
			//presentityManager.removeAllPresentity();
		}
		else{
			expiresHeader = headerFactory.createExpiresHeader(600000);//----SUB的期限
		}

		request.addHeader(expiresHeader);//-----------------SUB的期限


	ContentTypeHeader contentTypeHeader = headerFactory
		.createContentTypeHeader("text", "plain");
	request.setContent("subscribe", contentTypeHeader);

	sipProvider.sendRequest(request);

	//inviteTid = sipProvider.getNewClientTransaction(request);
	//inviteTid.sendRequest();


    }

    public void drawPresentity(String presentityUserName){
    	Presentity presentity = presentityManager.getPresentity(presentityUserName);
    	Double lat = presentity.getLat()*1E6;
    	Double lng = presentity.getLng()*1E6;
    	GeoPoint point = new GeoPoint(lat.intValue(), lng.intValue());
    	AndroidOverlay overlay = new AndroidOverlay();   
    	  
        overlay.p = point;
        overlay.presentityState=0;
        overlay.presentityNumber = presentity.getPresentityNumber();
        oc.add(overlay, false); 
       	mapConrtoller.setCenter(DefaultPoint);
    	
    }
    
    public void canvasUpdate(String presentityUserName){
    	List overlays = (List)oc.getOverlays();
    	//int i =2;
    	int i = presentityManager.getPresentityNumber(presentityUserName);
    	overlayNumber = i;
    	AndroidOverlay overlay1 =(AndroidOverlay)overlays.get(i);
       	
       	overlay1.setState(presentityManager.getPresentityStatus2(presentityUserName));
       	oc.deactivate(overlay1);
       	oc.activate(overlay1, false);
       	mapConrtoller.setCenter(DefaultPoint);
    }
    public boolean isRouteDisplayed(){
    	return false;
    }
}