package com.webpublish.main;

import com.dogpro.common.tool.HttpRequest;

public class test {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		for (int i = 0; i < 5; i++) {
			Thread task = new Thread(new Runnable() {

				public void run() {
					// TODO Auto-generated method stub
					for (int i = 0; i < 1000; i++) {
						System.out.println(HttpRequest.sendHtpps("", "https://192.168.199.69/index1.jsp"));
					}
				}
			});
			task.start();
		}
	}
}
