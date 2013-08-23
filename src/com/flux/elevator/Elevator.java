package com.flux.elevator;

import com.flux.doublelinked.list.DoubleLinkedList;

public class Elevator {
	private static final int BASE_FLOOR = 2;

	private final boolean[] callCabine;
	private final boolean[] callDown;
	private final boolean[] callUp;

	private boolean peopleMove = false; // doors open, people enter or leave elevator
	private boolean elevatorIsNotUsed = false;
	private boolean doorsOpen = false; // And elevator is not used
	private boolean closingInterrupted = false;

	private ElevatorState currentState = ElevatorState.NEUTRAL;
	private int currentFloor = BASE_FLOOR;
	private DoubleLinkedList<ElevatorAction> actionQueue;

	public Elevator(int floorCount) {
		this.callCabine = new boolean[floorCount];
		this.callDown = new boolean[floorCount];
		this.callUp = new boolean[floorCount];
	}

	public boolean isPeopleMove() {
		return peopleMove;
	}

	public void setPeopleMove(boolean peopleMove) {
		this.peopleMove = peopleMove;
	}

	public boolean isElevatorIsNotUsed() {
		return elevatorIsNotUsed;
	}

	public void setElevatorIsNotUsed(boolean elevatorIsNotUsed) {
		this.elevatorIsNotUsed = elevatorIsNotUsed;
	}

	public boolean isDoorsOpen() {
		return doorsOpen;
	}

	public void setDoorsOpen(boolean doorsOpen) {
		this.doorsOpen = doorsOpen;
	}

	public int getCurrentFloor() {
		return currentFloor;
	}

	public void setCurrentFloor(int currentFloor) {
		this.currentFloor = currentFloor;
	}

}
