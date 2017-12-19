package com.css.commcon.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @TODO : 常量类
 * @author: 翟春磊
 * @DATE : 2017年7月17日
 */
public class YorkUtil {
	// web端的ID 用于WEB端监听订阅持久化的订阅者ID--与手持机的通讯。
	public static String QUEUE_TOPIC_WEB_SYSTEM_ID = "QUEUE_TOPIC_WEB_SYSTEM_ID_web_self";

	/**
	 * 电子看板echache缓存的key编码。
	 */
	public static final String Memcache_看板_工程部 = "Memcache_看板_工程部";
	// 仓库
	public static final String Memcache_看板_缺料 = "Memcache_看板_缺料";
	public static final String Memcache_看板_报废 = "Memcache_看板_报废";
	public static final String Memcache_看板_仓库_列表 = "Memcache_看板_仓库_列表";
	public static final String Memcache_看板_仓库_库存 = "Memcache_看板_仓库_库存";
	// 车间
	public static String Memcache_看板_车间 = "Memcache_看板_车间";
	public static final String Memcache_看板_车间_异常 = "Memcache_看板_车间_异常";
	public static final String Memcache_看板_车间_机台 = "Memcache_看板_车间_机台";
	public static final String Memcache_看板_车间_呼叫 = "Memcache_看板_车间_呼叫";
	public static final String Memcache_看板_车间_进度 = "Memcache_看板_车间_进度";
	// 生产部
	public static final String Memcache_看板_生产_柱 = "Memcache_看板_生产_柱";
	public static final String Memcache_看板_生产_饼 = "Memcache_看板_生产_饼";

	// 各工序
	public static final String Memcache_看板_绞线_任务 = "Memcache_看板_绞线_任务";
	public static String Memcache_看板_绞线_实时 = "Memcache_看板_绞线_实时";

	public static String Memcache_看板_拉丝_实时 = "Memcache_看板_拉丝_实时";
	public static final String Memcache_看板_拉丝_任务 = "Memcache_看板_拉丝_任务";

	public static final String Memcache_看板_挤护套_实时 = "Memcache_看板_挤护套_实时";
	public static final String Memcache_看板_挤护套_任务 = "Memcache_看板_挤护套_任务";

	
	public static String Memcache_看板_铠装_实时 = "Memcache_看板_铠装_实时";
	public static final String Memcache_看板_铠装_任务 = "Memcache_看板_铠装_任务";

	public static final String Memcache_看板_分盘_任务 = "Memcache_看板_分盘_任务";
	public static final String Memcache_看板_分盘_实时 = "Memcache_看板_分盘_实时";
	
	public static String Memcache_看板_成缆_实时 = "Memcache_看板_成缆_实时";
	public static String Memcache_看板_成缆_任务 = "Memcache_看板_成缆_任务";
	
	public static String Memcache_看板_绝缘_实时 = "Memcache_看板_绝缘_实时";
	public static String Memcache_看板_绝缘_任务 = "Memcache_看板_绝缘_任务";
	
	public static String Memcache_看板_押出_实时 = "Memcache_看板_押出_实时";
	public static String Memcache_看板_押出_任务 = "Memcache_看板_押出_任务";
	
	public static String Memcache_看板_集绞_实时 = "Memcache_看板_集绞_实时";
	public static String Memcache_看板_集绞_任务 = "Memcache_看板_集绞_任务";
	
	public static String Memcache_看板_复绕_实时 = "Memcache_看板_复绕_实时";
	public static String Memcache_看板_复绕_任务 = "Memcache_看板_复绕_任务";
	
	public static String Memcache_看板_包带_实时 = "Memcache_看板_包带_实时";
	public static String Memcache_看板_包带_任务 = "Memcache_看板_包带_任务";
	

	//异常
	public static final String Memcache_看板_车间_异常_拉丝 = "Memcache_看板_车间_异常_拉丝";
	public static final String Memcache_看板_车间_异常_绞线 = "Memcache_看板_车间_异常_绞线";
	public static final String Memcache_看板_车间_异常_铠装 = "Memcache_看板_车间_异常_铠装";
	public static final String Memcache_看板_车间_异常_挤护套 = "Memcache_看板_车间_异常_挤护套";
	public static final String Memcache_看板_车间_异常_成缆 = "Memcache_看板_车间_异常_成缆";
	public static final String Memcache_看板_车间_异常_分盘 = "Memcache_看板_车间_异常_分盘";
	public static final String Memcache_看板_车间_异常_绝缘 = "Memcache_看板_车间_异常_绝缘";

	public static final String Memcache_看板_生产_通知单 = "Memcache_看板_生产_通知单";

	public static final String Memcache_看板_仓库_统计 = "Memcache_看板_仓库_统计";

	public static final String Memcache_看板_仓库_出库 = "Memcache_看板_仓库_出库";

	public static final String Memcache_看板_仓库_入库 = "Memcache_看板_仓库_入库";

	

	/*
	 * public static String Memcache_看板_护套_实时 = "Memcache_看板_护套_实时"; public
	 * static String Memcache_看板_护套_任务 = "Memcache_看板_护套_任务";
	 */
	
	
	public static String Memcache_看板_质检 = "Memcache_看板_质检";

	/*public static String Memcache_看板_挤护套 = "Memcache_看板_挤护套";
	public static String Memcache_看板_分盘 = "Memcache_看板_分盘";

	public static String Memcache_看板_拉丝 = "Memcache_看板_拉丝";

	public static String Memcache_看板_绞线 = "Memcache_看板_绞线";

	public static String Memcache_看板_绝缘 = "Memcache_看板_绝缘";
	public static String Memcache_看板_护套 = "Memcache_看板_护套";
	public static String Memcache_看板_成缆 = "Memcache_看板_成缆";

	

	public static String Memcache_看板_车间_实时 = "Memcache_看板_车间_实时";
	public static String Memcache_看板_生产 = "Memcache_看板_生产";
	public static String Memcache_看板_生产_实时 = "Memcache_看板_生产_实时";

	public static String Memcache_看板_仓库 = "Memcache_看板_仓库";
	public static String Memcache_看板_仓库_实时 = "Memcache_看板_仓库_实时";
	public static String Memcache_看板_铠装 = "Memcache_看板_铠装";
	public static String Memcache_看板_质检_实时 = "Memcache_看板_质检_实时";
	public static String Memcache_看板_生产部 = "Memcache_看板_生产部";
	public static String Memcache_看板_生产部_实时 = "Memcache_看板_生产部_实时";*/
	
	public static String Memcache_车间致辞 = "欢迎致辞";
	public static String Memcache_车间动态 = "Memcache_车间动态";
	
	public static String Memcache_质检呼叫 = "Memcache_质检呼叫";
	public static final String Memcache_质检呼叫_grid = "Memcache_质检呼叫_grid";

	public static final String Memcache_机台_实时_速度 = "Memcache_机台_实时_速度";

	public static final String Memcache_机台_实时_参数 = "Memcache_机台_实时_参数";

	public static final String Memcache_看板_生产_异常 = "Memcache_看板_生产_异常";

	public static final String Memcache_ALL_车间参数 = "Memcache_ALL_车间参数";

	public static final String Memcache_机台_实时_Echart = "Memcache_机台_实时_Echart";

	public static final String Memcache_机台code = "Memcache_机台code";

	public static final String Memcache_当日上传机台 = "Memcache_当日上传机台";
	
	
	public static String Memcache_看板_叉车呼叫 = "Memcache_看板_叉车呼叫";

	public static String 来料质检RFID = "来料质检RFID";

	public static String 线盘RFID = "线盘RFID";
	
	public static void main(String[] args) {
		// List<Object> list = new ArrayList<Object>();
		// for (int i = 0; i < 100; i++) {
		// Object obj = new String[] { i + "", "name" + i, i + "" };
		// list.add(obj);
		// }
		// String sql = " select id as id ,name as name ,count as count from ";
		// System.out.println(parse(list, Vo.class, sql));
	}

	public static <T> List<T> parse(List<?> list, Class<T> cla, String sql) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		List<T> resultList = new ArrayList<T>();
		String value = sql.split(" from ")[0];
		String value1 = sql.split(" FROM ")[0];
		String arr[] = null;
		if (value.length() < value1.length()) {
			arr = value.split(",");
		} else {
			arr = value1.split(",");
		}
		String key = null;
		for (int i = 0; i < arr.length; i++) {
			if (i == 7) {
				System.out.println(arr[i]);
			}
			try {

				if (arr[i].split(" as ").length < 2) {
					key = arr[i].split(" AS ")[1].trim();
				} else {
					key = arr[i].split(" as ")[1].trim();
				}
				map.put(key, i);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			parse(list, map, resultList, cla);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}

	public static <T> void parse(List<?> list, Map<String, Integer> map,
			List<T> result, Class<T> cla) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(" yyyy-MM-dd HH:mm:ss ");
		for (Object obj : list) {
			Object[] objArr = (Object[]) obj;
			T t = cla.newInstance();
			for (String key : map.keySet()) {
				Field nameField = cla.getDeclaredField(key);
				String name = nameField.getName();
				name = name.replaceFirst(name.substring(0, 1),
						name.substring(0, 1).toUpperCase());
				String fldtype = nameField.getType().getSimpleName();
				nameField.setAccessible(true);
				String value = objArr[map.get(key)].toString();
				Method m = null;
				if ("String".equals(fldtype)) {
					m = cla.getMethod("set" + name, String.class);
					m.invoke(t, value);
				} else if ("Integer".equals(fldtype)) {
					m = cla.getMethod("set" + name, Integer.class);
					m.invoke(t,
							Integer.valueOf(objArr[map.get(key)].toString()));
				} else if ("Date".equals(fldtype)) {
					m = cla.getMethod("set" + name, Date.class);
					m.invoke(t, sdf.parse(value));
				} else if ("Long".equals(fldtype)) {
					m = cla.getMethod("set" + name, Long.class);
					m.invoke(t, Long.valueOf(value));
				} else if ("Boolean".endsWith(fldtype)) {
					m = cla.getMethod("set" + name, Boolean.class);
					m.invoke(t, Boolean.valueOf(value));
				}
			}
			result.add(t);
		}
	}
}
