package com.css.business.web.subsysmanu.mauManage.bean;

public class Student {
	private Integer age;
	private String name;
	private String sex;
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Student(Integer age, String name, String sex) {
		super();
		this.age = age;
		this.name = name;
		this.sex = sex;
	}
}
