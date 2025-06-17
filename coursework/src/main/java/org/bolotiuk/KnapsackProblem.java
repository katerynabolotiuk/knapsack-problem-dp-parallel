package org.bolotiuk;

import java.util.List;

public class KnapsackProblem {
    private final List<Item> items;
    private final int capacity;

    public KnapsackProblem(List<Item> items, int capacity) {
        this.items = items;
        this.capacity = capacity;
    }

    public List<Item> getItems() {
        return items;
    }

    public int getCapacity() {
        return capacity;
    }
}
