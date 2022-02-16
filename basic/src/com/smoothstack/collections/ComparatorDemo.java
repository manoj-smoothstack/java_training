package com.smoothstack.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Student {
    String name;
    public Student(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}

class SortByName implements Comparator<Student> {
    public int compare(Student a, Student b) {
        return a.name.compareTo(b.name);
    }
}

public class ComparatorDemo {
    public ComparatorDemo() {
        ArrayList<Student> ar = new ArrayList<Student>();
        ar.add(new Student("Bob"));
        ar.add(new Student("Jack"));
        ar.add(new Student("Rajesh"));
        ar.add(new Student("Diana"));

        System.out.println("Unsorted");

        for (int i = 0; i < ar.size(); i++)
            System.out.println(ar.get(i));

        for (int i = 0; i < ar.size(); i++)
            System.out.println(ar.get(i));

        Collections.sort(ar, new SortByName());

        System.out.println("\nSorted by name");

        for (int i = 0; i < ar.size(); i++)
            System.out.println(ar.get(i));
    }
}

