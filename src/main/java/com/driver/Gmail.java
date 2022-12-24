package com.driver;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;



public class Gmail extends Email {

	
	
    int inboxCapacity; 
    List<List<String>> inbox;
    List<List<String>> trash ;
    
    
    //maximum number of mails inbox can store
    
   
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    public Gmail(String emailId, int inboxCapacity) {
    	   super(emailId);
    	this.inboxCapacity=inboxCapacity;
    	  this.inbox = new ArrayList<>();
          this.trash = new ArrayList<>();

    }
    
  

    public void receiveMail(Date date, String sender, String message){
    	
    
    	List<String> inboxList = new ArrayList<>();
      
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
        String datestr = dateFormat.format(date);  
    	
    	
    	inboxList.add(datestr);
    	inboxList.add(sender);
    	inboxList.add(message);
    	
         
    	
    	inbox.add(inboxList);
    	
    	
    	
    	  if(inbox.size()==inboxCapacity)
    	  {
    		  
    		  trash.add(inbox.get(inbox.size()-1));
    		inbox.remove(inbox.get(inbox.size()-1));
    		inbox.add(inboxList);
    	  }
    	  
    	  
    	    	
    	
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.

    }

    public void deleteMail(String message){
    	
    	for(int i =0;i<inbox.size();i++)
    	{
    		if(inbox.get(i).get(2).equals(message))
    		{
    			trash.add(inbox.get(i));
    			inbox.remove(inbox.get(i));
    		}
    	}
    	
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing

    }

    public String findLatestMessage(){
    	
    	
    	if(inbox.size()==0)return null;
    	
    	return inbox.get(inbox.size()-1).get(2);
    	
    	
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox

    }

    public String findOldestMessage(){
    	
if(inbox.size()==0)return null;
    	
    	return inbox.get(0).get(2);
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox

    }

    public int findMailsBetweenDates(Date start, Date end) throws Exception {
    	
    	
    	
    	
    	int countNoDate =0; 
    
    	
    	
    	
    	for(int i =0;i<inbox.size();i++)
    	{
    		
    	
    		SimpleDateFormat formatter6=new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    		Date date1=formatter6.parse(inbox.get(i).get(0)); 
    		
    		
    	
    		if(date1.compareTo(start) >= 0 && date1.compareTo(end) <= 0) {
    			countNoDate++;
    		    
    		} 
    	}
    	  
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
    return  countNoDate;
    }

    public int getInboxSize(){
        return inbox.size();

    }

    public int getTrashSize(){
       return trash.size();

    }

    public void emptyTrash(){
       for(int i=0;i<trash.size();i++)
       {
    	   trash.remove(trash.get(i));
       }
    }

    public int getInboxCapacity() {
        return this.inboxCapacity;
    }
}
