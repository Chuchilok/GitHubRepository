package com.dogpro.domain.model.extend;

import com.dogpro.domain.model.ServiceRecord;
import com.dogpro.domain.model.User;

public class ServiceRecord_User extends ServiceRecord{
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
