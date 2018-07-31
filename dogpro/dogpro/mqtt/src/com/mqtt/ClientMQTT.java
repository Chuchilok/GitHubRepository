package com.mqtt;
import java.util.concurrent.ScheduledExecutorService;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class ClientMQTT {  
  
    public static final String HOST = "tcp://192.168.199.200:1883";  
    public static final String TOPIC = "zi";  
    private static final String clientid = "client11";  
    private MqttClient client;  
    private MqttConnectOptions options;  
    private String userName = "admin";  
    private String passWord = "password";  
  
    private ScheduledExecutorService scheduler;  
  
    private void start() {  
        try {  
            // hostΪ��������clientid������MQTT�Ŀͻ���ID��һ����Ψһ��ʶ����ʾ��MemoryPersistence����clientid�ı�����ʽ��Ĭ��Ϊ���ڴ汣��  
            client = new MqttClient(HOST, clientid, new MemoryPersistence());  
            // MQTT����������  
            options = new MqttConnectOptions();  
            // �����Ƿ����session,�����������Ϊfalse��ʾ�������ᱣ���ͻ��˵����Ӽ�¼����������Ϊtrue��ʾÿ�����ӵ������������µ��������  
            options.setCleanSession(true);  
            // �������ӵ��û���  
            options.setUserName(userName);  
            // �������ӵ�����  
            options.setPassword(passWord.toCharArray());  
            // ���ó�ʱʱ�� ��λΪ��  
            options.setConnectionTimeout(10);  
            // ���ûỰ����ʱ�� ��λΪ�� ��������ÿ��1.5*20���ʱ����ͻ��˷��͸���Ϣ�жϿͻ����Ƿ����ߣ������������û�������Ļ���  
            options.setKeepAliveInterval(20);  
            // ���ûص�  
            client.setCallback(new PushCallback());  
            MqttTopic topic = client.getTopic(TOPIC);  
            //setWill�����������Ŀ����Ҫ֪���ͻ����Ƿ���߿��Ե��ø÷������������ն˿ڵ�֪ͨ��Ϣ  
            options.setWill(topic, "close".getBytes(), 2, true);  
  
            client.connect(options);  
            //������Ϣ  
            int[] Qos  = {1};  
            String[] topic1 = {TOPIC};  
            client.subscribe(topic1, Qos);  
  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    public static void main(String[] args) throws MqttException {  
        ClientMQTT client = new ClientMQTT();  
        client.start();  
    }  
}  