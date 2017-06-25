package com.file.tool.getNetHtml;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class UrlConnectionHtml {

	public static void main(String[] args) {

		String filepath = "d:/124.html";

		String url_str = "http://www.hao123.com/";
		URL url = null;
		try {
			url = new URL(url_str);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		String charset = "utf-8";
		int sec_cont = 1000;
		try {
			URLConnection url_con = url.openConnection();
			url_con.setDoOutput(true);
			url_con.setReadTimeout(10 * sec_cont);
			url_con.setRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");
			InputStream htm_in = url_con.getInputStream();

			String htm_str = InputStream2String(htm_in, charset);
			saveHtml(filepath, htm_str);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method: saveHtml Description: save String to file
	 * 
	 * @param filepath
	 *            file path which need to be saved
	 * @param str
	 *            string saved
	 */
	public static void saveHtml(String filepath, String str) {

		try {
			/*
			 * @SuppressWarnings("resource") FileWriter fw = new
			 * FileWriter(filepath); fw.write(str); fw.flush();
			 */
			OutputStreamWriter outs = new OutputStreamWriter(
					new FileOutputStream(filepath, true), "utf-8");
			outs.write(str);
			System.out.print(str);
			outs.close();
		} catch (IOException e) {
			System.out.println("Error at save html...");
			e.printStackTrace();
		}
	}
	
	/**
	 * 格式化xml字符串
	 * @param inputXML
	 * @return
	 * @throws Exception
	 */
	/*public String formatXML(String inputXML) throws Exception {  
	    SAXReader reader = new SAXReader();  
	    Document document = reader.read(new StringReader(inputXML));  
	    String requestXML = null;  
	    XMLWriter writer = null;  
	    if (document != null) {  
	      try {  
	        StringWriter stringWriter = new StringWriter();  
	        OutputFormat format = new OutputFormat(" ", true);  
	        writer = new XMLWriter(stringWriter, format);  
	        writer.write(document);  
	        writer.flush();  
	        requestXML = stringWriter.getBuffer().toString();  
	      } finally {  
	        if (writer != null) {  
	          try {  
	            writer.close();  
	          } catch (IOException e) {  
	          }  
	        }  
	      }  
	    }  
	    return requestXML;  
	  }  */
	
	/*//格式化XML字符串
    public static String formatXml(String str) throws Exception {
        Document document = null;
        document = DocumentHelper.parseText(str);
        // 格式化输出格式
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("gb2312");
        StringWriter writer = new StringWriter();
        // 格式化输出流
        XMLWriter xmlWriter = new XMLWriter(writer, format);
        // 将document写入到输出流
        xmlWriter.write(document);
        xmlWriter.close();
        return writer.toString();
    }*/
	
	/**
	 * Method: InputStream2String Description: make InputStream to String
	 * 
	 * @param in_st
	 *            inputstream which need to be converted
	 * @param charset
	 *            encoder of value
	 * @throws IOException
	 *             if an error occurred
	 */
	public static String InputStream2String(InputStream in_st, String charset)
			throws IOException {
		BufferedReader buff = new BufferedReader(new InputStreamReader(in_st,
				charset));
		StringBuffer res = new StringBuffer();
		String line = "";
		while ((line = buff.readLine()) != null) {
			res.append(line);
		}
		return res.toString();
	}

}

