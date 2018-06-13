package com.bridgelabz.designpattern.test;

import com.bridgelabz.desiginpattern.behavioural.observer.Invegilator;
import com.bridgelabz.desiginpattern.behavioural.observer.Observer;
import com.bridgelabz.desiginpattern.behavioural.observer.Student;
import com.bridgelabz.desiginpattern.behavioural.observer.Topic;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 13-Jun-2018
 */

public class BehaviouralObserverMain {

    /**
     * @param args
     * 
     */
    public static void main(String[] args) {
	//topic all set
	Topic topic=new Student("Ram");

	
	//creating the observers
	
	Observer ob1=new Invegilator();
	Observer ob2=new Invegilator();
	Observer ob3=new Invegilator();
	Observer ob4=new Invegilator();
	Observer ob5=new Invegilator();
	Observer ob6=new Invegilator();
	
	
	
	//setting the observers the topics
	ob1.setTopic(topic);
	ob2.setTopic(topic);
	ob3.setTopic(topic);
	ob4.setTopic(topic);
	ob5.setTopic(topic);
	ob6.setTopic(topic);
	
	
	
	
	//register all the observers in the topic list to get notified
	topic.registerObserver(ob1);
	topic.registerObserver(ob2);
	topic.registerObserver(ob3);
	topic.registerObserver(ob4);
	topic.registerObserver(ob5);
	topic.registerObserver(ob6);
	
	//make some comment on nationalism
	topic.postAnswer("Love afzal guru");

	
	
	//people will get notified

    }

}
