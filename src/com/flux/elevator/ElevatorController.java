package com.flux.elevator;

import com.flux.doublelinked.list.DoubleLinkedList;

public class ElevatorController extends Thread {
	private Elevator elevator;
	private DoubleLinkedList<ElevatorAction> actionQueue;

	public ElevatorController(int floorCount) {
		this.elevator = new Elevator(floorCount);
		this.actionQueue = new DoubleLinkedList<ElevatorAction>();
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

	private void openDoors() {
		// TODO Auto-generated method stub

	}

	private void executeAction(ElevatorAction first) {
		// TODO Auto-generated method stub

	}

	public void callElevator(int floor) {
		// TODO Auto-generated method stub

	}

	private boolean checkForInterruption(){
		return false;
	}
}
