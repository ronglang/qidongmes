package com.css.common.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.css.common.util.EhCacheFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

//import org.json.JSONArray;
//import org.json.JSONObject;

@ServerEndpoint(value = "/websocket", configurator = GetHttpSessionConfigurator.class)
public class WebSocketServlet {


	private ServletContext servletContext;

	@OnMessage
	public void onMessage(String message, Session session) throws IOException {
		// Print the client message for testing purposes
		System.out.println("Received: " + message);
		// Send the first message to the client
		// session.getBasicRemote().sendText("This is the first server message");
		// Send 3 messages to the client every 5 econds
		int allCount = 0, runCount = 0, standbyCount = 0;
		String info = "设备数量" + allCount + ",运行：" + (runCount) + ",待机："
				+ (standbyCount);
		boolean isFirst = true;

		while (session.isOpen()) {
			//例子
			//String jsonstr = EhCacheFactory.getInstance().getWebsocketCmdCache(YorkUtil.Memcache_看板_仓库);
			//用完后删除了
			//EhCacheFactory.getInstance().removeWebsocketCmdCache(YorkUtil.Memcache_看板_仓库);
			// 取得后台数据 TODO (取得后台的数量)
			if(isFirst){
				session.getBasicRemote().sendText(info);
				isFirst = false;
			}
			try {
				String str = null;
				if (servletContext.getAttribute("j_message") != null) {
					JSONObject json = (JSONObject) servletContext.getAttribute("j_message");
					StringBuffer sb = new StringBuffer();
					JSONArray lst = json.getJSONArray("data");
					try {
						for(int i = 0 ; i < lst.size(); i ++){
							JSONObject o = (JSONObject)lst.get(i);
							sb.append("设备编码:"+o.getInt("MachineID") + ";");
							//Integer pc =  o.getInt("ParameterCodeID");
							//sb.append("参数ID:" + o.getInt("ParameterCodeID") + ";");
							String par = o.getString("CollectedValue") ;
							String pname = "";
							if("0".equals(par)){
								pname = "关闭";
							}
							else if("1".equals(par)){
								pname = "运行";
							}
							else if("abc123".equals(par)){
								pname = "设备移走";
							}
							else if(par.startsWith("P")){
								pname = "新设备（"+par+"）加入，运行中";
							}
							sb.append(" 状态："+pname);
							//sb.append("参数值：" + o.get("CollectedValue") + ";");
							sb.append("; 时间：" + o.get("CollectedTime") + "\n");
						}
						
						str = sb.toString();
						servletContext.removeAttribute("user");
						
						if(str != null && str.length() > 0){
							session.getBasicRemote().sendText(str);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
					}
				}
				

//				System.out.println("从servletContext中取到的数据为"+ info);
				//推送消息到前台
				//session.getBasicRemote().sendText(str);

//				System.out.println("socket connect");

				Thread.sleep(100);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@OnOpen
	public void onOpen(Session session, EndpointConfig config) {
		HttpSession httpSession = (HttpSession) config.getUserProperties().get(
				HttpSession.class.getName());
		System.out.println(httpSession.getAttribute("name"));
		servletContext = httpSession.getServletContext();
	}

	@OnClose
	public void onClose() {
		System.out.println("Connection closed");
	}

}
