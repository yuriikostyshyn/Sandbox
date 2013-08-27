package com.flux.elevator;

import java.util.concurrent.Callable;

public class TimerCallable implements Callable<Boolean> {
	private int delay;

	public TimerCallable(int delay) {
		this.delay = delay;
	}

	@Override
	public Boolean call() throws Exception {
		try {
			Thread.sleep(delay * 1000);
		} catch (InterruptedException e) {
			return false;
		}
		return true;
	}
}
