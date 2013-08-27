package com.flux.elevator;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.flux.doublelinked.list.DoubleLinkedList;

public class ElevatorController extends Thread {
	private static final int DOORS_CLOSE_TIME = 20;

	private Elevator elevator;
	private DoubleLinkedList<ElevatorAction> actionQueue;
	private ExecutorService executorService;
	private Future<Boolean> timerTask;

	public ElevatorController(int floorCount) {
		this.elevator = new Elevator(floorCount);
		this.actionQueue = new DoubleLinkedList<ElevatorAction>();
		this.executorService = Executors.newFixedThreadPool(2);
	}

	// for testing purposes only
	protected ElevatorController(Elevator elevator) {
		this.elevator = elevator;
	}

	@Override
	public void run() {
		while (!actionQueue.isEmpty()) {
			executeAction(actionQueue.getFirst());
		}
	}

	protected void openDoors() {
		// TODO Auto-generated method stub

	}

	// visibility was changed for testing purposes
	protected void closeDoors() {
		timerTask = executorService.submit(new TimerCallable(DOORS_CLOSE_TIME));
		Boolean result = false;
		try {
			result = timerTask.get();
		} catch (InterruptedException e) {
			System.out.println("thread was interrupt");
			e.printStackTrace();
		} catch (ExecutionException e) {
			System.out.println("execution exception");
			e.printStackTrace();
		} catch (CancellationException ex) {
			System.out.println("timer task was interrupted");
		}

		if (!result) {
			System.out.println("thread was interrupted");
			openDoors();
		}

	}

	private void executeAction(ElevatorAction first) {
		// TODO Auto-generated method stub

	}

	public void callElevator(int floor) {
		if (elevator.getCurrentFloor() == floor && isDoorsClosing()) {
			interruptClosing();
			// TODO should check if doors are closing, and if yes, interrupt it
		}
	}

	private boolean isDoorsClosing() {
		return !elevator.isDoorsOpen() || !elevator.isPeopleMove();
	}

	protected void interruptClosing() {
		System.out.println("interrupt started");
		timerTask.cancel(true);
	}

	private boolean checkForInterruption() {
		return false;
	}
}
