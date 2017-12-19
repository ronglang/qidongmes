 package com.css.common.util;
 
 import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
 
 public class SimpleHttpClient
 {
   public static String get(String url)
     throws IOException
   {
     String getURL = "";
     URL getUrl = new URL(getURL);
     
 
     HttpURLConnection connection = (HttpURLConnection)getUrl.openConnection();
     
 
     connection.connect();
     
     BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
     String line = "";
     StringBuffer resContent = new StringBuffer();
     while ((line = reader.readLine()) != null) {
       resContent.append(line);
     }
     reader.close();
     connection.disconnect();
     return resContent.toString();
   }
   
   public static String post(String url, String content, String conentType)
     throws IOException
   {
     URL postUrl = new URL(url);
     
     HttpURLConnection connection = (HttpURLConnection)postUrl.openConnection();
     
 
     connection.setDoOutput(true);
     connection.setDoInput(true);
     connection.setRequestMethod("POST");
     
     connection.setUseCaches(false);
     
     connection.setInstanceFollowRedirects(true);
     
 
 
     connection.setRequestProperty("Content-Type", conentType);
     
 
     connection.connect();
     OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "utf-8");
     
 
 
     out.write(content);
     out.flush();
     out.close();
     BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
     String line = "";
     StringBuffer resContent = new StringBuffer();
     while ((line = reader.readLine()) != null) {
       resContent.append(line);
     }
     reader.close();
     connection.disconnect();
     return resContent.toString();
   }
 }






