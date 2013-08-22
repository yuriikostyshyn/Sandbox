package com.flux.elevator;

import com.flux.doublelinked.list.DoubleLinkedList;

public class Model {
	private DoubleLinkedList<User> waitingUsers;
	private Elevator elevator;

	public void addNewWaitingUser(User user) {
		waitingUsers.addLast(user);
	}

	public void callElevator(int floor) {
		int currentFloor = elevator.getCurrentFloor();
		if (currentFloor == floor) {
			if (elevator.getActionQueue().getFirst().equals(ElevatorAction.PREPARE_TO_MOVE)) { //possible conflict between threads
				elevator.openDoors(true);
			}
		}
	}
}
