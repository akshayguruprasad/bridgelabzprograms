package com.bridgelabz.desiginpattern.behavioural.observer;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 13-Jun-2018
 */

public class Invegilator implements Observer {
    Topic topic;

    /*
     * (non-Javadoc)
     * 
     * @see com.bridgelabz.desiginpattern.behavioural.observer.Observer#getUpdate()
     */
    @Override
    public String getUpdate() {

	String message = topic.notifySelectedObservers(this);

	System.out.println("latest feed  from "+topic.getName()+"	"+ message);

	return message;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.bridgelabz.desiginpattern.behavioural.observer.Observer#setTopic(com.
     * bridgelabz.desiginpattern.behavioural.observer.Topic)
     */
    @Override
    public void setTopic(Topic topic) {
	this.topic = topic;
    }

}
