package com.file.option.netConnection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TcpProduct {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public Map<String, Object> sendMsg(HashMap msg, String serverIp, int serverPort, int conTimeOut, int readTimeOut){
		
		HashMap rspMap = new HashMap();
		
		try {
			Socket socket = new Socket();
			
			DataOutputStream oos = null;
			
			DataInputStream ois = null;
			
			//��������Թ���һЩ��ֵ��Ȼ�󽫹���֮����������ӵ�msg��
			
			socket.connect(new InetSocketAddress(serverIp, serverPort), conTimeOut);
					
			socket.setSoTimeout(readTimeOut);
			
			oos = new DataOutputStream(socket.getOutputStream());
			
			//ʹ��jackson��msgת����String
			String str = msg.toString();
			
			//��ȡbety����
			byte[] bytes = str.getBytes("UTF-8");
					
			oos.write(bytes);
					
			oos.flush();
					
			ois = new DataInputStream(socket.getInputStream());
					
			byte[] lengthBytes = new byte[8];		
			
			ois.readFully(lengthBytes);
			
			ObjectMapper jsonMap = new ObjectMapper();
			
			rspMap = jsonMap.readValue(lengthBytes, HashMap.class);
		} catch (JsonParseException e) {
			
			logger.info("jsonת���쳣" + e);
			
			//e.printStackTrace();
			
		} catch (JsonMappingException e) {

			logger.info("json to map error" + e);
			//e.printStackTrace();
			
		} catch (SocketException e) {

			logger.error("socket error" + e);
			//e.printStackTrace();
		} catch (Exception e) {
			
			logger.error("tcp send error" + e);
			
		}
		
		return rspMap;
		
	}
	

}