package com.driver;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email {

    private String emailId;
    private String password;

    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String oldPassword, String newPassword){
    	
    	if(oldPassword.equals(this.password))
    	{
    		
    		   Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
    		   Matcher m = p.matcher(newPassword);
    		   boolean b = m.find();
    		
    		
    		boolean upper =false;
    		boolean  lower =false;
    		boolean digit =false;
    		
    		for(int i =0;i<newPassword.length()-1;i++)
    		{
    			char ch = newPassword.charAt(i);
    			
    		   if(ch>='A' && ch<='Z')
    		   {
    			   upper= true;
    		   }
    		   else if(ch>='a' && ch<='z')
    		   {
    			   lower= true;
    		   }
    		   else if(ch>='0' && ch<='9')
    		   {
    			   digit= true;
    		   }
    		   
    		   
    	
    		}
    		
    		if(newPassword.length()>=8  && upper && digit && lower && b)
    		{    		
    			
    			this.password= newPassword;
    		}
    		}
    	
 
    	}
        //Change password only if the oldPassword is equal to current password and the new password meets all of the following:
        // 1. It contains at least 8 characters
        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character
    }

