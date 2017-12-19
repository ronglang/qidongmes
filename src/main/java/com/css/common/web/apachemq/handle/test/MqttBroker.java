package com.css.common.web.apachemq.handle.test;  
  
import org.apache.commons.logging.Log;  
import org.apache.commons.logging.LogFactory;  
  





import org.apache.log4j.Logger;

import com.css.common.util.PropertyFileUtil;
import com.ibm.mqtt.IMqttClient;
import com.ibm.mqtt.MqttClient;  
import com.ibm.mqtt.MqttException;  
import com.ibm.mqtt.MqttPersistence;
import com.ibm.mqtt.MqttSimpleCallback;  
  
/** 
 * MQTT消息发送与接收 
 * @author Join 
 * 
 */  
public class MqttBroker {  
    //private final static Log logger = LogFactory.getLog(MqttBroker.class);// 日志对象
	private static Logger log = Logger.getLogger(MqttBroker.class);
    // 连接参数  
    private String CONNECTION_STRING; // = "tcp://192.168.0.2@61616";  
    private final static boolean CLEAN_START = true;  
    private final static short KEEP_ALIVE = 30;// 低耗网络，但是又需要及时获取数据，心跳30s  
    private final static String CLIENT_ID = "master";// 客户端标识  
    private final static int[] QOS_VALUES = { 0, 0, 2, 0 };// 对应主题的消息级别  
    private final static String[] TOPICS = { "cc" };  
    private static MqttBroker instance = new MqttBroker();  
    
    private PropertyFileUtil pfu = new PropertyFileUtil();
  
    private IMqttClient mqttClient;  
    
    private MqttBroker(){
    	CONNECTION_STRING = pfu.getProp("APACHE_MQ_URL_MQTT");
    	//CONNECTION_STRING = "failover:(tcp://192.168.0.2:61616,tcp://192.168.0.2:61617,tcp://192.168.0.2:61618)";
    }
    
    
//    private static String MQ_IP = null;
    /*static {
    	PropertyFileUtil pfu = new PropertyFileUtil();
    	String mqurl = pfu.getProp("MQ_URL");
    	CONNECTION_STRING = mqurl;
    }*/
  
    /** 
     * 返回实例对象 
     *  
     * @return 
     */  
    public static MqttBroker getInstance() {  
        return instance;  
    }  
  
    /** 
     * 重新连接服务 
     */  
    public void connect() throws MqttException {  
/*        logger.info("connect to mqtt broker.");  
        mqttClient = new MqttClient(CONNECTION_STRING);  
        logger.info("***********register Simple Handler***********");  
        SimpleCallbackHandler simpleCallbackHandler = new SimpleCallbackHandler();  
        mqttClient.registerSimpleHandler(simpleCallbackHandler);// 注册接收消息方法  
        mqttClient.connect("cc", CLEAN_START, KEEP_ALIVE);  
        logger.info("***********subscribe receiver topics***********");  
        mqttClient.subscribe(TOPICS, QOS_VALUES);// 订阅接主题  
  
        logger.info("***********CLIENT_ID:" + CLIENT_ID);  
        *//** 
         * 完成订阅后，可以增加心跳，保持网络通畅，也可以发布自己的消息 
         *//*  
        mqttClient.publish("keepalive", "keepalive".getBytes(), QOS_VALUES[0],  
                true);// 增加心跳，保持网络通畅  
*/  
    		
    	  String mqttConnSpec = CONNECTION_STRING;
    	  
          // Create the client and connect
    	  MqttPersistence MQTT_PERSISTENCE = null;
          mqttClient = (MqttClient) MqttClient.createMqttClient(mqttConnSpec, MQTT_PERSISTENCE);
          String clientID = "master";
          mqttClient.connect(clientID, false, KEEP_ALIVE);

          SimpleCallbackHandler simpleCallbackHandler = new SimpleCallbackHandler();  
          mqttClient.registerSimpleHandler(simpleCallbackHandler);// 注册接收消息方法  

          // Subscribe to an initial topic, which is combination of client ID and device ID.
          subscribeToTopic("cc");
    }  
  
    
    public void connect(String topicName,String clientID) throws MqttException {  
    	
    	    	  String mqttConnSpec = CONNECTION_STRING;
    	          // Create the client and connect
    	    	  MqttPersistence MQTT_PERSISTENCE = null;
    	          mqttClient = (MqttClient) MqttClient.createMqttClient(mqttConnSpec, MQTT_PERSISTENCE);
    	          //String clientID = "master";
    	          mqttClient.connect(clientID, false, KEEP_ALIVE);
    	          SimpleCallbackHandler simpleCallbackHandler = new SimpleCallbackHandler();  
    	          mqttClient.registerSimpleHandler(simpleCallbackHandler);// 注册接收消息方法  
    	          //subscribeToTopic(topicName);   //web端不订阅，只发
    	    } 
    
    private void subscribeToTopic(String topicName) throws MqttException {

        if ((mqttClient == null) || (mqttClient.isConnected() == false)) {
            // quick sanity check - don't try and subscribe if we don't have
            //  a connection
            log.info("Connection error" + "No connection");
        } else {
            String[] topics = {topicName};
            int[] a={0};
            mqttClient.subscribe(topics,a );
        }
    }

    /** 
     * 发送消息 
     *  
     * @param clientId 
     * @param messageId 
     */  
    public void sendMessage(String clientId, String message) {  
        try {  
            if (mqttClient == null || !mqttClient.isConnected()) {  
              connect("master",clientId);
            }  
  
            log.info("send message to " + clientId + ", message is "  
                    + message);  
            // 发布自己的消息  
            mqttClient.publish(clientId, message.getBytes(),  
                    0, false);  

            //再发时重连
            mqttClient.disconnect();
        } catch (MqttException e) {  
        	e.printStackTrace();  
            log.error(e.getCause());  
        }  
    }  
  
    /** 
     * 简单回调函数，处理server接收到的主题消息 
     *  
     * @author Join 
     *  
     */  
    class SimpleCallbackHandler implements MqttSimpleCallback {  
  
        /** 
         * 当客户机和broker意外断开时触发 可以再此处理重新订阅 
         */  
        @Override  
        public void connectionLost() throws Exception {  
            // TODO Auto-generated method stub  
            System.out.println("客户机和broker已经断开");  
        }  
  
        /** 
         * 客户端订阅消息后，该方法负责回调接收处理消息 
         */  
        @Override  
        public void publishArrived(String topicName, byte[] payload, int Qos,  
                boolean retained) throws Exception {  
            // TODO Auto-generated method stub  
            System.out.println("订阅主题: " + topicName);  
            System.out.println("消息数据: " + new String(payload));  
            System.out.println("消息级别(0,1,2): " + Qos);  
            System.out.println("是否是实时发送的消息(false=实时，true=服务器上保留的最后消息): "  
                    + retained);  
        }  
  
    }  
   

    public static void main(String[] args) {  
        new MqttBroker().sendMessage("cc", "message"); 
    }


}  