package com.FCCheng.WatcherClientOnAndroid;

import java.util.Properties;
import java.util.TooManyListenersException;



import javax.sip.ClientTransaction;
import javax.sip.DialogTerminatedEvent;
import javax.sip.IOExceptionEvent;
import javax.sip.InvalidArgumentException;
import javax.sip.ListeningPoint;
import javax.sip.ObjectInUseException;
import javax.sip.PeerUnavailableException;
import javax.sip.RequestEvent;
import javax.sip.ResponseEvent;
import javax.sip.SipFactory;
import javax.sip.SipListener;
import javax.sip.SipProvider;
import javax.sip.SipStack;
import javax.sip.TimeoutEvent;
import javax.sip.TransactionTerminatedEvent;
import javax.sip.TransportNotSupportedException;
import javax.sip.address.AddressFactory;
import javax.sip.header.HeaderFactory;
import javax.sip.message.MessageFactory;

public class SipLayer implements SipListener {

    private MessageProcessor messageProcessor;

    private String username;

    private SipStack sipStack;

    private SipFactory sipFactory;

    private AddressFactory addressFactory;

    private HeaderFactory headerFactory;

    private MessageFactory messageFactory;

    private SipProvider sipProvider;
    
    ClientTransaction subscribeTid;
    
    Double Lat;  //此Watcher的經度
    
    Double Lng;  //此Watcher的緯度
    
    PidfParser pidfParser;
    
    PresentityManager presentityManager;
    
    /** Here we initialize the SIP stack. */
    public SipLayer(String username, String ip, int port, Double lat, Double lng, PresentityManager presentityManager)
	    throws PeerUnavailableException, TransportNotSupportedException,
	    InvalidArgumentException, ObjectInUseException,
	    TooManyListenersException {
    	
    	sipFactory = SipFactory.getInstance();
    	sipFactory.setPathName("gov.nist");
    	Properties properties = new Properties();
    	properties.setProperty("javax.sip.STACK_NAME", "WatcherClient");
    	properties.setProperty("javax.sip.IP_ADDRESS", ip);
    	
    	sipStack = sipFactory.createSipStack(properties);
    	headerFactory = sipFactory.createHeaderFactory();
    	addressFactory = sipFactory.createAddressFactory();
    	messageFactory = sipFactory.createMessageFactory();

    	//ListeningPoint tcp = sipStack.createListeningPoint(port, "tcp");
    	//ListeningPoint udp = sipStack.createListeningPoint(port, "udp");
    	ListeningPoint udp = sipStack.createListeningPoint(ip, port, "udp");
    	//sipProvider = sipStack.createSipProvider(tcp);
    	//sipProvider.addSipListener(this);
    	sipProvider = sipStack.createSipProvider(udp);
    	sipProvider.addSipListener(this);
    	
    }
    
    public void processRequest(RequestEvent evt) {
    	
    }
    
    public void processResponse(ResponseEvent evt) {
    	
    }
    
    public void processTimeout(TimeoutEvent evt) {
    	//messageProcessor
    	//	.processError("Previous message not sent: " + "timeout");
    }

    /** 
     * This method is called by the SIP stack when there's an asynchronous
     * message transmission error.  
     */
    public void processIOException(IOExceptionEvent evt) {
    	//messageProcessor.processError("Previous message not sent: "
    	//	+ "I/O Exception");
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
    
}
