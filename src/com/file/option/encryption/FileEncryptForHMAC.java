package com.file.option.encryption;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import com.cn.comm.Tools;

/**
 * �ļ����ܣ����ļ�ת���ɼ����ļ����ܣ�
 * @author lenovo
 *
 */
public abstract class FileEncryptForHMAC {

	public static final String KEY_MAC = "HmacMD"; 
	
	public String getClassPath(){
		//��ľ���·��
		return Class.class.getClass().getResource("/").getPath();
		
		//�õ����̵�·��
		//System.getProperty("user.dir");
		
	}
	  /** 
	   * ��ʼ��HMAC��Կ 
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
	   * HMAC���� ����Ҫ����
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
	    System.out.println("=======����ǰ������:"+inputStr);
	    String result=null;
	    try {
	      byte[] inputData = inputStr.getBytes();
	      String key = FileEncryptForHMAC.initMacKey(); /*������Կ*/ 
	      System.out.println("Mac��Կ:===" + key); 
	      /*����Կд�ļ�*/
	      Tools.WriteMyFile(fileSource,key);
	      result= FileEncryptForHMAC.encryptHMAC(inputData, key);
	      System.out.println("HMAC���ܺ�:===" + result);
	    } catch (Exception e) {e.printStackTrace();} 
	    return result.toString();
	  }
	  public static String getResult(String inputStr)
	  {
	    System.out.println("=======����ǰ������:"+inputStr);
	     String path=Tools.getClassPath();
	     String fileSource=path+"/file/HMAC_key.txt";
	     String key=null;;
	    try {
	       /*����Կ���ļ��ж�ȡ*/
	       key=Tools.ReadMyFile(fileSource);
	       System.out.println("getResult��Կ:===" + key); 
	    } catch (Exception e) {
	      e.printStackTrace();}
	    String result=null;
	    try {
	      byte[] inputData = inputStr.getBytes(); 
	      /*�����ݽ��м���*/
	      result= FileEncryptForHMAC.encryptHMAC(inputData, key);
	      System.out.println("HMAC���ܺ�:===" + result);
	    } catch (Exception e) {e.printStackTrace();} 
	    return result.toString();
	  }
	  public static void main(String args[])
	  {
	    try {
	       String inputStr = "�򵥼���";
	       /*ʹ��ͬһ��Կ�������ݽ��м��ܣ��鿴���μ��ܵĽ���Ƿ�һ��*/
	       getResult(inputStr);
	       getResult(inputStr);
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	  }
	
}