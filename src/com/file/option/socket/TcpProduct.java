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
		
		//��������Թ���һЩ��ֵ��Ȼ�󽫹���֮����������ӵ�msg��
		
		socket.connect(new InetSocketAddress(serverIp, serverPort), conTimeOut);
		
		socket.setSoTimeout(readTimeOut);
		
		//ʹ��jackson��msgת����String
		String str = msg.toString();
		
		//��ȡbety����
		byte[] bytes = str.getBytes("UTF-8");
		
		oos.write(bytes);
		
		oos.flush();
		
		ois = new DataInputStream(socket.getInputStream());
		
		byte[] lengthBytes = new byte[8];
		
		ois.readFully(lengthBytes);
		
		int lenb = Integer.parseInt(new String(lengthBytes,"utf-8"));
		
		byte[] leftBytes = new byte[lenb];
		
		ois.readFully(leftBytes);
		
		//ʹ��jackson��Stringת����HashMap
		//rspMap = (HashMap)(new String(leftBytes,"utf-8")); 
		
	}

}