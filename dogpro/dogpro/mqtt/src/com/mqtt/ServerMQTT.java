package com.mqtt;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class ServerMQTT {  
  
    //tcp://MQTT��װ�ķ�������ַ:MQTT����Ķ˿ں�  
    public static final String HOST = "tcp://192.168.199.200:1883";  
    //����һ������  
    public static final String TOPIC = "topic11";  
    //����MQTT��ID��������MQTT����������ָ��  
    private static final String clientid = "server11";  
  
    private MqttClient client;  
    private MqttTopic topic11;  
    private String userName = "z";  
    private String passWord = "";  
  
    private MqttMessage message;  
  
    /** 
     * ���캯�� 
     * @throws MqttException 
     */  
    public ServerMQTT() throws MqttException {  
        // MemoryPersistence����clientid�ı�����ʽ��Ĭ��Ϊ���ڴ汣��  
        client = new MqttClient(HOST, clientid, new MemoryPersistence());  
        connect();  
    }  
  
    /** 
     *  �������ӷ����� 
     */  
    private void connect() {  
        MqttConnectOptions options = new MqttConnectOptions();  
        options.setCleanSession(false);  
        options.setUserName(userName);  
        options.setPassword(passWord.toCharArray());  
        // ���ó�ʱʱ��  
        options.setConnectionTimeout(10);  
        // ���ûỰ����ʱ��  
        options.setKeepAliveInterval(20);  
        try {  
            client.setCallback(new PushCallback());  
            client.connect(options);  
  
            topic11 = client.getTopic(TOPIC);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    /** 
     * 
     * @param topic 
     * @param message 
     * @throws MqttPersistenceException 
     * @throws MqttException 
     */  
    public void publish(MqttTopic topic , MqttMessage message) throws MqttPersistenceException,  
            MqttException {  
        MqttDeliveryToken token = topic.publish(message);  
        token.waitForCompletion();  
        System.out.println("message is published completely! "  
                + token.isComplete());  
    }  
  
    /** 
     *  ������� 
     * @param args 
     * @throws MqttException 
     */  
    public static void main(String[] args) throws MqttException {  
        ServerMQTT server = new ServerMQTT();  
  
        server.message = new MqttMessage();  
        server.message.setQos(1);  
        server.message.setRetained(true);  
        server.message.setPayload("hello,topic11".getBytes());  
        server.publish(server.topic11 , server.message);  
        System.out.println(server.message.isRetained() + "------ratained״̬");  
    }  
}  