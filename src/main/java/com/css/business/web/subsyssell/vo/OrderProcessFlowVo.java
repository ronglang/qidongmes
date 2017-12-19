package com.css.business.web.subsyssell.vo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.CompareToBuilder;

/**
 * 订单工艺流程
 * 
 * @author Administrator
 * 
 */
public class OrderProcessFlowVo implements  Comparable<OrderProcessFlowVo>{

	public static final Integer RECTANGLE_WIDTH = 100;// 矩形宽度

	public static final Integer RECTANGLE_HEIGHT = 50;// 矩形高度

	private List<String> seqNameList;

	private List<String> seqCodeList;
	
	private List<Integer[]> coordinateList; 

	private Integer rectangle_start_x;// 整个串行线的起点横坐标

	private Integer rectangle_start_y;// 整个串行线的起点纵坐标

	private Integer rectangle_end_x;// 整条串行线的终点横坐标

	private Integer rectangle_end_y;// 整条串行线的终点纵坐标

	private Integer level;// 级别，表示是第几级别，值越大优先级越高，表示第一行，靠左

	private String routeCode;// 产品工艺路线
	
	
	




	private String proGgxh;// 产品规格型号

	private String craftCode;// 工艺编码

	private String parentCraftCode;// 父级产品工艺编码
	
	private Integer level_start_x;
	
	private Integer level_start_y;
	
	private Integer level_end_x;
	
	private Integer level_end_y;
	
	private boolean is_y;//是否有向下连接线；
	
	
	
	

	public boolean isIs_y() {
		return is_y;
	}

	public void setIs_y(boolean is_y) {
		this.is_y = is_y;
	}

	public List<Integer[]> getCoordinateList() {
		if(coordinateList == null){
			coordinateList = new ArrayList<Integer[]>();
		}
		return coordinateList;
	}

	public void setCoordinateList(List<Integer[]> coordinateList) {
		this.coordinateList = coordinateList;
	}

	public Integer getLevel_start_x() {
		return level_start_x;
	}

	public void setLevel_start_x(Integer level_start_x) {
		this.level_start_x = level_start_x;
	}

	public Integer getLevel_start_y() {
		return level_start_y;
	}

	public void setLevel_start_y(Integer level_start_y) {
		this.level_start_y = level_start_y;
	}

	public Integer getLevel_end_x() {
		return level_end_x;
	}

	public void setLevel_end_x(Integer level_end_x) {
		this.level_end_x = level_end_x;
	}

	public Integer getLevel_end_y() {
		return level_end_y;
	}

	public void setLevel_end_y(Integer level_end_y) {
		this.level_end_y = level_end_y;
	}

	public Integer getRectangle_end_x() {
		return rectangle_end_x;
	}

	public void setRectangle_end_x(Integer rectangle_end_x) {
		this.rectangle_end_x = rectangle_end_x;
	}

	public Integer getRectangle_end_y() {
		return rectangle_end_y;
	}

	public void setRectangle_end_y(Integer rectangle_end_y) {
		this.rectangle_end_y = rectangle_end_y;
	}

	public List<String> getSeqCodeList() {
		if (seqCodeList == null)
			seqCodeList = new ArrayList<String>();
		return seqCodeList;
	}

	public void setSeqCodeList(List<String> seqCodeList) {
		this.seqCodeList = seqCodeList;
	}

	public String getCraftCode() {
		return craftCode;
	}

	public void setCraftCode(String craftCode) {
		this.craftCode = craftCode;
	}

	public String getParentCraftCode() {
		return parentCraftCode;
	}

	public void setParentCraftCode(String parentCraftCode) {
		this.parentCraftCode = parentCraftCode;
	}

	public String getProGgxh() {
		return proGgxh;
	}

	public void setProGgxh(String proGgxh) {
		this.proGgxh = proGgxh;
	}

	public List<String> getSeqNameList() {
		if (seqNameList == null)
			seqNameList = new ArrayList<String>();
		return seqNameList;
	}

	public void setSeqNameList(List<String> seqNameList) {
		this.seqNameList = seqNameList;
	}

	public Integer getRectangle_start_x() {
		return rectangle_start_x;
	}

	public void setRectangle_start_x(Integer rectangle_start_x) {
		this.rectangle_start_x = rectangle_start_x;
	}

	public Integer getRectangle_start_y() {
		return rectangle_start_y;
	}

	public void setRectangle_start_y(Integer rectangle_start_y) {
		this.rectangle_start_y = rectangle_start_y;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getRouteCode() {
		return routeCode;
	}

	public void setRouteCode(String routeCode) {
		this.routeCode = routeCode;
	}

	public static void main(String[] args) {
		// 已经按照先后顺序排好
		List<OrderProcessFlowVo> list = new ArrayList<OrderProcessFlowVo>();
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < args.length; j++) {
				// 1.绘制矩形
				// 2.绘制横线（i不是最后一个节点，否则不绘制）
				// 3.绘制纵线
			}

		}
	}



//	@Override
//	public int compare(OrderProcessFlowVo o1, OrderProcessFlowVo o2) {
//		if (o1.level > o2.level) {
//			return 1;
//		} else if (o1.level < o2.level) {
//			return -1;
//		} else {
//			return 1;
//		}
//	}

	@Override
	public int compareTo(OrderProcessFlowVo o) {
		return new CompareToBuilder().append(o.level, level).toComparison();
	}
}
