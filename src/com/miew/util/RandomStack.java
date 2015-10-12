package com.miew.util;

public class RandomStack<T extends StackElement> extends Stack<T> {

    /**
     * Construct an empty randomized queue
     */
    public RandomStack() {
        super();
    }

    /**
     * Add the item
     * 
     * @param item
     */
    public Stack<T> stack(T item) {
        if (item == null) {
            throw new java.lang.NullPointerException();
        }
        int index = (int) Math.round(Math.random() * size());
        if (index != size()) {
            list.add(index, item);
        } else
            list.add(item);

        return this;
    }
}
