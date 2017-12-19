package com.css.commcon.util;

import java.io.ByteArrayOutputStream;

/**
 * 
 * @author DELL
 * 
 */
public class TransformStrToSixteen {
	/**
	 * 将字符串转换成16进制
	 * 
	 * @data:2017年7月12日
	 * @param s
	 * @return
	 * @autor:wl
	 */
	public static String toHexString(String s) {
		String str = "";
		for (int i = 0; i < s.length(); i++) {
			int ch = (int) s.charAt(i);
			String s4 = Integer.toHexString(ch);
			str = str + s4;
		}
		return str;
	}
/**
 * 将16进制编码转换成字符串
 *@data:2017年7月12日
@param s
@return
@autor:wl
 */
	public static String toStringHex(String s) {
		byte[] baKeyword = new byte[s.length() / 2];
		for (int i = 0; i < baKeyword.length; i++) {
			try {
				baKeyword[i] = (byte) (0xff & Integer.parseInt(
						s.substring(i * 2, i * 2 + 2), 16));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			s = new String(baKeyword, "utf-8");// UTF-16le:Not
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return s;
	}
	
	/*
	* 将16进制数字解码成字符串,适用于所有字符（包括中文）
	*/
	public static String decode(String bytes,String hexString)
	{
	ByteArrayOutputStream baos=new ByteArrayOutputStream(bytes.length()/2);
	//将每2位16进制整数组装成一个字节
	for(int i=0;i<bytes.length();i+=2)
	baos.write((hexString.indexOf(bytes.charAt(i))<<4 |hexString.indexOf(bytes.charAt(i+1))));
	return new String(baos.toByteArray());
	} 
}
