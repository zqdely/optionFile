package com.file.option.fileUpload;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 * 文件上传到指定目录
 * @author lenovo
 *
 */
public class FilesUpload {
	public void saveFiles(String fileData, String Path, String fileName, String charset) throws Exception{
		
		FileOutputStream  fileOut = null;
		OutputStreamWriter outPut = null;
		BufferedWriter bufWr = null;
		if(charset == null || charset.trim().equals("")){
			charset = "utf-8";
		}
		try {
			//创建文件夹
			File filePath = new File(Path);
			//创建上传的文件
			File file = new File(Path + fileName);
			
			//设置创建文件成功标识
			boolean createPackage = true;
			boolean createFile = true;
			
			if(!filePath.exists()){
				
				createPackage = filePath.mkdirs();
				
			}
			if(!file.exists()){
				
				createFile = file.mkdirs();
				
			}
			//获取处理流
			fileOut = new FileOutputStream(file);
			//设置编码格式
			outPut = new OutputStreamWriter(fileOut, charset);
			bufWr = new BufferedWriter(outPut);
			//将内容写入操作
			bufWr.append(fileData);
			bufWr.flush();
			
		} catch (Exception e) {
			System.out.println("文件写入操作异常" + e);
			throw e;
		}finally{
			
			try {
				
				if(bufWr != null){
					bufWr.close();
				}
				if(outPut != null){
					outPut.close();
				}
				if(fileOut != null){
					fileOut.close();
				}
				
			} catch (Exception e2) {
				System.out.println("关闭操作流异常" + e2);
				throw e2;
			}
			
		}
		
	}
}
