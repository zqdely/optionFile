package com.file.option.encryption;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import com.cn.comm.Tools;

/**
 * 文件加密（将文件转换成加密文件加密）
 * @author lenovo
 *
 */
public abstract class FileEncryptForHMAC {

	public static final String KEY_MAC = "HmacMD"; 
	
	public String getClassPath(){
		//类的绝对路径
		return Class.class.getClass().getResource("/").getPath();
		
		//得到工程的路径
		//System.getProperty("user.dir");
		
	}
	  /** 
	   * 初始化HMAC密钥 
	   * 
	   * @return 
	   * @throws Exception 
	   */ 
	  public static String initMacKey() throws Exception { 
	    KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_MAC); 
	    SecretKey secretKey = keyGenerator.generateKey(); 
	    return BASE.encryptBASE(secretKey.getEncoded()); 
	  } 
	  /** 
	   * HMAC加密 ：主要方法
	   * 
	   * @param data 
	   * @param key 
	   * @return 
	   * @throws Exception 
	   */ 
	  public static String encryptHMAC(byte[] data, String key) throws Exception { 
	    SecretKey secretKey = new SecretKeySpec(BASE.decryptBASE(key), KEY_MAC); 
	    Mac mac = Mac.getInstance(secretKey.getAlgorithm()); 
	    mac.init(secretKey); 
	    return new String(mac.doFinal(data)); 
	  } 
	  
	  public String getResult(String inputStr)
	  {
	    String path = getClassPath();
	    String fileSource=path+"/file/HMAC_key.txt";
	    System.out.println("=======加密前的数据:"+inputStr);
	    String result=null;
	    try {
	      byte[] inputData = inputStr.getBytes();
	      String key = FileEncryptForHMAC.initMacKey(); /*产生密钥*/ 
	      System.out.println("Mac密钥:===" + key); 
	      /*将密钥写文件*/
	      Tools.WriteMyFile(fileSource,key);
	      result= FileEncryptForHMAC.encryptHMAC(inputData, key);
	      System.out.println("HMAC加密后:===" + result);
	    } catch (Exception e) {e.printStackTrace();} 
	    return result.toString();
	  }
	  public static String getResult(String inputStr)
	  {
	    System.out.println("=======加密前的数据:"+inputStr);
	     String path=Tools.getClassPath();
	     String fileSource=path+"/file/HMAC_key.txt";
	     String key=null;;
	    try {
	       /*将密钥从文件中读取*/
	       key=Tools.ReadMyFile(fileSource);
	       System.out.println("getResult密钥:===" + key); 
	    } catch (Exception e) {
	      e.printStackTrace();}
	    String result=null;
	    try {
	      byte[] inputData = inputStr.getBytes(); 
	      /*对数据进行加密*/
	      result= FileEncryptForHMAC.encryptHMAC(inputData, key);
	      System.out.println("HMAC加密后:===" + result);
	    } catch (Exception e) {e.printStackTrace();} 
	    return result.toString();
	  }
	  public static void main(String args[])
	  {
	    try {
	       String inputStr = "简单加密";
	       /*使用同一密钥：对数据进行加密：查看两次加密的结果是否一样*/
	       getResult(inputStr);
	       getResult(inputStr);
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	  }
	
}
