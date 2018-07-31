package com.dogpro.common.tool;

import com.alibaba.fastjson.JSONObject;
import com.dogpro.common.domain.IMsend;
import com.dogpro.common.domain.PushMessage;
import com.dogpro.common.tool.upush.android.AndroidUnicast;
import com.dogpro.common.tool.upush.push.AndroidNotification;
import com.dogpro.common.tool.upush.push.PushClient;

public class UmengPush {
	private PushClient pushClient;
//	private  String appkey = "5a5ec305b27b0a1457000112";
//	private  String app_master_secret = "cfvpzdbwk7iap026aeosu32jsuunvtui";
	private String appkey;
	private String app_master_secret;
	public  UmengPush() {
		pushClient = new PushClient();
	}
	public  UmengPush(String appkey,String app_master_secret) {
		pushClient = new PushClient();
		this.appkey = appkey;
		this.app_master_secret = app_master_secret;
	}
	public  void  sendAndroidUnicast(String DeviceToken,String Ticker,String Title,String Text) throws Exception{
		AndroidUnicast unicast = new AndroidUnicast(appkey,app_master_secret);
		unicast.setDeviceToken(DeviceToken);
		unicast.setTicker(Ticker);
		unicast.setTitle(Title);
		unicast.setText(Text);
		unicast.goAppAfterOpen();
		unicast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
		//正式模式
		unicast.setProductionMode();
		pushClient.send(unicast);
		
	}
	public  void  sendAndroidUnicast(String DeviceToken,String Ticker,String Title,String Text,JSONObject goCustomAfterOpen) throws Exception{
		AndroidUnicast unicast = new AndroidUnicast(appkey,app_master_secret);
		unicast.setDeviceToken(DeviceToken);
		unicast.setTicker(Ticker);
		unicast.setTitle(Title);
		unicast.setText(Text);
		unicast.goCustomAfterOpen(goCustomAfterOpen);
		unicast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
		//正式模式
		unicast.setProductionMode();
		pushClient.send(unicast);
	}
	
	public void sendAndroidUnicast(String Ticker,String Title,IMsend iMsend) throws Exception{
		String DeviceToken = iMsend.getToken();
		String pushMessageJSONstring = iMsend.getContent();
		JSONObject jsonObject = JSONObject.parseObject(pushMessageJSONstring);
		PushMessage pushMessage = JSONObject.toJavaObject(jsonObject, PushMessage.class);
		sendAndroidUnicast(DeviceToken,  Ticker, Title,pushMessage.getContent(), jsonObject);
	}
//	public static void main(String[] args) throws Exception {
//		PushMessage pushMessage = new PushMessage();
//		pushMessage.setTargetid(111l);
//		pushMessage.setType(3);
//		String jsonString = JSONObject.toJSONString(pushMessage);
//		JSONObject jsonObject = JSONObject.parseObject(jsonString);
//		UmengPush umengPush = new UmengPush("5a5ec305b27b0a1457000112","cfvpzdbwk7iap026aeosu32jsuunvtui");
//		umengPush.sendAndroidUnicast("ArHE2QH0DuFi-VqZEiDLl2qwetKhqF8lppoXOHGtetgZ", "Ticker", "PetMan", "main Text",jsonObject);
//		new UmengPush().sendAndroidUnicast("AmGq0VIDFDewTqobcQKjfh-ACX6qVGQCxRLPhqMzklax", "Ticker", "PetMan", "Text");
//		new UmengPush().sendAndroidUnicast("AvzjlZQkMO6BtVMKKJgrX1_QSgvlhL6Fuye5GvIRsVRL", "Ticker", "PetMan", "Text");
//
//	}
}
