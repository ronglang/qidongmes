package com.css.common.web.syscommon.bean;

import java.util.ArrayList;
import java.util.List;

public class ExcelGridVo {
	private Integer columnindex;
	private String columnname;
	private String display;
	private String type;
	private Boolean islast;
	private Integer width;
	private Boolean hide;
	private String id;
	private String pid;
	private Integer colspan=1;
	private Integer rowspan=1;
	private Boolean ischild = false;
	
	//冗余，计算用
	private int rownum = 1;//第几行
	
	private List<ExcelGridVo> colchild = new ArrayList<ExcelGridVo>();
	
	public Integer getColumnindex() {
		return columnindex;
	}
	public void setColumnindex(Integer columnindex) {
		this.columnindex = columnindex;
	}
	public String getColumnname() {
		return columnname;
	}
	public void setColumnname(String columnname) {
		this.columnname = columnname;
	}
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Boolean getIslast() {
		return islast;
	}
	public void setIslast(Boolean islast) {
		this.islast = islast;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public Boolean getHide() {
		return hide;
	}
	public void setHide(Boolean hide) {
		this.hide = hide;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public Integer getColspan() {
		return colspan;
	}
	public void setColspan(Integer colspan) {
		this.colspan = colspan;
	}
	public List<ExcelGridVo> getColchild() {
		return colchild;
	}
	public void setColchild(List<ExcelGridVo> colchild) {
		this.colchild = colchild;
	}
	public Boolean getIschild() {
		return ischild;
	}
	public void setIschild(Boolean ischild) {
		this.ischild = ischild;
	}
	public Integer getRowspan() {
		return rowspan;
	}
	public void setRowspan(Integer rowspan) {
		this.rowspan = rowspan;
	}
	public int getRownum() {
		return rownum;
	}
	public void setRownum(int rownum) {
		this.rownum = rownum;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExcelGridVo other = (ExcelGridVo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
