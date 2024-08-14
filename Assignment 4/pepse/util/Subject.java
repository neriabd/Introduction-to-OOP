package pepse.util;

import java.util.HashSet;
import java.util.Set;

/**
 * The Subject class for the observer design pattern.
 * @author Neriya Ben David
 */
public class Subject {

    // Fields
    private final Set<Observer> observers = new HashSet<>();

    /**
     * Adds an observer to the list of observers.
     * @param observer the observer to add
     */
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * Removes an observer from the list of observers.
     * @param observer the observer to remove
     */
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    /**
     * Notifies all observers in the list of observers and perform the action.
     */
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.performAction();
        }
    }
}
