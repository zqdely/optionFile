package com.file.option.encryption;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileTest {

	/**
	 * @param args
	 * @throws IOException 
	 */
	/*public static void main(String[] args) throws IOException {

		String fileName = "E:/test/new.txt";
		
		InputStream is = null;
		
		try {
			
			is = new FileInputStream(fileName);
			
			FileEncryptInterface file = new DESFileEncrypt();
			
			//���ɴ���ʱ��洢�����ݿ⣬���ҽ�����ʱ����Ϊ�ܳ׵�KEY
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			
			String createTime = sdf.format(new Date());
			//�˴�Ӧ�����Ӵ洢���ݿ�Ĵ���
			
			if(file.fileEncryptImpl(is, "E:/test/destFile.enc", createTime))
				
				System.out.println("���ܳɹ�");
			else
				System.out.println("����ʧ��");
			
		} catch (FileNotFoundException e) {
			
			System.out.println("�ļ�����ʧ��" + e.getMessage());
			
		}finally{
			
			if(is != null){
				
				is.close();
				
			}
			
		}
		
	}*/
	
	public static void main(String[] args) throws IOException {
		
		String decryptPath = "E:/test/destFile.enc";
		
		String key = "2018-01-07 22:14:01.965";
		
		//��Ҫһ����ʱ�ļ�ת��,�˴�E:\test\temp.temp
		OutputStream out = null;
		
		try {
			
			out = new FileOutputStream("E:/test/temp.temp");
			
			FileEncryptInterface file = new DESFileEncrypt();
			
			if(file.fileDecryptImpl(out, decryptPath, key))
				
				System.out.println("���ܳɹ�");;
			
		} catch (FileNotFoundException e) {
			
			System.out.println("�ļ�����ʧ��" + e.getMessage());
			
		}finally{
			
			if(out != null)
				
				out.close();
			
		}
		
	}
}
