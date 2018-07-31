package com.dogpro.service.listener;

import com.dogpro.domain.model.DogLocation;
import com.dogpro.lucene.index.DoglocationIndex;
import com.dogpro.lucene.util.LuceneQueue;

public class LuceneThread extends Thread{
	private DoglocationIndex doglocationIndex = new DoglocationIndex();
	private long sleeptime = 1000;
	public void run(){
		while(true){
			DogLocation dogLocation = LuceneQueue.popQueue();
			if(dogLocation!=null){
				try {
					doglocationIndex.testUpdateIndex(dogLocation);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				try {
					sleep(sleeptime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
