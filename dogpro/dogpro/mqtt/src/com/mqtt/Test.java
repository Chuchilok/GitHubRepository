package com.mqtt;

import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class Test {
     
	private static String hostName="tcp://192.168.199.200:1883";//"tcp://iot.eclipse.org:1883";
    public static String uuid="102";
	public static String token="xx";	
	private static String username= uuid ;
	private static String password= token ;
	private static String subscribeTopic= "102" ;
	private static String publisheTopic= "101" ;
	private static String sendMsg="{\"devices\":\""+uuid+"\",\"payload\":{\"param1\":\"��á�\"}}";
	private static MqttClient client ;
	public static void main(String[] args) {
	     //������Ϣ�ķ���  
		subscribe(); 
        //������Ϣ����  
		//publish();
	}

	public static String subscribe() {  //���� 
	        try {   
	            //����MqttClient  
	        	client=new MqttClient(hostName,"01");   
	            client.setCallback(new PushCallback());
	            MqttConnectOptions conOptions = new MqttConnectOptions();   
	            conOptions.setUserName(username);
	            conOptions.setPassword(password.toCharArray());
	            conOptions.setCleanSession(false);   
	            client.connect(conOptions);   
	            client.subscribe(subscribeTopic, 2); 
	            boolean isSuccess =client.isConnected();
	            System.out.println("����״̬:"+isSuccess);
	            //client.disconnect();   
	        } catch (Exception e) {   
	            e.printStackTrace();   
	            return "failed";   
	        }   
	        return "success";   
	    } 
	  
	    public static void publish(){   //����
	        try {   
	            MqttTopic topic = client.getTopic(publisheTopic);   
	            System.out.println("���͵���Ϣ����Ϊ:"+sendMsg);
	            MqttMessage message = new MqttMessage(sendMsg.getBytes());   
	            message.setQos(1);   
	            //while(true){  
	                MqttDeliveryToken token = topic.publish(message); 
	                while (!token.isComplete()){   
	                	
	                    token.waitForCompletion(1000);   
	                }   
	            //}  
	        } catch (Exception e) {   
	            e.printStackTrace();   
	        }   
	    }  
}
