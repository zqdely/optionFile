package com.file.tool.getNetHtml;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetPagUrl {
	/**
	 * Ҫ��������ҳ
	 */
	String htmlUrl;
	/**
	 * ���з������
	 */
	ArrayList<String> hrefList = new ArrayList<String>();
	/**
	 * �@ȡ�W�����key����url��ַ��value��
	 */
	HashMap<String, Object> urlDate = new HashMap<String, Object>();
	/**
	 * ��ҳ���뷽ʽ
	 */
	String charSet;

	public GetPagUrl(String htmlUrl) {
		// TODO �Զ����ɵĹ��캯�����
		this.htmlUrl = htmlUrl;
	}

	/**
	 * ��ȡ�������
	 * 
	 * @throws IOException
	 */
	public ArrayList<String> getHrefList() throws IOException {
		parser();
		return hrefList;
	}
	
	/**
	 * ��ȡmap����
	 */
	public HashMap<String, Object> getUrlMap(){
		return urlDate;
	}

	/**
	 * ������ҳ����
	 * 
	 * @return
	 * @throws IOException
	 */
	private void parser() throws IOException {
		URL url = new URL(htmlUrl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		String contenttype = connection.getContentType();
		charSet = getCharset(contenttype);
		if (charSet == null || charSet.equals(""))
			charSet = "UTF-8";
		InputStreamReader isr = new InputStreamReader(connection.getInputStream(), charSet);
		BufferedReader br = new BufferedReader(isr);
		String str = null, rs = null;
		while ((str = br.readLine()) != null) {
			rs = getHref(str);
			if (rs != null)
				hrefList.add(rs);
			if (rs != null)
				getUrlInfo(rs);
		}
	}
	
	/**
	 * ��ȡ<a>��ǩ
	 */
	public void getUrlInfo(String info){
		
		while(info != null){
			String index = info.substring(info.indexOf("<a"), info.indexOf("</a>")+4);
			
			urlDate.put(pagName(index, "name"), pagName(index, "url"));
			
			info = info.substring(index.length()+1);
				
			if(info.indexOf("<a")>1){
				
				info = (String) info.subSequence(info.indexOf("<a"), info.length());
				
			}
		}
		
	}

	/**
	 * ����url������
	 * 
	 * @param str
	 * @param info
	 *            name:����url��Ӧ���� url������url��ַ
	 * @return
	 */
	public String pagName(String str, String info) {

		if (info.equals("name")) {

			str = (String) str.subSequence(str.lastIndexOf("</a>") - 2,str.lastIndexOf("</a>"));

		} else {

			str = str.substring(str.indexOf("href") + 5,str.substring((str.indexOf("href"))).indexOf(" "));

		}

		return str;

	}

	/**
	 * ��ȡ��ҳ���뷽ʽ
	 * 
	 * @param str
	 */
	private String getCharset(String str) {
		Pattern pattern = Pattern.compile("charset=.*");
		Matcher matcher = pattern.matcher(str);
		if (matcher.find())
			return matcher.group(0).split("charset=")[1];
		return null;
	}

	/**
	 * ��һ���ַ����ж�ȡ����
	 * 
	 * @return
	 */
	private String getHref(String str) {
		Pattern pattern = Pattern.compile("<a href=.*</a>");
		Matcher matcher = pattern.matcher(str);
		if (matcher.find())
			return matcher.group(0);
		return null;
	}

	public static void main(String[] arg) throws IOException {
		GetPagUrl a = new GetPagUrl("http://www.baidu.com");
		ArrayList<String> hrefList = a.getHrefList();
		for (int i = 0; i < hrefList.size(); i++)
			System.out.println(hrefList.get(i));
		Map<String, Object> result = a.getUrlMap();
		for (String key : result.keySet()) {
			System.out.println(key + "\t" + result.get(key) +"\n");
		}
	}
}