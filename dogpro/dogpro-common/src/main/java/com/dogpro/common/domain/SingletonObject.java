package com.dogpro.common.domain;

public class SingletonObject {
	private static SingletonObject instance = new SingletonObject();

	private SingletonObject() {
	}

	public static SingletonObject newInstance() {
		return instance;
	}
}
