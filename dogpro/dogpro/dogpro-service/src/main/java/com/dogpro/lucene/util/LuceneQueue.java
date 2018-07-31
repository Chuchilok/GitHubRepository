package com.dogpro.lucene.util;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.dogpro.domain.model.DogLocation;

public class LuceneQueue {
	private static BlockingQueue<DogLocation> queue = new LinkedBlockingQueue<DogLocation>();
	public  static void pushQueue(DogLocation dogLocation){
		try {
			queue.put(dogLocation);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public  static DogLocation popQueue(){
		DogLocation dogLocation = null;
		try {
			dogLocation = queue.take();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return dogLocation;
	}
	
}
