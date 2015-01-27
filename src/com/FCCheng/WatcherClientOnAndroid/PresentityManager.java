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
    
    //�P�_PresentityManager�O�_�w����presentity
    public Boolean hasPresentity(String presentityUserName){
    	Presentity presentity=(Presentity)presentityList.get(presentityUserName);
    	if(presentity==null){
    		return false;
    	}else
    		return true;
    }

    
    //�s�W�@��presentity , lat , lng , �O�_�w�q�\
    public void addPresentity(String presentityURL, String presentityUserName, Double Lat, Double Lng,int presentityNumber, boolean subscribed){
    	Presentity presentity= new Presentity(presentityUserName,presentityURL);
    	//presentity.setUsetName(presentityUserName);
    	presentity.setLat(Lat);
    	presentity.setLng(Lng);
    	presentity.setPresentityNumber(presentityNumber);
    	presentity.setSubscribed(subscribed);
    	presentityList.put(presentityUserName,presentity);
    	//System.out.println("PresenceServer �s�W�@��presentity: " + presentityURL);
    	//printPresentityList();
    }
    
    //�������w��presentity
    public void removePresentity(String presentityUserName){
    	 Presentity presentity=(Presentity)presentityList.get(presentityUserName);
    	 presentityList.remove(presentity);
    	 //System.out.println("DEBUG, PresentityManager, removePresentity(), ����"+presentityUserName);
    	 //printPresentityList();
    }
    
    //���o�۹�����presentity��presentityNumber
    public int getPresentityNumber(String presentityUserName){
    	Presentity presentity=(Presentity)presentityList.get(presentityUserName);
    	return presentity.getPresentityNumber();
    }
    
    //�̷ӿ�J��presentityUserName ���o�Q�n��presentity
    public Presentity getPresentity(String presentityUserName){
    	Presentity presentity=(Presentity)presentityList.get(presentityUserName);
    	return presentity;
    }
    
    //�qpresentityList���W��presentityUserName��presentity �è��o ��presentity�� URI
    public String getPresentityURI(String presentityUserName){
    	Presentity presentity=(Presentity)presentityList.get(presentityUserName);
    	String presentityURI = presentity.getPresentityURL();
    	return presentityURI;
    }
    
    //�]�wpresentity�O�_�w�q�\
    public void setPresentitySubscribed(String presentityUserName, boolean subscribed){
    	Presentity presentity=(Presentity)presentityList.get(presentityUserName);
    	presentity.setSubscribed(subscribed);
    }
    
    //���o��presentity�O�_�w�q�\
    public boolean hasPresentitySubscribed(String presentityUserName){
    	Presentity presentity=(Presentity)presentityList.get(presentityUserName);
    	if(presentity.hasSubscribed()) return true;
    	else return false;
    }
    
    //�]�wpresentity��pidf
    public void setPresentityPidf(String presentityUserName,String pidf){
    	Presentity presentity=(Presentity)presentityList.get(presentityUserName);
    	presentity.setPresentityPidf(pidf);
    }
    
    //���o���w��presentity��pidf
    public String getPresentityPidf(String presentityUserName){
    	Presentity presentity=(Presentity)presentityList.get(presentityUserName);
    	return presentity.getPresentityPidf();
    }
    
    
    //�]�wpresentity�� status1: open/closed
    public void setPresentityStatus1(String presentityUserName,String status1){
    	Presentity presentity=(Presentity)presentityList.get(presentityUserName);
    	presentity.setPresentityStatus1(status1);
    }
    
    //���o���w��presentity�� status1: open/closed
    public String getPresentityStatus1(String presentityUserName){
    	Presentity presentity=(Presentity)presentityList.get(presentityUserName);
    	return presentity.getPresentityStatus1();
    }
    
    //�]�wpresentity�� status2: ���Ⱥ�/�Ⱥ���
    public void setPresentityStatus2(String presentityUserName,String status2){
    	Presentity presentity=(Presentity)presentityList.get(presentityUserName);
    	presentity.setPresentityStatus2(status2);
    }
    
    //���o���w��presentity�� status2: ���Ⱥ�/�Ⱥ���
    public int getPresentityStatus2(String presentityUserName){
    	Presentity presentity=(Presentity)presentityList.get(presentityUserName);
    	return presentity.getPresentityStatus2();
    }
    
    //�]�wpresentity��note���e: <note></note>
    public void setPresentityNote(String presentityUserName,String note){
    	Presentity presentity=(Presentity)presentityList.get(presentityUserName);
    	presentity.setPresentityNote(note);
    }
    
    //���o���w��presentity��note���e: <note>xxx</note>
    public String getPresentityNote(String presentityUserName){
    	Presentity presentity=(Presentity)presentityList.get(presentityUserName);
    	return presentity.getPresentityNote();
    }
    
 
   
    /*
    
    //��M�g�n�׾F��presentityUserName �æ^��
    public String searchPresentity(Double lat, Double lng){
    	String totalPresentityUserName ="";
        Collection collection=presentityList.values();
        Vector presentities=new Vector(collection);
        
        //�Q��for�j���ˬd�Ҧ�presentity
        for (int i=0;i<presentities.size();i++) {
            Presentity presentity=(Presentity)presentities.elementAt(i);
            //�P�_�U��presentity�g�n�׬O�_�{��watcher
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

    //�����Ҧ�presentity
    public void removeAllPresentity(){
    	presentityList.clear();
    }
 
    
 
    
    //�L�XPresentityManager�ҰO�����U��Presentity
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