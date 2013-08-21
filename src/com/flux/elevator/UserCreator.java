package com.flux.elevator;

import com.flux.doublelinked.list.DoubleLinkedList;

public class UserCreator {

	private final DoubleLinkedList<User> usersToAdd;
	private final Model targetModel;

	public UserCreator(DoubleLinkedList<User> usersToAdd, Model targetModel) {
		this.usersToAdd = usersToAdd;
		this.targetModel = targetModel;
	}
}
