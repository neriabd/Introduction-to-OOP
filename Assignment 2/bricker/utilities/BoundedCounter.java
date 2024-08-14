package bricker.utilities;

import danogl.util.Counter;

/**
 * This class represents a bounded counter that has a maximum value.
 * The counter will not increment or decrement if the value is at the maximum value or 0 accordingly.
 * @see Counter
 * @author Neriya Ben David
 */
public class BoundedCounter extends Counter {

    // Fields
    private final int maxValue;

    /**
     * The constructor of the BoundedCounter object.
     * @param initValue the initial value of the counter.
     * @param maxValue the maximum value of the counter.
     */
    public BoundedCounter(int initValue, int maxValue) {
        super(initValue);
        this.maxValue = maxValue;
    }

    /**
     * This method increments the counter by 1 if the value is less than the maximum value.
     */
    @Override
    public void increment() {
        if (value() < maxValue) {
            super.increment();
        }
    }

    /**
     * This method decrements the counter by 1 if the value is greater than 0.
     */
    @Override
    public void decrement() {
        if (value() > 0) {
            super.decrement();
        }
    }
}
