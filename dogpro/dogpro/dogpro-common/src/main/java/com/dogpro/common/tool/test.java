package com.dogpro.common.tool;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.inject.New;

import com.alibaba.fastjson.JSONObject;

public class test {
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
			Thread thread = new Thread(new Runnable() {
				public void run() {
					while (true) {
						System.out.println(new Date());
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			});
			thread.start();
	}
}
