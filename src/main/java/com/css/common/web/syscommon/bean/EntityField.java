package com.css.common.web.syscommon.bean;

import java.lang.reflect.Field;

public class EntityField {

	//列名
	private String col;
	private String colName;

	//类型
	private Class<?> type;
	private String typeCode;
	private String typeName;
	
	//长度
	private Integer colLen;
	//精度
	private Integer colPrecision; 
	
	private Field field;

	public EntityField() {
	}

	public EntityField(String colName) {
		this.colName = colName;
	}
	
	public EntityField(String col, Field field) {
		super();
		this.col = col;
		this.field = field;
		this.colName = field.getName();
		this.type = field.getType();

	}
	
	public EntityField(String col, Field field,String colName) {
		super();
		this.col = col;
		this.field = field;
		this.colName = colName;
		this.type = field.getType();

	}

	public EntityField(String col, String colName, Class<?> type) {
		super();
		this.col = col;
		this.colName = colName;
		this.type = type;
	}

	public String getCol() {
		return col;
	}

	public void setCol(String col) {
		this.col = col;
	}

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getColLen() {
		return colLen;
	}

	public void setColLen(Integer colLen) {
		this.colLen = colLen;
	}

	public Integer getColPrecision() {
		return colPrecision;
	}

	public void setColPrecision(Integer colPrecision) {
		this.colPrecision = colPrecision;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}
	

}