package com.css.business.web.subsyssell.vo;

public class CoordinateVo {
	
	

	private String type;//三种：矩形，横线，纵线
	
	private Integer start_x;
	
	private Integer start_y;
	
	private Integer width;//矩形宽度
	
	private Integer height;//矩形高度
	
	private Integer line_length;//线长度/高度
	
	private String seqName;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getStart_x() {
		return start_x;
	}

	public void setStart_x(Integer start_x) {
		this.start_x = start_x;
	}

	public Integer getStart_y() {
		return start_y;
	}

	public void setStart_y(Integer start_y) {
		this.start_y = start_y;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getLine_length() {
		return line_length;
	}

	public void setLine_length(Integer line_length) {
		this.line_length = line_length;
	}

	public String getSeqName() {
		return seqName;
	}

	public void setSeqName(String seqName) {
		this.seqName = seqName;
	}
	
	
	
	
	
}
