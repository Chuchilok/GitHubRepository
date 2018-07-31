package com.webpublish.mqtttool;

import com.ibm.micro.client.mqttv3.MqttCallback;
import com.ibm.micro.client.mqttv3.MqttClientPersistence;
import com.ibm.micro.client.mqttv3.MqttConnectOptions;
import com.ibm.micro.client.mqttv3.MqttDefaultFilePersistence;
import com.ibm.micro.client.mqttv3.MqttDeliveryToken;
import com.ibm.micro.client.mqttv3.MqttException;
import com.ibm.micro.client.mqttv3.MqttSecurityException;
import com.ibm.micro.client.mqttv3.MqttTopic;
import com.ibm.micro.client.mqttv3.internal.ClientComms;
import com.ibm.micro.client.mqttv3.internal.DestinationProvider;
import com.ibm.micro.client.mqttv3.internal.ExceptionHelper;
import com.ibm.micro.client.mqttv3.internal.LocalNetworkModule;
import com.ibm.micro.client.mqttv3.internal.MemoryPersistence;
import com.ibm.micro.client.mqttv3.internal.NetworkModule;
import com.ibm.micro.client.mqttv3.internal.SSLNetworkModule;
import com.ibm.micro.client.mqttv3.internal.TCPNetworkModule;
import com.ibm.micro.client.mqttv3.internal.trace.Trace;
import com.ibm.micro.client.mqttv3.internal.wire.MqttConnect;
import com.ibm.micro.client.mqttv3.internal.wire.MqttDisconnect;
import com.ibm.micro.client.mqttv3.internal.wire.MqttSubscribe;
import com.ibm.micro.client.mqttv3.internal.wire.MqttUnsubscribe;
import com.ibm.micro.internal.security.SSLSocketFactoryFactory;
import com.ibm.mqttdirect.core.MqttDirectException;

import java.lang.reflect.Constructor;
import java.util.Hashtable;
import java.util.Properties;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;

/**
 * Created by Administrator on 2017/9/4 0004 3:21.
 */
/*
 * 由于com.ibm.micro.client.mqttv3这个jar包的MqttClient不支持Client ID长度大于23，
 * 所以把他的代码重新拷出来，把>23的判断删掉
 * */
public class MyMqttClient implements DestinationProvider {
    private static final int URI_TYPE_TCP = 0;
    private static final int URI_TYPE_SSL = 1;
    private static final int URI_TYPE_LOCAL = 2;
    private String clientId;
    private String serverURI;
    private int serverURIType;
    private ClientComms comms;
    private Hashtable topics;
    private MqttClientPersistence persistence;
    private Trace trace;

    public MyMqttClient(String serverURI, String clientId) throws MqttException {
        this(serverURI, clientId, new MqttDefaultFilePersistence());
    }

    public MyMqttClient(String serverURI, String clientId, MqttClientPersistence persistence) throws MqttException {
    	//jar包里面是这样的，不知道为什么要这样  不能大于23？
    	//if(clientId != null && clientId.length() != 0 && clientId.length() <= 23) {
        if(clientId != null && !clientId.isEmpty()) {
            this.trace = Trace.getTrace(clientId);
            this.serverURI = serverURI;
            this.serverURIType = this.validateURI(serverURI);
            this.clientId = clientId;
            this.persistence = persistence;
            if(this.persistence == null) {
                this.persistence = new MemoryPersistence();
            }
           
            this.trace.trace((byte) 1, 101, new Object[]{clientId, serverURI, persistence});
            this.persistence.open(clientId, serverURI);
            this.comms = new ClientComms(this, this.persistence, this.trace);
            this.persistence.close();
            this.topics = new Hashtable();
        } else {
            throw new IllegalArgumentException();
        }
    }

    private int validateURI(String serverURI) {
        if(serverURI.startsWith("tcp://")) {
            return 0;
        } else if(serverURI.startsWith("ssl://")) {
            return 1;
        } else if(serverURI.startsWith("local://")) {
            return 2;
        } else {
            throw new IllegalArgumentException();
        }
    }

    protected NetworkModule createNetworkModule(String address, MqttConnectOptions options) throws MqttException {
        Object factory = options.getSocketFactory();
        Object netModule;
        String shortAddress;
        String host;
        int port;
        switch(this.serverURIType) {
            case 0:
                shortAddress = address.substring(6);
                host = this.getHostName(shortAddress);
                port = this.getPort(shortAddress, 1883);
                if(factory == null) {
                    factory = SocketFactory.getDefault();
                    options.setSocketFactory((SocketFactory)factory);
                } else if(factory instanceof SSLSocketFactory) {
                    throw ExceptionHelper.createMqttException(32105);
                }

                netModule = new TCPNetworkModule(this.trace, (SocketFactory)factory, host, port);
                break;
            case 1:
                shortAddress = address.substring(6);
                host = this.getHostName(shortAddress);
                port = this.getPort(shortAddress, 8883);
                SSLSocketFactoryFactory factoryFactory = null;
                if(factory == null) {
                    try {
                        factoryFactory = new SSLSocketFactoryFactory();
                        Properties enabledCiphers = options.getSSLProperties();
                        if(null != enabledCiphers) {
                            factoryFactory.initialize(enabledCiphers, (String)null);
                        }

                        factory = factoryFactory.createSocketFactory((String)null);
                    } catch (MqttDirectException var10) {
                        throw ExceptionHelper.createMqttException(var10.getCause());
                    }
                } else if(!(factory instanceof SSLSocketFactory)) {
                    throw ExceptionHelper.createMqttException(32105);
                }

                netModule = new SSLNetworkModule(this.trace, (SSLSocketFactory)factory, host, port);
                ((SSLNetworkModule)netModule).setSSLhandshakeTimeout(options.getConnectionTimeout());
                if(factoryFactory != null) {
                    String[] enabledCiphers1 = factoryFactory.getEnabledCipherSuites((String)null);
                    if(enabledCiphers1 != null) {
                        ((SSLNetworkModule)netModule).setEnabledCiphers(enabledCiphers1);
                    }
                }
                break;
            case 2:
                netModule = new LocalNetworkModule(address.substring(8));
                break;
            default:
                netModule = null;
        }

        return (NetworkModule)netModule;
    }

    private int getPort(String uri, int defaultPort) {
        int portIndex = uri.lastIndexOf(58);
        int port;
        if(portIndex == -1) {
            port = defaultPort;
        } else {
            port = Integer.valueOf(uri.substring(portIndex + 1)).intValue();
        }

        return port;
    }

    private String getHostName(String uri) {
        int schemeIndex = uri.lastIndexOf(47);
        int portIndex = uri.lastIndexOf(58);
        if(portIndex == -1) {
            portIndex = uri.length();
        }

        return uri.substring(schemeIndex + 1, portIndex);
    }

    public void connect() throws MqttSecurityException, MqttException {
        this.connect(new MqttConnectOptions());
    }

    public void connect(MqttConnectOptions options) throws MqttSecurityException, MqttException {
        if(this.isConnected()) {
            throw ExceptionHelper.createMqttException(32100);
        } else {
            if(this.trace.isOn()) {
                this.trace.trace((byte) 1, 103, new Object[]{new Boolean(options.isCleanSession()), new Integer(options.getConnectionTimeout()), new Integer(options.getKeepAliveInterval()), options.getUserName(), null == options.getPassword()?"[null]":"[notnull]", null == options.getWillMessage()?"[null]":"[notnull]"});
            }

            this.comms.setNetworkModule(this.createNetworkModule(this.serverURI, options));
            this.persistence.open(this.clientId, this.serverURI);
            if(options.isCleanSession()) {
                this.persistence.clear();
            }

            this.comms.connect(new MqttConnect(this.clientId, options.isCleanSession(), options.getKeepAliveInterval(), options.getUserName(), options.getPassword(), options.getWillMessage(), options.getWillDestination()), options.getConnectionTimeout(), (long)options.getKeepAliveInterval(), options.isCleanSession());
        }
    }

    public void disconnect() throws MqttException {
        this.disconnect(30000L);
    }

    public void disconnect(long quiesceTimeout) throws MqttException {
        this.trace.trace((byte) 1, 104, new Object[]{new Long(quiesceTimeout)});
        MqttDisconnect disconnect = new MqttDisconnect();

        try {
            this.comms.disconnect(disconnect, quiesceTimeout);
        } catch (MqttException var5) {
            this.trace.trace((byte) 1, 105, (Object[])null, var5);
            throw var5;
        }
    }

    public boolean isConnected() {
        return this.comms.isConnected();
    }

    public String getClientId() {
        return this.clientId;
    }

    public String getServerURI() {
        return this.serverURI;
    }

    public MqttTopic getTopic(String topic) {
    	//jar包里面的，不知道为什么要这样 
        if(topic.indexOf(35) == -1 && topic.indexOf(43) == -1) {
            MqttTopic result = (MqttTopic)this.topics.get(topic);
            if(result == null) {
                try {
                    Class<MqttTopic> clazz = MqttTopic.class;
                    Constructor constructor = clazz.getDeclaredConstructor(String.class, ClientComms.class);
                    constructor.setAccessible(true);
                    result = (MqttTopic) constructor.newInstance(topic, this.comms);
                    this.topics.put(topic, result);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            return result;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void subscribe(String topicFilter) throws MqttException, MqttSecurityException {
        this.subscribe(new String[]{topicFilter}, new int[]{1});
    }

    public void subscribe(String[] topicFilters) throws MqttException, MqttSecurityException {
        int[] qos = new int[topicFilters.length];

        for(int i = 0; i < qos.length; ++i) {
            qos[i] = 1;
        }

        this.subscribe(topicFilters, qos);
    }

    public void subscribe(String topicFilter, int qos) throws MqttException, MqttSecurityException {
        this.subscribe(new String[]{topicFilter}, new int[]{qos});
    }

    public void subscribe(String[] topicFilters, int[] qos) throws MqttException, MqttSecurityException {
        if(topicFilters.length != qos.length) {
            throw new IllegalArgumentException();
        } else {
            if(this.trace.isOn()) {
                String register = "";

                for(int i = 0; i < topicFilters.length; ++i) {
                    if(i > 0) {
                        register = register + ", ";
                    }

                    register = register + topicFilters[i] + ":" + qos[i];
                }

                this.trace.trace((byte) 1, 106, new Object[]{register});
            }

            MqttSubscribe var5 = new MqttSubscribe(topicFilters, qos);
            this.comms.sendAndWait(var5);
        }
    }

    public void unsubscribe(String topicFilter) throws MqttException {
        this.unsubscribe(new String[]{topicFilter});
    }

    public void unsubscribe(String[] topicFilters) throws MqttException {
        if(this.trace.isOn()) {
            String unregister = "";

            for(int i = 0; i < topicFilters.length; ++i) {
                if(i > 0) {
                    unregister = unregister + ", ";
                }

                unregister = unregister + topicFilters[i];
            }

            this.trace.trace((byte) 1, 107, new Object[]{unregister});
        }

        MqttUnsubscribe var4 = new MqttUnsubscribe(topicFilters);
        this.comms.sendAndWait(var4);
    }

    public void setCallback(MqttCallback callback) throws MqttException {
        this.comms.setCallback(callback);
    }

    public static String generateClientId() {
        return System.getProperty("user.name") + "." + System.currentTimeMillis();
    }

    public MqttDeliveryToken[] getPendingDeliveryTokens() {
        return this.comms.getPendingDeliveryTokens();
    }
}
