package com.dogpro.domain.model.extend;

import java.util.List;

import com.dogpro.domain.model.Discuss;
import com.dogpro.domain.model.FriendCircle;
import com.dogpro.domain.model.FriendCircleMedia;
import com.dogpro.domain.model.Praise;
import com.dogpro.domain.model.User;

public class FriendCircleExtend extends FriendCircle{
	private User user;
	private List<FriendCircleMedia> friendCircleMedias;
	private List<Praise> praises;
	private List<DiscussExtend> discussExtends;
	private int praiseTotal;
	private int isPraise;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<FriendCircleMedia> getFriendCircleMedias() {
		return friendCircleMedias;
	}
	public void setFriendCircleMedias(List<FriendCircleMedia> friendCircleMedias) {
		this.friendCircleMedias = friendCircleMedias;
	}
	public List<Praise> getPraises() {
		return praises;
	}
	public void setPraises(List<Praise> praises) {
		this.praises = praises;
	}
	public List<DiscussExtend> getDiscussExtends() {
		return discussExtends;
	}
	public void setDiscussExtends(List<DiscussExtend> discussExtends) {
		this.discussExtends = discussExtends;
	}
	public int getPraiseTotal() {
		return praiseTotal;
	}
	public void setPraiseTotal(int praiseTotal) {
		this.praiseTotal = praiseTotal;
	}
	public int getIsPraise() {
		return isPraise;
	}
	public void setIsPraise(int isPraise) {
		this.isPraise = isPraise;
	}
	
}
