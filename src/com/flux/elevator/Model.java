package com.flux.elevator;

import com.flux.doublelinked.list.DoubleLinkedList;

public class Model {
	private DoubleLinkedList<User> waitingUsers;
	private ElevatorController elevatorController;
	private Thread elevatorThread;

	public Model(){
		elevatorThread = new Thread();
	}
	
	public void addNewWaitingUser(User user) {
		waitingUsers.addLast(user);
	}

	public void callElevator(int floor) {
		elevatorController.callElevator(floor);
	}
}
