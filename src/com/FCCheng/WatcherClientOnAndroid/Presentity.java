package com.FCCheng.WatcherClientOnAndroid;

public class Presentity {

    //private Hashtable subscriberList;
    private String presentityURL;
    private Double Lat;  //經度 : 23.697810
    private Double Lng;  //緯度 : 120.960515
    private String presentityUserName;
    private String pidf; 
    private String status1;
    private int status2;
    private String note;
    boolean subscribed = false; //是否訂閱
    int presentityNumber;
    //private PIDF pidf;
    /** Creates new Presentity */
    public Presentity(String username, String presentityURL) {
        //subscriberList=new Hashtable();
        //pidf = new PIDF(); 
        this.presentityUserName = username;
        this.presentityURL=presentityURL;
    }

    //設定該presentity的編號 以此對應overlays的編號
    public void setPresentityNumber(int presentityNumber){
    	this.presentityNumber = presentityNumber;
    }
    
    public int getPresentityNumber(){
    	return this.presentityNumber;
    }
    
    public String getPresentityURL() {
        return presentityURL;
    }
    
    public void setPresentityURL(String url) {
        this.presentityURL= url;
    }
    
    //設定該presentity是否訂閱
    public void setSubscribed(boolean Subscribed){
    	subscribed =Subscribed;
    }
    
    public boolean hasSubscribed(){
    	if(subscribed)return true;
    	else return false;
    }


    
    public Double getLat(){
    	return this.Lat;
    }
    
    public Double getLng(){
    	return this.Lng;
    }
    
    public void setLat(Double lat){
    	this.Lat = lat;
    }
    
    public void setLng(Double lng){
    	this.Lng = lng;
    }
    
    //設定該presentity的 username  ex: s1
    public void setUsetName(String username){
    	this.presentityUserName = username;
    }
    
    //取得該presentity的username
    public String getUserName(){
    	return this.presentityUserName;
    }
    
    //設定該presentity的pidf
    public void setPresentityPidf(String pidf){
    	this.pidf = pidf;
    }
    
    //取得該presentity的pidf
    public String getPresentityPidf(){
    	return this.pidf;
    }
    
    //設定該presentity pidf的status1 : open/closed
    public void setPresentityStatus1(String status1){
    	this.status1 = status1;
    	if(status1.equals("closed"))
    		this.status2 = 3; //紅色
    }
    
    //取得該presentity pidf的status1 : open/closed
    public String getPresentityStatus1(){
    	return this.status1;
    }
    
    //設定該presentity pidf的status2 : 未客滿/客滿中
    public void setPresentityStatus2(String status2){
    	if(status2.equals("http://www.pu.edu.tw/g9672001/0.jpg")){
    		this.status2 = 1;  //未客滿  綠色
    	}else if (status2.equals("http://www.pu.edu.tw/g9672001/1.jpg")){
    		this.status2 = 2;  //客滿 黃色
    	}else if (status2.equals("http://www.pu.edu.tw/g9672001/2.jpg")){
    		this.status2 = 3;
    	}else if(status2.equals("null")){
    		this.status2 = 4;
    	}else{
    	this.status2 = 0;
    	}
    }
    
    //取得該presentity pidf的status2 : 未客滿/客滿中
    public int getPresentityStatus2(){
    	return this.status2;
    }
    
    //設定該presentity的note內容: <note> </note>
    public void setPresentityNote(String note){
    	this.note = note;
    }
    
    //取得該presentity的note內容: <note>xxx</note>
    public String getPresentityNote(){
    	return this.note;
    }

}
