package com.file.option.fileUpload;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 * �ļ��ϴ���ָ��Ŀ¼
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
			//�����ļ���
			File filePath = new File(Path);
			//�����ϴ����ļ�
			File file = new File(Path + fileName);
			
			//���ô����ļ��ɹ���ʶ
			boolean createPackage = true;
			boolean createFile = true;
			
			if(!filePath.exists()){
				
				createPackage = filePath.mkdirs();
				
			}
			if(!file.exists()){
				
				createFile = file.mkdirs();
				
			}
			//��ȡ������
			fileOut = new FileOutputStream(file);
			//���ñ����ʽ
			outPut = new OutputStreamWriter(fileOut, charset);
			bufWr = new BufferedWriter(outPut);
			//������д�����
			bufWr.append(fileData);
			bufWr.flush();
			
		} catch (Exception e) {
			System.out.println("�ļ�д������쳣" + e);
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
				System.out.println("�رղ������쳣" + e2);
				throw e2;
			}
			
		}
		
	}
}