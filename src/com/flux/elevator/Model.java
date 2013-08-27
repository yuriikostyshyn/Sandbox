package com.flux.elevator;

import com.flux.doublelinked.list.DoubleLinkedList;

public class Model extends Thread {
	private DoubleLinkedList<User> waitingUsers;
	private DoubleLinkedList<User> incomingUsers;
	private ElevatorController elevatorController;

	public Model() {
		while (!incomingUsers.isEmpty()) {
			User newUser = incomingUsers.retrieveFirst();
			waitingUsers.addLast(newUser);
			callElevator(newUser.getInFloor());
		}
	}

	@Override
	public void run() {

	}

	public void addNewWaitingUser(User user) {
		waitingUsers.addLast(user);
	}

	public void callElevator(int floor) {
		elevatorController.callElevator(floor);
	}
}
