package com.example.skidrow;

import java.util.LinkedList;

public class QueueEvents {
	private LinkedList linked;
	private static QueueEvents  instance = null;
	protected QueueEvents () {
		linked=new LinkedList();
	}
	/**
	 * Singleton implementation of QueueEvent
	 * Returns an instance of the class
	 * @return instance of the class
	 */
	public static QueueEvents  getInstance() {
		if(instance == null) {
			instance = new QueueEvents ();
		}
		return instance;
	}
	/*
	 * Returns the first element of the QueueEvent and deletes it from the linked list
	 * @returns first element of the QueueEvent
	 */
	public Event pop(){
		Event nextE=(Event)linked.pop();
		if(nextE!=null){
			return nextE;
		}
		return null;
	}
	/*
	 * Returns the first element of the QueueEvent
	 * @returns first element of the QueueEvent
	 */
	public Event peek(){
		Event nextE=(Event)linked.peek();
		if(nextE!=null){
			return nextE;
		}
		return null;
	}
	/*
	 * Returns the size of the queue
	 * @return the size of the queue
	 */
	public int size(){
		return linked.size();
	}
	/*
	 * Adds an element to the list
	 * @param the event to be added
	 */
	public void add(Event e){
		linked.add(e);
	}
	

}
