package com.flux.elevator;

import com.flux.doublelinked.list.DoubleLinkedList;

public class UserCreator implements Runnable {

	private final DoubleLinkedList<User> usersToAdd;
	private final Model targetModel;
	private final int interTime;

	public UserCreator(DoubleLinkedList<User> usersToAdd, Model targetModel, int interTime) {
		this.usersToAdd = usersToAdd;
		this.targetModel = targetModel;
		this.interTime = interTime;
	}

	@Override
	public void run() {

		while (usersToAdd.getFirst() != null) {
			addUserToModel(usersToAdd.retrieveFirst());
			try {
				Thread.sleep(interTime * 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void addUserToModel(User user) {
		targetModel.addNewWaitingUser(user);
	}

}
