package com.file.option.encryption;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DESFileEncrypt implements FileEncryptInterface {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private Key key;
	
	/**
	 * ���ݲ�������key
	 * @param strKey
	 * @return
	 */
	private void getKey(String strKey) {
		
		KeyGenerator _generator = null;
		
		try {
			
			_generator = KeyGenerator.getInstance(EncodeConstants.Key_DES);
			
			_generator.init(new SecureRandom(strKey.getBytes()));   
			
			this.key = _generator.generateKey();
			
		} catch (NoSuchAlgorithmException e) {
			
			logger.info("����Keyʧ�ܣ�" + e.toString());
			
			 throw new RuntimeException("Error initializing SqlMap class. Cause: " + e);
			 
		}finally{
			
			if(_generator != null){
				
				_generator = null;
				
			}
			
		}
        
	}

	/**
	 * �ļ�file���м��ܲ�����Ŀ���ļ�destFile��  
	 * @param inputStream Ҫ���ܵ��ļ� ��InputStream is = new FileInputStream("c:/test/srcFile.txt")  
	 * @param destFile ���ܺ��ŵ��ļ��� ��c:/���ܺ��ļ�.txt  
	 * @param createTime ����ʱ�䣨���ܵ�key��
	 * @throws Exception
	 */
	private void encryptImpl(InputStream inputStream, String destFile, String createTime) throws Exception {
		
		//	    InputStream is = new FileInputStream(file);   
		OutputStream out = null;
		
		CipherInputStream cis = null;
		
		Cipher cipher = Cipher.getInstance(EncodeConstants.Key_DES);
		
		//ʹ�ô���ʱ����Ϊkey
		getKey(createTime);
		
		cipher.init(Cipher.ENCRYPT_MODE, this.key);  
		
		out = new FileOutputStream(destFile);   
		
		cis = new CipherInputStream(inputStream, cipher);  
		
		byte[] buffer = new byte[1024];   
		
		int r;
		
		while ((r = cis.read(buffer)) > 0) { 
			
		    out.write(buffer, 0, r);  
		    
		}
				
		if(cis != null){
			
			 cis.close();   
			
		}
		
		if(out != null){
			
			out.close();
			
		}
				
	}
	
	/**
	 * �ļ�����DES�㷨�����ļ�
	 * @param outputStream �ļ������
	 * @param destFile �Ѽ��ܵ��ļ� ��c:/���ܺ��ļ�.txt 
	 * @param createTime ����ʱ�䣨���ܵ�key��
	 * @return
	 * @throws Exception
	 */
	private void decryptImpl(OutputStream outputStream, String destFile, String createTime) throws Exception{
		
		 Cipher cipher = Cipher.getInstance(EncodeConstants.Key_DES); 
		 
		 //ʹ�ô���ʱ����Ϊkey
		 getKey(createTime);
		 
		 cipher.init(Cipher.DECRYPT_MODE, this.key);
		 
		 InputStream is = new FileInputStream(destFile);
		 
		 /*//��Ҫһ����ʱ�ļ�ת��,�˴�E:\test\temp.temp
		 OutputStream out = new FileOutputStream("E:/test/temp.temp");*/ 
		 
		 CipherOutputStream cos = new CipherOutputStream(outputStream, cipher);
		 
		 byte[] buffer = new byte[1024];   
		 
		 int r;
		 
		 while ((r = is.read(buffer)) >= 0) {
			 
		     cos.write(buffer, 0, r);
		     
		 } 
		 
		 if(cos != null)
		
			 cos.close();
		 
		 if(is != null)
			 
			 is.close();
	    
	}

	@Override
	public boolean fileEncryptImpl(InputStream inputStream, String destFile, String createTime) {
		
		try{
			
			encryptImpl(inputStream, destFile, createTime);
			
		} catch (Exception e) {
			
			logger.info("�ļ�����ʧ�ܣ�ԭ��" + e.toString());
			
			//throw new RuntimeException("Error for FileEncrypt: " + e);
			
			return false;
			
		} 
		
		return true;
	}

	@Override
	public boolean fileDecryptImpl(OutputStream outputStream, String destFile, String createTime) {
		
		try {
			
			decryptImpl(outputStream, destFile, createTime);
			
		} catch (Exception e) {
			
			logger.info("�����ļ�ʧ�ܣ�ԭ��" + e.toString());
			
			throw new RuntimeException("Error decrypt file : " + e);
			
		}
		
		return true;
		
	}

}