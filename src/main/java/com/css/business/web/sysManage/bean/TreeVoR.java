
package com.css.business.web.sysManage.bean;
/**     
 * @Title: TreeVoR.java   
 * @Package com.css.business.web.sysManage.bean   
 * @Description: 返回tree的vo   
 * @author   rb
 * @date 2017年7月26日 上午11:16:51   
 * @company  SMTC   
 */

public class TreeVoR {
	
	public String id;
	public String pid;
	public String name;
	public String isParent;
	/**
	 * @return  id
	 */
	public String getId() {
		return id;
	}
	/**
	 *  @param id 要设置的 id 
	 *    
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return  pid
	 */
	public String getPid() {
		return pid;
	}
	/**
	 *  @param pid 要设置的 pid 
	 *    
	 */
	public void setPid(String pid) {
		this.pid = pid;
	}
	/**
	 * @return  name
	 */
	public String getName() {
		return name;
	}
	/**
	 *  @param name 要设置的 name 
	 *    
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return  isParent
	 */
	public String getIsParent() {
		return isParent;
	}
	/**
	 *  @param isParent 要设置的 isParent 
	 *    
	 */
	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}
	
}
