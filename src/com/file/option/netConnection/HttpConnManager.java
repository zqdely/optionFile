package com.file.option.netConnection;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class HttpConnManager {
	
	private String httpUrl = "";
	
	//���ӳ�ʱʱ��
	private int connTimeOut = 30000;
	
	//��ȡ��ʱʱ��
	private int readTimeOut = 30000;

	public HttpConnManager(String httpUrl) {
		super();
		this.httpUrl = httpUrl;
	}
	
	public URLConnection getConnection() throws IOException{
		
		URL url = new URL(httpUrl);
		
		URLConnection conn = url.openConnection();
		
		conn.setConnectTimeout(connTimeOut);
		
		conn.setReadTimeout(readTimeOut);
		
		conn.setDoInput(true);
		
		conn.setUseCaches(true);
		
		conn.setRequestProperty("Keep-Alive", "");
		
		return conn;
		
	}
	
	public String getHttpUrl(){
		
		return httpUrl;
		
	}

}