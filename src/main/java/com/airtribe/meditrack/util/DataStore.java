package com.airtribe.meditrack.util;


import java.util.*;
import java.util.stream.Stream;

public class DataStore<T> {

    private final List<T> dataList = new ArrayList<>();

    // Add item
    public void add(T item) {
        dataList.add(item);
    }

    // Get all items
    public List<T> getAll() {
        return new ArrayList<>(dataList); // return copy (good practice)
    }

    // Remove item
    public void remove(T item) {
        dataList.remove(item);
    }

//    public void removeAll (T item){dataList.removeAll((Collection<?>) item);}

    // Find first matching item (using Predicate)
    public Optional<T> find(java.util.function.Predicate<T> condition) {
        return dataList.stream()
                .filter(condition)
                .findFirst();
    }

    public Stream<T> stream() {
        return dataList.stream();
    }


}