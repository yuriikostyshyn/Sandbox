package com.flux.elevator;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.verify;
import static org.mockito.Matchers.eq;

public class ElevatorControllerTest {
	private ElevatorController underTest;

	@Before
	public void setUp() {
		underTest = new ElevatorController(3);
	}

	@Test
	public void shouldCallOpenDoorsIfClosingWasInterrupted() throws InterruptedException {
		Runnable closeRunnable = new Runnable() {
			@Override
			public void run() {
				System.out.println("closing started");
				underTest.closeDoors();
					
			}
		};
		Thread thread = new Thread(closeRunnable);
		thread.start();
		Thread.sleep(10000);
		
		Runnable interruptRunnable = new Runnable() {
			@Override
			public void run() {
				System.out.println("interruption started");
				underTest.interruptClosing();
					
			}
		};
		Thread interruptThread = new Thread(interruptRunnable);
		interruptThread.setPriority(Thread.MAX_PRIORITY);
		interruptThread.start();
		verify(eq(underTest)).openDoors();
	}
}
