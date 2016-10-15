package com.file.option.packZip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;

/**
 * @Title: GZIPUtil.java
 * @Description: gzip文件压缩和解压缩工具类
 * @author Ally
 * @date 2009-11-4 下午06:23:29
 * @version V1.0
 */
public class GZIPUtil {
 
	/**
	* 
	* @Title: pack
	* @Description: 将一组文件打成tar包
	* @param sources 要打包的原文件数组
	* @param target 打包后的文件
	* @return File    返回打包后的文件
	* @throws
	*/
	public static File pack(File[] sources, File target){
	FileOutputStream out = null;
	try {
		out = new FileOutputStream(target);
	} catch (FileNotFoundException e1) {
		e1.printStackTrace();
	}
	TarArchiveOutputStream os = new TarArchiveOutputStream(out);
	for (File file : sources) {
	try {
		os.putArchiveEntry(new TarArchiveEntry(file));
		IOUtils.copy(new FileInputStream(file), os);
		os.closeArchiveEntry();
		
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} 
	}
	if(os != null) {
	try {
		os.flush();
		os.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
	}
	
	return target;
 }

 /**
  * @throws IOException 
  * @Title: compress
  * @Description: 将文件用gzip压缩
  * @param  source 需要压缩的文件
  * @return File    返回压缩后的文件
  * @throws
  */
	public static File compress(File source) throws IOException {
//		String name = source.getCanonicalPath() ;
		//創建目標路徑下的文件
		File target = new File(source.getCanonicalPath() + ".gz");
		FileInputStream in = null;
		GZIPOutputStream out = null;
		try {
			in = new FileInputStream(source);
			out = new GZIPOutputStream(new FileOutputStream(target));
			byte[] array = new byte[1024];
			int number = -1;
			while((number = in.read(array, 0, array.length)) != -1) {
			out.write(array, 0, number);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if(in != null) {
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		}
		
		if(out != null) {
		try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		}
		}
		return target;
	}
	/**
	 * 壓縮具單個體文件（入口函數）Linux下使用
	 * @param filePath 單個文件的路徑 {E:\\宋燕\\IMG_1066.JPG,E:\\宋燕\\IMG_1066.JPG}
	 * @param gZipPath 壓縮之後的文件路徑and文件名：E:\\宋燕\\release_package.tar
	 * @return 壓縮是否成功
	 * @throws IOException 
	 */
	public static boolean gZipOpen(String[] filePath ,String gZipPath) throws IOException{
		boolean jud = false;
		//創建File文件數組
		File[] sources = new File[filePath.length] ;
		for (int i = 0;i<filePath.length;i++) {
			
			sources[i] = new File(filePath[i]);
			
		}
		File target = new File(gZipPath);
		if(compress(pack(sources, target)).exists())
			jud = true;
		return jud;
	}
	
	public static void main(String[] args) throws IOException {
		String[] name = {"E:\\宋燕\\IMG_1066.JPG","E:\\宋燕\\IMG_1065.JPG","E:\\宋燕\\IMG_1067.JPG"};
		if(gZipOpen(name, "E:\\宋燕\\壓縮.tar"))
			System.out.println("压缩成功");
		else 
			System.out.println("壓縮失敗");
	}
}
