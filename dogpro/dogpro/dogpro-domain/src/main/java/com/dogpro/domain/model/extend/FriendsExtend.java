package com.dogpro.domain.model.extend;

import com.dogpro.domain.model.Friends;
import com.dogpro.domain.model.FriendsNote;
import com.dogpro.domain.model.User;

public class FriendsExtend extends Friends{
	private User user;
	private FriendsNote friendsNote;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public FriendsNote getFriendsNote() {
		return friendsNote;
	}
	public void setFriendsNote(FriendsNote friendsNote) {
		this.friendsNote = friendsNote;
	}
	
}
