package com.css.common.web.syscommon.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.common.util.PropertyFileUtil;
import com.css.common.util.StringUtil;

@Controller
@RequestMapping("/appDownloadAction")
public class AppDownloadAction {

	@RequestMapping({ "appDownload" })
	@ResponseBody
	public HashMap<String, Object> appDownload(String versionNumber,String type) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("success", false);
		String appDownloadUrl = checkVersionNumberMsg(versionNumber,type);
		if (!StringUtil.isEmpty(appDownloadUrl)) {
			map.put("success", true);
			map.put("appDownloadUrl", appDownloadUrl);
		}else{
			map.put("msg", "已为最新版本！");
		}
		return map;
	}

	public String checkVersionNumberMsg(String versionNumber,String type) {//
		String encoding = "GBK";
		String appDownloadUrl = "";
		try {
			PropertyFileUtil property = new PropertyFileUtil();
			String uploadDir = property.getProp("image_path") + "uploadfiles"
					+ File.separatorChar + "appResource" + File.separatorChar
					+ "versionNumberMsg.txt";// 获取系统存放版本号信息文件路径
			File file = new File(uploadDir);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String versionNumberMsg = readLineBy(1, bufferedReader)
						.replace(" ", "");
				System.out.println("app最新版本号：" + versionNumberMsg);
				Integer vn=Integer.parseInt(versionNumber);
				Integer vnm=Integer.parseInt(versionNumberMsg);
//				if (!versionNumber.equals(versionNumberMsg))
				if (vn<vnm)
					appDownloadUrl = "uploadfiles" + File.separatorChar
					+ "appResource" + File.separatorChar;
				else{
					return "";
				}
					if("android".equals(type)){
						appDownloadUrl+="ZHBZ_fuping.apk";
					}else{
						appDownloadUrl+="ZHBZ_fuping.ipa";
					}
					
							
				read.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("文件读取失败！");
		}
		return appDownloadUrl;
	}

	/**
	 * 读取指定行的数据
	 * 
	 * @param lineNumber
	 * @param reader
	 * @return
	 * @throws Exception
	 */
	private static String readLineBy(int lineNumber, BufferedReader reader)
			throws Exception {
		String line = "";
		int i = 0;
		while (i < lineNumber) {
			line = reader.readLine();
			i++;
		}
		return line;
	}
}
