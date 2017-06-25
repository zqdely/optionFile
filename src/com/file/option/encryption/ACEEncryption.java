package com.file.option.encryption;

import java.math.BigInteger;  
import java.security.MessageDigest;  
import java.security.SecureRandom;  
  
import javax.crypto.Cipher;  
import javax.crypto.KeyGenerator;  
import javax.crypto.spec.SecretKeySpec;

import com.file.option.encryption.QStringUtil;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;

/** 
 * ���빤���� 
 * 1.��byte[]תΪ���ֽ��Ƶ��ַ��� 
 * 2.base 64 encode 
 * 3.base 64 decode 
 * 4.��ȡbyte[]��md5ֵ 
 * 5.��ȡ�ַ���md5ֵ 
 * 6.���base64ʵ��md5���� 
 * 7.AES���� 
 * 8.AES����Ϊbase 64 code 
 * 9.AES���� 
 * 10.��base 64 code AES���� 
 * @author uikoo9 
 * @version 0.0.7.20140601 
 */  
public class ACEEncryption {

	public static void main(String[] args) throws Exception {  
        String content = "�Ұ���";  
        System.out.println("����ǰ��" + content);  
  
        String key = "123456";  
        System.out.println("������Կ�ͽ�����Կ��" + key);  
          
        String encrypt = aesEncrypt(content, key);  
        System.out.println("���ܺ�" + encrypt);  
          
        String decrypt = aesDecrypt(encrypt, key);  
        System.out.println("���ܺ�" + decrypt);  
    }  
      
    /** 
     * ��byte[]תΪ���ֽ��Ƶ��ַ��� 
     * @param bytes byte[] 
     * @param radix ����ת�����Ƶķ�Χ����Character.MIN_RADIX��Character.MAX_RADIX��������Χ���Ϊ10���� 
     * @return ת������ַ��� 
     */  
    public static String binary(byte[] bytes, int radix){  
        return new BigInteger(1, bytes).toString(radix);// �����1��������  
    }  
      
    /** 
     * base 64 encode 
     * @param bytes �������byte[] 
     * @return ������base 64 code 
     */  
    public static String base64Encode(byte[] bytes){  
        return new BASE64Encoder().encode(bytes);  
    }  
      
    /** 
     * base 64 decode 
     * @param base64Code �������base 64 code 
     * @return ������byte[] 
     * @throws Exception 
     */  
    public static byte[] base64Decode(String base64Code) throws Exception{  
        return QStringUtil.isEmpty(base64Code) ? null : new BASE64Decoder().decodeBuffer(base64Code);  
    }  
      
    /** 
     * ��ȡbyte[]��md5ֵ 
     * @param bytes byte[] 
     * @return md5 
     * @throws Exception 
     */  
    public static byte[] md5(byte[] bytes) throws Exception {  
        MessageDigest md = MessageDigest.getInstance("MD5");  
        md.update(bytes);  
          
        return md.digest();  
    }  
      
    /** 
     * ��ȡ�ַ���md5ֵ 
     * @param msg  
     * @return md5 
     * @throws Exception 
     */  
    public static byte[] md5(String msg) throws Exception {  
        return QStringUtil.isEmpty(msg) ? null : md5(msg.getBytes());  
    }  
      
    /** 
     * ���base64ʵ��md5���� 
     * @param msg �������ַ��� 
     * @return ��ȡmd5��תΪbase64 
     * @throws Exception 
     */  
    public static String md5Encrypt(String msg) throws Exception{  
        return QStringUtil.isEmpty(msg) ? null : base64Encode(md5(msg));  
    }  
      
    /** 
     * AES���� 
     * @param content �����ܵ����� 
     * @param encryptKey ������Կ 
     * @return ���ܺ��byte[] 
     * @throws Exception 
     */  
    public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {  
        KeyGenerator kgen = KeyGenerator.getInstance("AES");  
        kgen.init(128, new SecureRandom(encryptKey.getBytes()));  
  
        Cipher cipher = Cipher.getInstance("AES");  
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));  
          
        return cipher.doFinal(content.getBytes("utf-8"));  
    }  
      
    /** 
     * AES����Ϊbase 64 code 
     * @param content �����ܵ����� 
     * @param encryptKey ������Կ 
     * @return ���ܺ��base 64 code 
     * @throws Exception 
     */  
    public static String aesEncrypt(String content, String encryptKey) throws Exception {  
        return base64Encode(aesEncryptToBytes(content, encryptKey));  
    }  
      
    /** 
     * AES���� 
     * @param encryptBytes �����ܵ�byte[] 
     * @param decryptKey ������Կ 
     * @return ���ܺ��String 
     * @throws Exception 
     */  
    public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {  
        KeyGenerator kgen = KeyGenerator.getInstance("AES");  
        kgen.init(128, new SecureRandom(decryptKey.getBytes()));  
          
        Cipher cipher = Cipher.getInstance("AES");  
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));  
        byte[] decryptBytes = cipher.doFinal(encryptBytes);  
          
        return new String(decryptBytes, "utf-8");  
    }  
      
    /** 
     * ��base 64 code AES���� 
     * @param encryptStr �����ܵ�base 64 code 
     * @param decryptKey ������Կ 
     * @return ���ܺ��string 
     * @throws Exception 
     */  
    public static String aesDecrypt(String encryptStr, String decryptKey) throws Exception {  
        return QStringUtil.isEmpty(encryptStr) ? null : aesDecryptByBytes(base64Decode(encryptStr), decryptKey);  
    }  
	
}