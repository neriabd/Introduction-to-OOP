package pepse.util;

import java.util.HashSet;
import java.util.Set;

/**
 * The Subject class for the observer design pattern.
 * @author Neriya Ben David
 */
public class Subject {

    // Fields
    private final Set<Runnable> observers = new HashSet<>();

    /**
     * Adds an observer to the list of observers.
     * @param observer the observer to add
     */
    public void addObserver(Runnable observer) {
        observers.add(observer);
    }

    /**
     * Removes an observer from the list of observers.
     * @param observer the observer to remove
     */
    public void removeObserver(Runnable observer) {
        observers.remove(observer);
    }

    /**
     * Notifies all observers in the list of observers and perform the action.
     */
    public void notifyObservers() {
        for (Runnable observer : observers) {
            observer.run();
        }
    }
}
