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
	 * 要分析的网页
	 */
	String htmlUrl;
	/**
	 * 所有分析结果
	 */
	ArrayList<String> hrefList = new ArrayList<String>();
	/**
	 * 獲取網頁名（key）和url地址（value）
	 */
	HashMap<String, Object> urlDate = new HashMap<String, Object>();
	/**
	 * 网页编码方式
	 */
	String charSet;

	public GetPagUrl(String htmlUrl) {
		// TODO 自动生成的构造函数存根
		this.htmlUrl = htmlUrl;
	}

	/**
	 * 获取分析结果
	 * 
	 * @throws IOException
	 */
	public ArrayList<String> getHrefList() throws IOException {
		parser();
		return hrefList;
	}
	
	/**
	 * 获取map集合
	 */
	public HashMap<String, Object> getUrlMap(){
		return urlDate;
	}

	/**
	 * 解析网页链接
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
	 * 截取<a>标签
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
	 * 解析url和名字
	 * 
	 * @param str
	 * @param info
	 *            name:解析url对应的名 url：解析url地址
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
	 * 获取网页编码方式
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
	 * 从一行字符串中读取链接
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