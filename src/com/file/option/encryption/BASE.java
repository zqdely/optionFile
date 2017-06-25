package com.file.option.encryption;


/*
BASE�ļ��ܽ�����˫��ģ������󷴽�.
BASEEncoder��BASEDecoder�Ƿǹٷ�JDKʵ���ࡣ��Ȼ������JDK�����ҵ���ʹ�ã�������API��鲻����
JRE �� sun �� com.sun ��ͷ�����඼��δ���ĵ����ģ��������� java, javax ���Ļ��������е�ʵ�ִ������ײ�ƽ̨�йأ�
һ����˵�ǲ��Ƽ�ʹ�õġ�
BASE �ϸ��˵�����ڱ����ʽ�����Ǽ����㷨
��Ҫ����BASEEncoder��BASEDecoder�����࣬����ֻ��Ҫ֪��ʹ�ö�Ӧ�ķ������ɡ�
����BASE���ܺ�������ֽ�λ���ǵı������������λ����=������䡣
BASE
����RFC�Ķ��壬Base������Ϊ��Base���ݴ��ͱ��뱻����������������е�λ�ֽ�����Ϊһ�ֲ��ױ���ֱ��ʶ�����ʽ��
��The Base Content-Transfer-Encoding is designed to represent arbitrary sequences of octets in a form that need not be humanly readable.��
�������ʼ���http���ܣ���ȡhttp��Ϣ����ͻᷢ�ֵ�¼�������û����������ֶ�ͨ��BASE���ܵġ�
*/
public class BASE {

	/** 
	   * BASE���� 
	   * 
	   * @param key 
	   * @return 
	   * @throws Exception 
	   */ 
	  public static byte[] decryptBASE(String key) throws Exception { 
	    return (new BASEDecoder()).decodeBuffer(key); 
	  } 
	  /** 
	   * BASE���� 
	   * 
	   * @param key 
	   * @return 
	   * @throws Exception 
	   */ 
	  public static String encryptBASE(byte[] key) throws Exception { 
	    return (new BASEEncoder()).encodeBuffer(key); 
	  } 
	  public static void main(String[] args) {
	   String str="";
	    try {
	    String result= BASE.encryptBASE(str.getBytes());
	     System.out.println("result=====��������=========="+result);
	     byte result[]= BASE.decryptBASE(result);
	     String str=new String(result);
	     System.out.println("str========��������========"+str);
	  } catch (Exception e) {
	    e.printStackTrace();
	  }
	  }
	
}