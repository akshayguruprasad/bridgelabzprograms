package com.bridgelabz.desiginpattern.behavioural.observer;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 13-Jun-2018
 */

public interface Topic {

    void registerObserver(Observer observer);

    void deRegisterObserver(Observer observer);

    void notifyAllObservers();

    void postAnswer(String answer);

    String notifySelectedObservers(Observer observer);

    String getName();

}
