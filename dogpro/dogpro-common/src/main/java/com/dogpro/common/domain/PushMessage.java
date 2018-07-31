package com.dogpro.common.domain;

import java.io.Serializable;

public class PushMessage implements Serializable {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 6093207749163393437L;

	private Long revuid;

	    private Long senduid;

	    private Integer type;

	    private Long msgcode;

	    private Integer state;

	    private Long targetid;
	    
	    private String content;
		
	    public Long getRevuid() {
			return revuid;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public void setRevuid(Long revuid) {
			this.revuid = revuid;
		}

		public Long getSenduid() {
			return senduid;
		}

		public void setSenduid(Long senduid) {
			this.senduid = senduid;
		}

		public Integer getType() {
			return type;
		}

		public void setType(Integer type) {
			this.type = type;
		}

		public Long getMsgcode() {
			return msgcode;
		}

		public void setMsgcode(Long msgcode) {
			this.msgcode = msgcode;
		}

		public Integer getState() {
			return state;
		}

		public void setState(Integer state) {
			this.state = state;
		}

		public Long getTargetid() {
			return targetid;
		}

		public void setTargetid(Long targetid) {
			this.targetid = targetid;
		}


	

}
