package com.imserver.mqtttool.IBM;

/**
 * Created by Administrator on 2017/9/4 0004 2:26.
 */

public class MQTTMessageData {

    private String sendUid;
    private String revUid;
    private String content;
    private int type;
    private String md5;
    private String token;
    private String msgKey;

    public String getMsgKey() {
        return msgKey;
    }

    public void setMsgKey(String msgKey) {
        this.msgKey = msgKey;
    }

    public String getSendUid() {
        return sendUid;
    }

    public void setSendUid(String sendUid) {
        this.sendUid = sendUid;
    }

    public String getRevUid() {
        return revUid;
    }

    public void setRevUid(String revUid) {
        this.revUid = revUid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return String.format("{\"sendUid\":\"%s\"," +
                        "\"revUid\":\"%s\"," +
                        "\"content\":\"%s\"," +
                        "\"type\":%d," +
                        "\"md5\":\"%s\"," +
                        "\"token\":\"%s\"}",
                sendUid, revUid, content, type, md5, token);
    }
}
