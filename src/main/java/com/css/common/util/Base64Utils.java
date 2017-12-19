 package com.css.common.util;
 
 import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.security.crypto.codec.Base64;
 
 public class Base64Utils
 {
   //private static final int CACHE_SIZE = 1024;
   
   public static byte[] decode(String base64)
     throws Exception
   {
     return Base64.decode(base64.getBytes());
   }
   
   public static String encode(byte[] bytes)
     throws Exception
   {
     return new String(Base64.encode(bytes));
   }
   
   public static String encodeFile(String filePath)
     throws Exception
   {
     byte[] bytes = fileToByte(filePath);
     return encode(bytes);
   }
   
   public static void decodeToFile(String filePath, String base64)
     throws Exception
   {
     byte[] bytes = decode(base64);
     byteArrayToFile(bytes, filePath);
   }
   
   public static byte[] fileToByte(String filePath)
     throws Exception
   {
     byte[] data = new byte[0];
     File file = new File(filePath);
     if (file.exists())
     {
       FileInputStream in = new FileInputStream(file);
       ByteArrayOutputStream out = new ByteArrayOutputStream(2048);
       byte[] cache = new byte[1024];
       int nRead = 0;
       while ((nRead = in.read(cache)) != -1)
       {
         out.write(cache, 0, nRead);
         out.flush();
       }
       out.close();
       in.close();
       data = out.toByteArray();
     }
     return data;
   }
   
   public static void byteArrayToFile(byte[] bytes, String filePath)
     throws Exception
   {
     InputStream in = new ByteArrayInputStream(bytes);
     File destFile = new File(filePath);
     if (!destFile.getParentFile().exists()) {
       destFile.getParentFile().mkdirs();
     }
     destFile.createNewFile();
     OutputStream out = new FileOutputStream(destFile);
     byte[] cache = new byte[1024];
     int nRead = 0;
     while ((nRead = in.read(cache)) != -1)
     {
       out.write(cache, 0, nRead);
       out.flush();
     }
     out.close();
     in.close();
   }
   
   /**   
	 * 字符串转换成十六进制字符串  
	 * @param String str 待转换的ASCII字符串  
	 * @return String 每个Byte之间无空格分隔，如: [616C6B]  
	 */  
	public String str2HexStr(String str) {

		char[] chars = "0123456789ABCDEF".toCharArray();
		StringBuilder sb = new StringBuilder("");
		byte[] bs = str.getBytes();
		int bit;

		for (int i = 0; i < bs.length; i++) {
			bit = (bs[i] & 0x0f0) >> 4;
			sb.append(chars[bit]);
			bit = bs[i] & 0x0f;
			sb.append(chars[bit]);
			// sb.append(' ');
		}
		return sb.toString().trim();
	}

	/**
	 * 十六进制转换字符串
	 * 
	 * @param String
	 *            str Byte字符串(Byte之间无分隔符 如:[616C6B])
	 * @return String 对应的字符串
	 */
	public static String hexStr2Str(String hexStr) {
		String str = "0123456789ABCDEF";
		char[] hexs = hexStr.toCharArray();
		byte[] bytes = new byte[hexStr.length() / 2];
		int n;

		for (int i = 0; i < bytes.length; i++) {
			n = str.indexOf(hexs[2 * i]) * 16;
			n += str.indexOf(hexs[2 * i + 1]);
			bytes[i] = (byte) (n & 0xff);
		}
		return new String(bytes);
	}
 }






