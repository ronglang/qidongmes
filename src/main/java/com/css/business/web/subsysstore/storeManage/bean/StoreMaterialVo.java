package com.css.business.web.subsysstore.storeManage.bean;

/**
 * 原材料类型vo
 * @author Administrator
 *
 */
public class StoreMaterialVo {
	
	private Integer id;
	
	private String materialtype;
	
	private String matername;
	
	private String numbers;

	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMaterialtype() {
		return materialtype;
	}

	public void setMaterialtype(String materialtype) {
		this.materialtype = materialtype;
	}

	public String getMatername() {
		return matername;
	}

	public void setMatername(String matername) {
		this.matername = matername;
	}

	public String getNumbers() {
		if(numbers == null || numbers == ""){
			return "0";
		}
		return numbers;
	}

	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}

	
	

	

	
	
	
	
	
	
}
