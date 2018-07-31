import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.dogpro.common.domain.PushMessage;
import com.zpush.Notification;
import com.zpush.NotificationBuilder;


public class Test {
	public static void main(String[] args) {
		System.out.println("IOS_123".substring(4));
		PushMessage pushMessage = new PushMessage();
		pushMessage.setContent("你好！");
		Map values = new HashMap();
		values.put("arg1", "其他(发送者)");
		values.put("arg2", "其他1(接收者)");
		Notification no = new NotificationBuilder()
//		.setAlert("nihao")
		.setToken("a7447c71a0c531c5600ba891e88b0327adb356151526526091428593ff920070")
		.setAlertBody("123")
		.setBadge(1)
		.setSound("default")
		.setUserProperty("params",values )
		.setType(1)
		.build();
		System.out.println(no.payloadJSONString());
		JSONObject json =  JSONObject.parseObject(JSONObject.toJSONString(pushMessage));
		PushMessage object = JSONObject.toJavaObject(json, PushMessage.class);
	}
}
