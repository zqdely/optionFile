package com.file.option.encryption;

import java.math.BigInteger;
import java.security.MessageDigest;

public class SHA {
	
	 public static final String KEY_SHA = "SHA"; 
	  public static String getResult(String inputStr)
	  {
	    BigInteger sha =null;
	    System.out.println("=======����ǰ������:"+inputStr);
	    byte[] inputData = inputStr.getBytes(); 
	    try {
	       MessageDigest messageDigest = MessageDigest.getInstance(KEY_SHA); 
	       messageDigest.update(inputData);
	       sha = new BigInteger(messageDigest.digest()); 
	       System.out.println("SHA���ܺ�:" + sha.toString()); 
	    } catch (Exception e) {e.printStackTrace();}
	    return sha.toString();
	  }
	  
	  
	  public static void main(String args[])
	  {
	    try {
	       String inputStr = "�򵥼���"; 
	       getResult(inputStr);
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	  }

}