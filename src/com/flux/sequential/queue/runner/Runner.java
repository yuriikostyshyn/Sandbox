package com.flux.sequential.queue.runner;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import javax.naming.InsufficientResourcesException;

import com.flux.sequential.queue.QueueManager;

public class Runner {

	private static final int queueCount = 5;

	/**
	 * @param args
	 * @throws InsufficientResourcesException 
	 */
	public static void main(String[] args) throws InsufficientResourcesException {
		QueueManager queueManager = new QueueManager(queueCount, 30);
		queueManager.printQueueInformation();
		for(int i=1; i<=3; i++){
			for(int j = 0; j<queueCount; j++){
				queueManager.putElement("Element "+ i*j, j);
				queueManager.printQueueInformation();
			}
		}
		
		for(int i=1; i<=5; i++){
				queueManager.putElement("Element "+ i*3, 3);
				queueManager.printQueueInformation();

		}
	}

}
