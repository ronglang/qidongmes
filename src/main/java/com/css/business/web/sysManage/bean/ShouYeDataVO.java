package com.css.business.web.sysManage.bean;


public class ShouYeDataVO {
	   public ShouYePopVO pop;//贫困人口减贫
	   public ShouYeVilVO vil; //贫困村摘帽
	   public ShouYeCouVO cou;//贫困县摘帽
	   public ShouYeZjzhVO zjzh;//资金整合
	   public ShouYeWgypVO wgyp;//五个一批
	   public ShouYeViltpVO viltp;//村规划
	   public ShouYeBmzjVO bmzj;//十七个专项
	   public ShouYeLpfpVO lpfp;//连片扶贫
	   public ShouYeTzVO tz;//一本台账建立情况
	   public String num_cou;//县个数
	   public String jc_cou;//监测县个数
	   public String jc_cou_green;//监测县时序正常 绿灯
	   public String jc_cou_yellow;//监测县时序滞后 黄灯
	   public String jc_cou_red;//监测县时序严重滞后 红灯
	   public String jc_town;//监测乡镇个数
	   public String jc_town_green;//监测乡镇时序正常   绿灯
	   public String jc_town_yellow;//监测乡镇时序滞后   黄灯
	   public String jc_town_red;////监测乡镇时序严重滞后  红灯
	   public String jc_vil;//监测村个数
	   public String jc_vil_green;//监测村时序正常   绿灯
	   public String jc_vil_yellow;//监测村时序滞后   黄灯
	   public String jc_vil_red;//监测村时序严重滞后 红灯
	   
	public ShouYeDataVO(ShouYePopVO pop, ShouYeVilVO vil, ShouYeCouVO cou, ShouYeZjzhVO zjzh, ShouYeWgypVO wgyp,
			ShouYeViltpVO viltp, ShouYeBmzjVO bmzj, ShouYeLpfpVO lpfp, ShouYeTzVO tz, String num_cou, String jc_cou,
			String jc_cou_green, String jc_cou_yellow, String jc_cou_red, String jc_town, String jc_town_green,
			String jc_town_yellow, String jc_town_red, String jc_vil, String jc_vil_green, String jc_vil_yellow,
			String jc_vil_red) {
		super();
		this.pop = pop;
		this.vil = vil;
		this.cou = cou;
		this.zjzh = zjzh;
		this.wgyp = wgyp;
		this.viltp = viltp;
		this.bmzj = bmzj;
		this.lpfp = lpfp;
		this.tz = tz;
		this.num_cou = num_cou;
		this.jc_cou = jc_cou;
		this.jc_cou_green = jc_cou_green;
		this.jc_cou_yellow = jc_cou_yellow;
		this.jc_cou_red = jc_cou_red;
		this.jc_town = jc_town;
		this.jc_town_green = jc_town_green;
		this.jc_town_yellow = jc_town_yellow;
		this.jc_town_red = jc_town_red;
		this.jc_vil = jc_vil;
		this.jc_vil_green = jc_vil_green;
		this.jc_vil_yellow = jc_vil_yellow;
		this.jc_vil_red = jc_vil_red;
	}
	public ShouYeDataVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ShouYePopVO getPop() {
		return pop;
	}
	public void setPop(ShouYePopVO pop) {
		this.pop = pop;
	}
	public ShouYeVilVO getVil() {
		return vil;
	}
	public void setVil(ShouYeVilVO vil) {
		this.vil = vil;
	}
	public ShouYeCouVO getCou() {
		return cou;
	}
	public void setCou(ShouYeCouVO cou) {
		this.cou = cou;
	}
	public ShouYeZjzhVO getZjzh() {
		return zjzh;
	}
	public void setZjzh(ShouYeZjzhVO zjzh) {
		this.zjzh = zjzh;
	}
	public ShouYeWgypVO getWgyp() {
		return wgyp;
	}
	public void setWgyp(ShouYeWgypVO wgyp) {
		this.wgyp = wgyp;
	}
	public ShouYeViltpVO getViltp() {
		return viltp;
	}
	public void setViltp(ShouYeViltpVO viltp) {
		this.viltp = viltp;
	}
	public ShouYeBmzjVO getBmzj() {
		return bmzj;
	}
	public void setBmzj(ShouYeBmzjVO bmzj) {
		this.bmzj = bmzj;
	}
	public ShouYeLpfpVO getLpfp() {
		return lpfp;
	}
	public void setLpfp(ShouYeLpfpVO lpfp) {
		this.lpfp = lpfp;
	}
	public ShouYeTzVO getTz() {
		return tz;
	}
	public void setTz(ShouYeTzVO tz) {
		this.tz = tz;
	}
	public String getNum_cou() {
		return num_cou;
	}
	public void setNum_cou(String num_cou) {
		this.num_cou = num_cou;
	}
	public String getJc_cou() {
		return jc_cou;
	}
	public void setJc_cou(String jc_cou) {
		this.jc_cou = jc_cou;
	}
	public String getJc_cou_green() {
		return jc_cou_green;
	}
	public void setJc_cou_green(String jc_cou_green) {
		this.jc_cou_green = jc_cou_green;
	}
	public String getJc_cou_yellow() {
		return jc_cou_yellow;
	}
	public void setJc_cou_yellow(String jc_cou_yellow) {
		this.jc_cou_yellow = jc_cou_yellow;
	}
	public String getJc_cou_red() {
		return jc_cou_red;
	}
	public void setJc_cou_red(String jc_cou_red) {
		this.jc_cou_red = jc_cou_red;
	}
	public String getJc_town() {
		return jc_town;
	}
	public void setJc_town(String jc_town) {
		this.jc_town = jc_town;
	}
	public String getJc_town_green() {
		return jc_town_green;
	}
	public void setJc_town_green(String jc_town_green) {
		this.jc_town_green = jc_town_green;
	}
	public String getJc_town_yellow() {
		return jc_town_yellow;
	}
	public void setJc_town_yellow(String jc_town_yellow) {
		this.jc_town_yellow = jc_town_yellow;
	}
	public String getJc_town_red() {
		return jc_town_red;
	}
	public void setJc_town_red(String jc_town_red) {
		this.jc_town_red = jc_town_red;
	}
	public String getJc_vil() {
		return jc_vil;
	}
	public void setJc_vil(String jc_vil) {
		this.jc_vil = jc_vil;
	}
	public String getJc_vil_green() {
		return jc_vil_green;
	}
	public void setJc_vil_green(String jc_vil_green) {
		this.jc_vil_green = jc_vil_green;
	}
	public String getJc_vil_yellow() {
		return jc_vil_yellow;
	}
	public void setJc_vil_yellow(String jc_vil_yellow) {
		this.jc_vil_yellow = jc_vil_yellow;
	}
	public String getJc_vil_red() {
		return jc_vil_red;
	}
	public void setJc_vil_red(String jc_vil_red) {
		this.jc_vil_red = jc_vil_red;
	}
	   
}
