package com.dogpro.domain.model.extend;

import com.dogpro.domain.model.Discuss;

public class DiscussExtend extends Discuss{
	private Long discussUserId;
	private String discussNickname;
	private String discussHeadPic;
	private Long replyUserId;
	private String replyNickname;
	private int isReply;
	public Long getDiscussUserId() {
		return discussUserId;
	}
	public void setDiscussUserId(Long discussUserId) {
		this.discussUserId = discussUserId;
	}
	public String getDiscussNickname() {
		return discussNickname;
	}
	public void setDiscussNickname(String discussNickname) {
		this.discussNickname = discussNickname;
	}
	public Long getReplyUserId() {
		return replyUserId;
	}
	public void setReplyUserId(Long replyUserId) {
		this.replyUserId = replyUserId;
	}
	public String getReplyNickname() {
		return replyNickname;
	}
	public void setReplyNickname(String replyNickname) {
		this.replyNickname = replyNickname;
	}
	public int getIsReply() {
		return isReply;
	}
	public void setIsReply(int isReply) {
		this.isReply = isReply;
	}
	public String getDiscussHeadPic() {
		return discussHeadPic;
	}
	public void setDiscussHeadPic(String discussHeadPic) {
		this.discussHeadPic = discussHeadPic;
	}
}
