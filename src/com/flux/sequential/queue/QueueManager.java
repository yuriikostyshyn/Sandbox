package com.flux.sequential.queue;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

import javax.naming.InsufficientResourcesException;

public class QueueManager {
	private Queue[] queueList;
	private String[] values;
	private int oldTop[];

	public QueueManager(int queueCount, int totalSize) {
		init(queueCount, totalSize);
	}

	public void putElement(String newElement, int queueId) throws InsufficientResourcesException {
		Queue queue = queueList[queueId];
		try {
			queue.put(newElement);
		} catch (IndexOutOfBoundsException ex) {
			reallocateQueues(queueId);
			printQueueInformation();
			queue.put(newElement);
			printQueueInformation();
		}
	}

	public void printQueueInformation() {
		for (int i = 0; i < queueList.length; i++) {
			System.out.print("Queue number " + i + ": ");
			System.out.println(queueList[i].getBase() + "   " + queueList[i].getTop());
		}
	}

	private void init(int queueCount, int totalSize) {
		this.queueList = new Queue[queueCount];
		this.values = new String[totalSize];
		initQueues(queueCount, totalSize);
		initOldTopArray();
	}

	private void initQueues(int queueCount, int totalSize) {
		for (int i = 0; i < queueCount; i++) {
			int size = totalSize / queueCount;
			int base = size * i;
			int top = base;
			queueList[i] = new Queue(values, size, base, top);
		}
	}

	private void reallocateQueues(int queueId) throws InsufficientResourcesException {
		int sum = values.length;
		int inc = 0;
		int offset[] = new int[queueList.length];

		for (int j = 0; j < queueList.length; j++) {
			sum = sum - (queueList[j].getTop() - queueList[j].getBase());
			if (queueList[j].getTop() > oldTop[j]) {
				offset[j] = queueList[j].getTop() - oldTop[j];
				inc = inc + offset[j];
			} else {
				offset[j] = 0;
			}
		}

		if (sum < 0) {
			throw new InsufficientResourcesException();
		}

		BigDecimal alpha = new BigDecimal(0.1 * sum / queueList.length, new MathContext(5));
		BigDecimal beta = new BigDecimal(0.9 * sum / inc, new MathContext(5));

		int newBase[] = new int[queueList.length];

		newBase[0] = queueList[0].getBase();
		BigDecimal sigma = new BigDecimal(0, new MathContext(5));

		for (int j = 1; j < queueList.length; j++) {
			BigDecimal tau = sigma.add(alpha, new MathContext(5));
			BigDecimal addition = beta.multiply(new BigDecimal(offset[j - 1]), new MathContext(5));
			tau = tau.add(addition);
			newBase[j] = newBase[j - 1] + queueList[j - 1].getTop() - queueList[j - 1].getBase() + tau.intValue() - sigma.intValue();
			sigma = tau;
		}

		queueList[queueId].setTop(queueList[queueId].getTop() - 1);
		moveQueues(newBase);
		queueList[queueId].setTop(queueList[queueId].getTop() + 1);
		initOldTopArray();
	}

	private void initOldTopArray() {
		if (oldTop == null) {
			oldTop = new int[queueList.length];

			for (int j = 0; j < queueList.length; j++) {
				oldTop[j] = queueList[j].getTop();
			}
		}
	}

	private void moveQueues(int[] newBase) {
		for (int j = 1; j < queueList.length; j++) {
			int base = queueList[j].getBase();
			if (newBase[j] < base) {
				int delta = base - newBase[j];

				for (int i = 0; i < queueList[j].getTop() - queueList[j].getBase(); i++) {
					values[base + i - delta] = values[base + i];
				}

				queueList[j].setBase(newBase[j]);
				int top = queueList[j].getTop();
				queueList[j].setTop(top - delta);
			}
		}

		for (int j = queueList.length - 1; j >= 0; j--) {
			int base = queueList[j].getBase();
			if (base < newBase[j]) {
				int top = queueList[j].getTop();
				int delta = newBase[j] - base;

				for (int i = 0; i <queueList[j].getTop() - queueList[j].getBase(); i++) {
					values[top - i + delta] = values[top - i];
				}
				queueList[j].setBase(newBase[j]);
				queueList[j].setTop(top + delta);
			}
		}

		queueList[queueList.length - 1].setSize(values.length - newBase[queueList.length - 1] - 1);
		for (int j = 0; j < queueList.length - 1; j++) {
			queueList[j].setSize(newBase[j+1] - newBase[j]);
		}
	}
}
