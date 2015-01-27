package com.FCCheng.WatcherClientOnAndroid;



import java.util.Collection;
import java.util.Hashtable;
import java.util.Vector;

public class PresentityManager {

    private Hashtable presentityList;
   
    /** Creates new SubscriberController */
    public PresentityManager() {
         presentityList=new Hashtable();
    }
    
    //判斷PresentityManager是否已有該presentity
    public Boolean hasPresentity(String presentityUserName){
    	Presentity presentity=(Presentity)presentityList.get(presentityUserName);
    	if(presentity==null){
    		return false;
    	}else
    		return true;
    }

    
    //新增一個presentity , lat , lng , 是否已訂閱
    public void addPresentity(String presentityURL, String presentityUserName, Double Lat, Double Lng,int presentityNumber, boolean subscribed){
    	Presentity presentity= new Presentity(presentityUserName,presentityURL);
    	//presentity.setUsetName(presentityUserName);
    	presentity.setLat(Lat);
    	presentity.setLng(Lng);
    	presentity.setPresentityNumber(presentityNumber);
    	presentity.setSubscribed(subscribed);
    	presentityList.put(presentityUserName,presentity);
    	//System.out.println("PresenceServer 新增一個presentity: " + presentityURL);
    	//printPresentityList();
    }
    
    //移除指定的presentity
    public void removePresentity(String presentityUserName){
    	 Presentity presentity=(Presentity)presentityList.get(presentityUserName);
    	 presentityList.remove(presentity);
    	 //System.out.println("DEBUG, PresentityManager, removePresentity(), 移除"+presentityUserName);
    	 //printPresentityList();
    }
    
    //取得相對應的presentity的presentityNumber
    public int getPresentityNumber(String presentityUserName){
    	Presentity presentity=(Presentity)presentityList.get(presentityUserName);
    	return presentity.getPresentityNumber();
    }
    
    //依照輸入的presentityUserName 取得想要的presentity
    public Presentity getPresentity(String presentityUserName){
    	Presentity presentity=(Presentity)presentityList.get(presentityUserName);
    	return presentity;
    }
    
    //從presentityList找到名為presentityUserName的presentity 並取得 該presentity的 URI
    public String getPresentityURI(String presentityUserName){
    	Presentity presentity=(Presentity)presentityList.get(presentityUserName);
    	String presentityURI = presentity.getPresentityURL();
    	return presentityURI;
    }
    
    //設定presentity是否已訂閱
    public void setPresentitySubscribed(String presentityUserName, boolean subscribed){
    	Presentity presentity=(Presentity)presentityList.get(presentityUserName);
    	presentity.setSubscribed(subscribed);
    }
    
    //取得該presentity是否已訂閱
    public boolean hasPresentitySubscribed(String presentityUserName){
    	Presentity presentity=(Presentity)presentityList.get(presentityUserName);
    	if(presentity.hasSubscribed()) return true;
    	else return false;
    }
    
    //設定presentity的pidf
    public void setPresentityPidf(String presentityUserName,String pidf){
    	Presentity presentity=(Presentity)presentityList.get(presentityUserName);
    	presentity.setPresentityPidf(pidf);
    }
    
    //取得指定的presentity的pidf
    public String getPresentityPidf(String presentityUserName){
    	Presentity presentity=(Presentity)presentityList.get(presentityUserName);
    	return presentity.getPresentityPidf();
    }
    
    
    //設定presentity的 status1: open/closed
    public void setPresentityStatus1(String presentityUserName,String status1){
    	Presentity presentity=(Presentity)presentityList.get(presentityUserName);
    	presentity.setPresentityStatus1(status1);
    }
    
    //取得指定的presentity的 status1: open/closed
    public String getPresentityStatus1(String presentityUserName){
    	Presentity presentity=(Presentity)presentityList.get(presentityUserName);
    	return presentity.getPresentityStatus1();
    }
    
    //設定presentity的 status2: 未客滿/客滿中
    public void setPresentityStatus2(String presentityUserName,String status2){
    	Presentity presentity=(Presentity)presentityList.get(presentityUserName);
    	presentity.setPresentityStatus2(status2);
    }
    
    //取得指定的presentity的 status2: 未客滿/客滿中
    public int getPresentityStatus2(String presentityUserName){
    	Presentity presentity=(Presentity)presentityList.get(presentityUserName);
    	return presentity.getPresentityStatus2();
    }
    
    //設定presentity的note內容: <note></note>
    public void setPresentityNote(String presentityUserName,String note){
    	Presentity presentity=(Presentity)presentityList.get(presentityUserName);
    	presentity.setPresentityNote(note);
    }
    
    //取得指定的presentity的note內容: <note>xxx</note>
    public String getPresentityNote(String presentityUserName){
    	Presentity presentity=(Presentity)presentityList.get(presentityUserName);
    	return presentity.getPresentityNote();
    }
    
 
   
    /*
    
    //找尋經緯度鄰近的presentityUserName 並回傳
    public String searchPresentity(Double lat, Double lng){
    	String totalPresentityUserName ="";
        Collection collection=presentityList.values();
        Vector presentities=new Vector(collection);
        
        //利用for迴圈檢查所有presentity
        for (int i=0;i<presentities.size();i++) {
            Presentity presentity=(Presentity)presentities.elementAt(i);
            //判斷各個presentity經緯度是否臨近watcher
            Double presentityLat = presentity.getLat();
            Double presentityLng = presentity.getLng();
           
            //System.out.println("pLat: "+ pLat +" pLng: "+ pLng +" wLat: "+ wLat + " wLng: "+ wLng);
            
            
            if (presentityLat>= lat-0.003 && presentityLat<= lat+0.003 &&
            		presentityLng>= lng-0.003 && presentityLng <= lng+0.003)	
            		totalPresentityUserName = totalPresentityUserName + presentity.getUserName()+":"+presentity.getLat()+","+presentity.getLng()+";";
             
        }
    	return totalPresentityUserName;
    }
    */

    //移除所有presentity
    public void removeAllPresentity(){
    	presentityList.clear();
    }
 
    
 
    
    //印出PresentityManager所記錄的各個Presentity
    public void printPresentityList() {
        Collection collection=presentityList.values();
        Vector presentities=new Vector(collection);
        System.out.println();
        System.out.println("************* DEBUG PresentityManager ************************************");
        System.out.println("************* Presentities record:    ************************************");
        System.out.println();
        for (int i=0;i<presentities.size();i++) {
            Presentity presentity=(Presentity)presentities.elementAt(i);
            System.out.println("presentity: "+presentity.getUserName());
            System.out.println("presentity: "+presentity.getPresentityURL());
            System.out.println("presentity has subscribed: "+presentity.hasSubscribed()+"\n");
            System.out.println("    <basic>"+presentity.getPresentityStatus1()+"</basic>\n"+
            "    <rpid:status-icon>"+ presentity.getPresentityStatus2()+"</rpid:status-icon>\n"+
            "    <note>"+ presentity.getPresentityNote()+"</note>");
        
            System.out.println();
        }
        System.out.println("**************************************************************************");
        System.out.println();
    }
    
}