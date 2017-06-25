package com.file.option.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TcpProduct {
	
	private static Logger logger = LoggerFactory.getLogger(TcpProduct.class);
	
	public HashMap sendMsg(HashMap msg, String serverIp, int serverPort, int conTimeOut, int readTimeOut){
		
		HashMap rspMap = new HashMap();
		
		Socket socket = new Socket();
		
		DataOutputStream oos = null;
		
		DataInputStream ois = null;
		
		//在这里可以过滤一些空值，然后将过滤之后的数据添加到msg中
		
		socket.connect(new InetSocketAddress(serverIp, serverPort), conTimeOut);
		
		socket.setSoTimeout(readTimeOut);
		
		//使用jackson将msg转换成String
		String str = msg.toString();
		
		//获取bety数组
		byte[] bytes = str.getBytes("UTF-8");
		
		oos.write(bytes);
		
		oos.flush();
		
		ois = new DataInputStream(socket.getInputStream());
		
		byte[] lengthBytes = new byte[8];
		
		ois.readFully(lengthBytes);
		
		int lenb = Integer.parseInt(new String(lengthBytes,"utf-8"));
		
		byte[] leftBytes = new byte[lenb];
		
		ois.readFully(leftBytes);
		
		//使用jackson将String转换成HashMap
		//rspMap = (HashMap)(new String(leftBytes,"utf-8")); 
		
	}

}
