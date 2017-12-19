package com.css.common.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * 
 * @author york 编码集转换工具
 * 
 */
public class CodedFormatParseUtil {

	public static final String UTF_8 = "UTF-8";
	
	public static final String GBK = "GBK";
	
	public static final String ISO_8859_1 = "ISO-8859-1";

	public static void main(String[] args) throws Exception {
//		// System.out.println(toUtf_8("日尼玛", UTF_8));
//		try {
//			System.out.println(new String(getUTF8ByteFromGBK("日尼玛hello world"),"UTF-8"));
//			 System.out.println("当前JVM的默认字符集：" + Charset.defaultCharset()); 
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		String str = "默认中文";
		System.out.println("默认编码"+Charset.defaultCharset());
		System.out.println(new String(str.getBytes(),"UTF-8"));
		System.out.println(getCharset("a;sdjaslfj"));
	}
	/**
	 * 仅仅适用于GBK转UTF-8
	 * @param gbkStr
	 * @return
	 */
	public static byte[] getUTF8ByteFromGBK(String gbkStr) {
		int n = gbkStr.length();
		byte[] utfBytes = new byte[3 * n];
		int k = 0;
		for (int i = 0; i < n; i++) {
			int m = gbkStr.charAt(i);
			if (m < 128 && m >= 0) {
				utfBytes[k++] = (byte) m;
				continue;
			}
			utfBytes[k++] = (byte) (0xe0 | (m >> 12));
			utfBytes[k++] = (byte) (0x80 | ((m >> 6) & 0x3f));
			utfBytes[k++] = (byte) (0x80 | (m & 0x3f));
		}
		if (k < utfBytes.length) {
			byte[] tmp = new byte[k];
			System.arraycopy(utfBytes, 0, tmp, 0, k);
			return tmp;
		}
		return utfBytes;
	}
	
	public static String getCharset(String str) throws UnsupportedEncodingException{
		String tempStr = new String(str.getBytes(UTF_8),UTF_8);
		if(tempStr.equals(str)){
			return UTF_8;
		}
		tempStr = new String(str.getBytes(GBK),GBK);
		if(tempStr.equals(str)){
			return GBK;
		}
		tempStr = new String(str.getBytes(ISO_8859_1),ISO_8859_1);
		if(tempStr.equals(str)){
			return ISO_8859_1;
		}
		return null;
	}
}
