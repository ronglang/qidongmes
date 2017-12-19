package com.css.common.web.syscommon.controller.springResolver;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.util.StringUtils;

public class TimestampPropertyEditor extends PropertyEditorSupport {
	private static final DateFormat DF_YMDHMS = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	private static final DateFormat DF_YMDHM = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm");
	private static final DateFormat DF_YMD = new SimpleDateFormat("yyyy-MM-dd");
	private static final DateFormat DF_YM = new SimpleDateFormat("yyyy-MM");
	//private static final String MSG = "日期格式不正确（只支持yyyy-MM yyyy-MM-dd yyyy-MM-dd HH:mm yyyy-MM-dd HH:mm:ss";

	public void setAsText(String text) throws IllegalArgumentException {
		text = text.trim();
		if (!StringUtils.hasText(text)) {
			setValue(null);
			return;
		}
		try {
			if ((isNumeric(text)) && (text.length() > 8)) {
				setValue(new Date(Long.parseLong(text)));
			} else if (text.length() == 7) {
				setValue(new Date(DF_YM.parse(text).getTime()));
			} else if (text.length() == 10) {
				setValue(new Date(DF_YMD.parse(text).getTime()));
			} else if (text.length() == 16) {
				setValue(new Date(DF_YMDHM.parse(text).getTime()));
			} else if (text.length() == 19) {
				setValue(new Date(DF_YMDHMS.parse(text).getTime()));
			} else {
				throw new Exception();
			}
		} catch (Exception ex) {
			IllegalArgumentException iae = new IllegalArgumentException(
					"日期格式不正确（只支持yyyy-MM yyyy-MM-dd yyyy-MM-dd HH:mm yyyy-MM-dd HH:mm:ss"
							+ ex.getMessage());
			iae.initCause(ex);
			throw iae;
		}
	}

	public String getAsText() {
		Date value = (Date) getValue();
		return value != null ? DF_YMDHMS.format(value) : "";
	}

	private static boolean isNumeric(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
}
