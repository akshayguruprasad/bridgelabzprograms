package com.bridgelabz.desiginpattern.behavioural.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 13-Jun-2018
 */

public class Student implements Topic {

    private List<Observer> registedObservers;
    String message;
    final Object MUTEX = new Object();
    boolean changesMade;
    String name;

    public Student(String name) {

	this.name = name;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.bridgelabz.desiginpattern.behavioural.observer.Topic#registerObserver(com
     * .bridgelabz.desiginpattern.behavioural.observer.Observer)
     */
    @Override
    public void registerObserver(Observer observer) {
	synchronized (MUTEX) {
	    if (registedObservers == null) {
		this.registedObservers = new ArrayList<>();// LEAVING EMPTY FOR FUTHER MULTIPLE SUB CLASSES
	    }
	    this.registedObservers.add(observer);
	    System.out.println("Observer registed successful");
	}

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.bridgelabz.desiginpattern.behavioural.observer.Topic#deRegisterObserver(
     * com.bridgelabz.desiginpattern.behavioural.observer.Observer)
     */
    @Override
    public void deRegisterObserver(Observer observer) {

	synchronized (MUTEX) {

	    if (this.registedObservers == null) {
		System.out.println("Empty registers found");
		return;
	    }
	    this.registedObservers.remove(observer);
	    System.out.println("Observer deregistered");
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.bridgelabz.desiginpattern.behavioural.observer.Topic#notifyAllObservers()
     */
    @Override
    public void notifyAllObservers() {

	synchronized (MUTEX) {

	    if (!changesMade) {
		return;
	    }
	    for (Observer observer : registedObservers) {
		// this.notifySelectedObservers(observer);

		observer.getUpdate();

	    }
	    this.changesMade = false;

	}

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.bridgelabz.desiginpattern.behavioural.observer.Topic#postAnswer(java.lang
     * .String)
     */
    @Override
    public void postAnswer(String answer) {
	synchronized (MUTEX) {

	    this.message = answer;
	    this.changesMade = true;
	    this.notifyAllObservers();
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bridgelabz.desiginpattern.behavioural.observer.Topic#
     * notifySelectedObservers(com.bridgelabz.desiginpattern.behavioural.observer.
     * Observer)
     */
    @Override
    public String notifySelectedObservers(Observer observer) {

	synchronized (MUTEX) {

	    if (this.registedObservers.contains(observer)) {

		if (changesMade) {
		    return this.message;
		}
	    }
	    return null;
	}

    }

    /* (non-Javadoc)
     * @see com.bridgelabz.desiginpattern.behavioural.observer.Topic#getName()
     */
    @Override
    public String getName() {
	return this.name;
	
    }

}
