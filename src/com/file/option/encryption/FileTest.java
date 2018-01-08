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
			
			//生成创建时间存储到数据库，并且将生成时间作为密匙的KEY
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			
			String createTime = sdf.format(new Date());
			//此处应该添加存储数据库的代码
			
			if(file.fileEncryptImpl(is, "E:/test/destFile.enc", createTime))
				
				System.out.println("加密成功");
			else
				System.out.println("加密失败");
			
		} catch (FileNotFoundException e) {
			
			System.out.println("文件加密失败" + e.getMessage());
			
		}finally{
			
			if(is != null){
				
				is.close();
				
			}
			
		}
		
	}*/
	
	public static void main(String[] args) throws IOException {
		
		String decryptPath = "E:/test/destFile.enc";
		
		String key = "2018-01-07 22:14:01.965";
		
		//需要一个临时文件转换,此处E:\test\temp.temp
		OutputStream out = null;
		
		try {
			
			out = new FileOutputStream("E:/test/temp.temp");
			
			FileEncryptInterface file = new DESFileEncrypt();
			
			if(file.fileDecryptImpl(out, decryptPath, key))
				
				System.out.println("解密成功");;
			
		} catch (FileNotFoundException e) {
			
			System.out.println("文件解密失败" + e.getMessage());
			
		}finally{
			
			if(out != null)
				
				out.close();
			
		}
		
	}
}

