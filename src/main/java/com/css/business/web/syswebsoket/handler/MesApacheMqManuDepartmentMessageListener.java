/**     
*
*/   
package com.css.business.web.syswebsoket.handler;

import java.io.Serializable;

import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.web.context.WebApplicationContext;







import com.css.business.web.syswebsoket.maudepartwebsocket.ProductDepartmentChartWebSocket;
import com.css.business.web.syswebsoket.maudepartwebsocket.ProductDepartmentPineWebSocket;
import com.css.business.web.syswebsoket.maudepartwebsocket.ProductDepartmentShowPlanWebS;
import com.css.commcon.util.ActiveMQUtil;
import com.css.commcon.util.YorkUtil;
import com.css.common.servlet.WebSocketServlet;
import com.css.common.util.EhCacheFactory;
import com.css.common.util.PropertyFileUtil;

/**     
 * @Package com.css.business.web.syswebsoket   
 * @Description: 针对  生产部的电子看板做的activeMQ的监听
 * @author   wl
 * @date 2017年7月13日 下午5:13:55   
 * @company  SMTC   
 */
@Deprecated
public class MesApacheMqManuDepartmentMessageListener {//implements Serializable,MessageListener{

	/**   
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	*/ 
	private static final long serialVersionUID = 452563188320006838L;

	private WebApplicationContext wac;
	/**
	 *引入生产部电子看板的cash 
	 */
	
	 EhCacheFactory cacheFactory = EhCacheFactory.getInstance();
	 
	 
	/*private String cachName=YorkUtil.Memcache_看板_生产部;
	private String cachName_now=YorkUtil.Memcache_看板_生产部_实时;*/
	/** 生产电子看板的异常websocket */
	private ProductDepartmentShowPlanWebS productDepartmentShowPlanWebS ;
	private ProductDepartmentChartWebSocket productDepartmentChartWebSocket;
	private ProductDepartmentPineWebSocket productDepartmentPineWebSocket;
	private  PropertyFileUtil propertyFileUtil = new PropertyFileUtil();
	public MesApacheMqManuDepartmentMessageListener(final WebApplicationContext wac){
		this.wac = wac;
		this.init();
	}
	private void init(){
		try {
			String name = propertyFileUtil.getProp("KB_DEP_MANU");
			MessageConsumer messageConsumer = ActiveMQUtil.getMessageConsumer_topic(name);
			//初始化MessageListener
			//给消费者设定监听对象
			//messageConsumer.setMessageListener(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
    
	
	//@Override
	public void onMessage(Message msg) {
		// TODO Auto-generated method stub
		TextMessage txtMessage = (TextMessage) msg;
		try {
			System.out.println("get message:" + txtMessage.getText());
			String text = txtMessage.getText();
			/*if(text.equals("生产部延迟情况")){
				cacheFactory.addWebsocketCmdCache(cachName, "生产");
			}else if(text.equals("饼状图")){
				cacheFactory.addWebsocketCmdCache(cachName_now, "进度完成");
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	/**
	 * @return  wac
	 */
	public WebApplicationContext getWac() {
		return wac;
	}
	/**
	 *  @param wac 要设置的 wac 
	 *    
	 */
	public void setWac(WebApplicationContext wac) {
		this.wac = wac;
	}
	public ProductDepartmentShowPlanWebS getProductDepartmentShowPlanWebS() {
		return productDepartmentShowPlanWebS;
	}
	public void setProductDepartmentShowPlanWebS(
			ProductDepartmentShowPlanWebS productDepartmentShowPlanWebS) {
		this.productDepartmentShowPlanWebS = productDepartmentShowPlanWebS;
	}

	
	 
}
