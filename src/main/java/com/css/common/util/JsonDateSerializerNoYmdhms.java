package com.css.common.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * yyyy-MM-dd HH:mm:ss
 * @author Administrator
 *
 */
public class JsonDateSerializerNoYmdhms extends JsonSerializer<Date> {
	private SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");  

	@Override
	public void serialize(Date date, JsonGenerator gen, SerializerProvider arg2)
			throws IOException, JsonProcessingException {
		// TODO Auto-generated method stub
		String value = dateFormat.format(date);  
	       gen.writeString(value);  
	}

}
