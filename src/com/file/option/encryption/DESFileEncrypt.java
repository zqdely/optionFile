package com.file.option.encryption;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DES文件加密/解密  
 * 		加密：需要加密文件输入流inputStream 加密之后文件路径destFile 创建时间createTime
 * 		解密：需要解密文件输出流outputStream 解密之后文件路径destFile 创建时间createTime
 * @author Ally
 *
 */
public class DESFileEncrypt implements FileEncryptInterface {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private Key key;
	
	/**
	 * 根据参数生成key
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
			
			logger.info("生成Key失败：" + e.toString());
			
			 throw new RuntimeException("Error initializing SqlMap class. Cause: " + e);
			 
		}finally{
			
			if(_generator != null){
				
				_generator = null;
				
			}
			
		}
        
	}

	/**
	 * 文件file进行加密并保存目标文件destFile中  
	 * @param inputStream 要加密的文件 如InputStream is = new FileInputStream("c:/test/srcFile.txt")  
	 * @param destFile 加密后存放的文件名 如c:/加密后文件.txt  
	 * @param createTime 创建时间（解密的key）
	 * @throws Exception
	 */
	private void encryptImpl(InputStream inputStream, String destFile, String createTime) throws Exception {
		
		//	    InputStream is = new FileInputStream(file);   
		OutputStream out = null;
		
		CipherInputStream cis = null;
		
		Cipher cipher = Cipher.getInstance(EncodeConstants.Key_DES);
		
		//使用创建时间作为key
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
	 * 文件采用DES算法解密文件
	 * @param outputStream 文件输出流
	 * @param destFile 已加密的文件 如c:/加密后文件.txt 
	 * @param createTime 创建时间（解密的key）
	 * @return
	 * @throws Exception
	 */
	private void decryptImpl(OutputStream outputStream, String destFile, String createTime) throws Exception{
		
		 Cipher cipher = Cipher.getInstance(EncodeConstants.Key_DES); 
		 
		 //使用创建时间作为key
		 getKey(createTime);
		 
		 cipher.init(Cipher.DECRYPT_MODE, this.key);
		 
		 InputStream is = new FileInputStream(destFile);
		 
		 /*//需要一个临时文件转换,此处E:\test\temp.temp
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
			
			logger.info("文件加密失败，原因：" + e.toString());
			
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
			
			logger.info("解密文件失败，原因：" + e.toString());
			
			throw new RuntimeException("Error decrypt file : " + e);
			
		}
		
		return true;
		
	}

}
