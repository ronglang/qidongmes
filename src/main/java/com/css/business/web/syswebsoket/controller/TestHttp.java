package com.css.business.web.syswebsoket.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.junit.Test;

import com.css.business.web.syswebsoket.storewebsocket.storeCallWebSocket;

public class TestHttp extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TestHttp(){
		
	}
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("aaaaaaaaaa");
		super.init();
	}
	
	@Test
	public void testJS(){
		storeCallWebSocket storeCallWebSocket = new storeCallWebSocket();
		
		String text = "[{\"machineId\":1,\"createBy\":\"admin\",\"courseCode\":\"工单编号01\",\"amount\":1,\"id\":1,\"materId\":1,\"unit\":\"公斤\",\"axisName\":\"生产令编号_01\",\"planSendTime\":{\"nanos\":0,\"time\":1499769235000,\"minutes\":33,\"seconds\":55,\"hours\":18,\"month\":6,\"year\":117,\"timezoneOffset\":-480,\"day\":2,\"date\":11},\"flag\":\"否\",\"materName\":\"\",\"ggxh\":\"sss\",\"machinePlanId\":1,\"createDate\":{\"nanos\":0,\"time\":1499702400000,\"minutes\":0,\"seconds\":0,\"hours\":0,\"month\":6,\"year\":117,\"timezoneOffset\":-480,\"day\":2,\"date\":11},\"sendState\":\"否\"}]";
		//storeCallWebSocket.getStoreCall(text );
		
	}
	

}
