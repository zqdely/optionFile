package com.file.tool.getNetHtml;


import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class HtmlClient {

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        String url_str = "http://www.hao123.com";
        String charset = "utf-8";
        String filepath = "d:/125.html";
        
        HttpClient hc = new DefaultHttpClient();
        HttpGet hg = new HttpGet(url_str);
        HttpResponse response = hc.execute(hg);
        HttpEntity entity = response.getEntity();
        
        
        InputStream htm_in = null;
        
        if(entity != null){
            System.out.println(entity.getContentLength());
            htm_in = entity.getContent();
            String htm_str = InputStream2String(htm_in,charset);
            saveHtml(filepath,htm_str);
        }
    }
    /**
     * Method: saveHtml 
     * Description: save String to file
     * @param filepath
     * file path which need to be saved
     * @param str
     * string saved
     */
    public static void saveHtml(String filepath, String str){
        
        try {
            /*@SuppressWarnings("resource")
            FileWriter fw = new FileWriter(filepath);
            fw.write(str);
            fw.flush();*/
            OutputStreamWriter outs = new OutputStreamWriter(new FileOutputStream(filepath, true), "utf-8");
            outs.write(str);
            outs.close();
        } catch (IOException e) {
            System.out.println("Error at save html...");
            e.printStackTrace();
        }
    }
    /**
     * Method: InputStream2String 
     * Description: make InputStream to String
     * @param in_st
     * inputstream which need to be converted
     * @param charset
     * encoder of value
     * @throws IOException
     * if an error occurred 
     */
    public static String InputStream2String(InputStream in_st,String charset) throws IOException{
        BufferedReader buff = new BufferedReader(new InputStreamReader(in_st, charset));
        StringBuffer res = new StringBuffer();
        String line = "";
        while((line = buff.readLine()) != null){
            res.append(line);
        }
        return res.toString();
    }
}
