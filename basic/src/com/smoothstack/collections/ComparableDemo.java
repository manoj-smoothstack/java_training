package com.smoothstack.collections;

import java.util.*;

class Animal implements Comparable<Animal> {
    public String name;

    public Animal(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Animal animal) {
        return this.name.compareTo(animal.name);
    }

    public String toString() { return name; }
}

public class ComparableDemo {
    public ComparableDemo() {
        List<Animal> list = new ArrayList<Animal>() {
            {
                add(new Animal("zebra"));
                add(new Animal("fox"));
                add(new Animal("tiger"));
            }
        };
        Collections.sort(list);
        System.out.println(list);
    }
}
