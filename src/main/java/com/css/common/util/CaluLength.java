package com.css.common.util;

import java.math.BigDecimal;

public class CaluLength {

	/**
	 * 根据生产长度、绞距、直径得到线的实际长度
	 * 
	 * @param proLength
	 * @param h
	 * @param d
	 * @return
	 */
	public static float  getFactLength(float proLength, float h, float d, float compress) {
		double length = 0;
		length = (proLength * 100 / h)
				* Math.sqrt((Math.PI * d) * (Math.PI * d) + h * h);

		if (compress > 0) {
			length = length * compress;
		}
        //凑整
        
		return (float)(Math.round(length))/100;
	}
	
}
