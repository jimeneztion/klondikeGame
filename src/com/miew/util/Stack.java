package com.miew.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Stack<T extends StackElement> implements Iterable<T> {

    protected List<T> list;

    /**
     * Construct an empty randomized queue
     */
    public Stack() {
        list = new ArrayList<T>();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
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
        list.add(item);
        return this;
    }

    /**
     * Add the item
     * 
     * @param item
     * @return
     */
    public Stack<T> stackAll(Stack<T> itemList) {
        if (itemList == null) {
            throw new java.lang.NullPointerException();
        }

        for (T item : itemList)
            stack(item);
        return this;
    }

    /**
     * delete and return a random item
     * 
     * @return
     */
    public synchronized T unstack() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }

        return (T) list.remove(size() - 1);
    }

    public synchronized Stack<T> unstackMultiUnstakables() {
        Stack<T> res_stack = new Stack<T>();
        while (sample().isMultiUnstakable())
            res_stack.stack(unstack());
        return res_stack;
    }

    /**
     * delete and return a random item
     * 
     * @return
     */
    public synchronized Stack<T> sampleMultiUnstakables() {
        Stack<T> res_stack = new Stack<T>();
        for (int i = list.size() - 1; i >= 0; i--) {
            T el = list.get(i);
            if (el.isMultiUnstakable())
                res_stack.stack(el);
            else
                break;
        }
        return res_stack;
    }

    /**
     * return (but do not delete) a random item
     * 
     * @return
     */
    public T sample() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        return (T) list.get(size() - 1);
    }

    private class StackIterator<E> implements Iterator<E> {
        public boolean hasNext() {
            return size() > 0;
        }

        @SuppressWarnings("unchecked")
        public E next() {
            if (isEmpty()) {
                throw new java.util.NoSuchElementException();
            }

            return (E) unstack();
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }

    /**
     * return an independent iterator over items in random order
     * 
     * @return
     */
    public Iterator<T> iterator() {
        return new StackIterator<T>();
    }
}
