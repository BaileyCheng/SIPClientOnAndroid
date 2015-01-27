package com.FCCheng.WatcherClientOnAndroid;

public class Presentity {

    //private Hashtable subscriberList;
    private String presentityURL;
    private Double Lat;  //�g�� : 23.697810
    private Double Lng;  //�n�� : 120.960515
    private String presentityUserName;
    private String pidf; 
    private String status1;
    private int status2;
    private String note;
    boolean subscribed = false; //�O�_�q�\
    int presentityNumber;
    //private PIDF pidf;
    /** Creates new Presentity */
    public Presentity(String username, String presentityURL) {
        //subscriberList=new Hashtable();
        //pidf = new PIDF(); 
        this.presentityUserName = username;
        this.presentityURL=presentityURL;
    }

    //�]�w��presentity���s�� �H������overlays���s��
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
    
    //�]�w��presentity�O�_�q�\
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
    
    //�]�w��presentity�� username  ex: s1
    public void setUsetName(String username){
    	this.presentityUserName = username;
    }
    
    //���o��presentity��username
    public String getUserName(){
    	return this.presentityUserName;
    }
    
    //�]�w��presentity��pidf
    public void setPresentityPidf(String pidf){
    	this.pidf = pidf;
    }
    
    //���o��presentity��pidf
    public String getPresentityPidf(){
    	return this.pidf;
    }
    
    //�]�w��presentity pidf��status1 : open/closed
    public void setPresentityStatus1(String status1){
    	this.status1 = status1;
    	if(status1.equals("closed"))
    		this.status2 = 3; //����
    }
    
    //���o��presentity pidf��status1 : open/closed
    public String getPresentityStatus1(){
    	return this.status1;
    }
    
    //�]�w��presentity pidf��status2 : ���Ⱥ�/�Ⱥ���
    public void setPresentityStatus2(String status2){
    	if(status2.equals("http://www.pu.edu.tw/g9672001/0.jpg")){
    		this.status2 = 1;  //���Ⱥ�  ���
    	}else if (status2.equals("http://www.pu.edu.tw/g9672001/1.jpg")){
    		this.status2 = 2;  //�Ⱥ� ����
    	}else if (status2.equals("http://www.pu.edu.tw/g9672001/2.jpg")){
    		this.status2 = 3;
    	}else if(status2.equals("null")){
    		this.status2 = 4;
    	}else{
    	this.status2 = 0;
    	}
    }
    
    //���o��presentity pidf��status2 : ���Ⱥ�/�Ⱥ���
    public int getPresentityStatus2(){
    	return this.status2;
    }
    
    //�]�w��presentity��note���e: <note> </note>
    public void setPresentityNote(String note){
    	this.note = note;
    }
    
    //���o��presentity��note���e: <note>xxx</note>
    public String getPresentityNote(){
    	return this.note;
    }

}
