package com.css.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {

	/**
	 * 复制文件
	 * @param src 源路径
	 * @param tag 目的路径
	 * @return
	 */
	public static boolean copyFile(String src, String tag) {
		try {
			FileInputStream input = new FileInputStream(src);
			BufferedInputStream in = new BufferedInputStream(input);

			FileOutputStream fos = new FileOutputStream(tag);
			BufferedOutputStream bos = new BufferedOutputStream(fos);

			byte[] size = new byte[5 * 1024];
			int len;
			while ((len = in.read(size)) != -1) {
				bos.write(size, 0, len);
			}
			bos.flush();
			bos.close();
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/**
	 * 该方法功能是删除文件
	 * @param path		文件路径(绝对路径)
	 * @return			删除成功返回true,失败返回false
	 */
	public static boolean deleteFile(String path)  {
		File file = new File(path);
		if (file.exists()) {
			if (file.isFile()) {
				file.delete();
			} else if (file.isDirectory()) {
				File files[] = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					files[i].delete();
				}
			}
			file.delete();
		} 
		return file.delete();
	}

	
}
