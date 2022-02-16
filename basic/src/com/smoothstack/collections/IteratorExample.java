package com.smoothstack.collections;

import java.util.Iterator;

class MyIterator<Type> implements Iterable<Type> {

    private Type[] arrayList;
    private int currentSize;

    public MyIterator(Type[] newArray) {
        this.arrayList = newArray;
        this.currentSize = arrayList.length;
    }

    @Override
    public Iterator<Type> iterator() {
        Iterator<Type> it = new Iterator<Type>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < currentSize && arrayList[currentIndex] != null;
            }

            @Override
            public Type next() {
                return arrayList[currentIndex++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }
}

public class IteratorExample {

    public IteratorExample() {
        System.out.println("Iterator Demo follows");
        // create an array of type Integer
        Integer[] numbers = new Integer[]{1, 2, 3, 4, 5};

        // create your list and hold the values.
        MyIterator<Integer> mylist = new MyIterator<Integer>(numbers);

        // Since our class SOList is an instance of Iterable, then we can use it on a foreach loop
        for (Integer num : mylist) {
            System.out.print(num);
        }

        // creating an array of Strings
        String[] languages = new String[]{"C", "C++", "Java", "Python", "Scala"};

        // create your list and hold the values using the same list implementation.
        MyIterator<String> languagesList = new MyIterator<String>(languages);

        System.out.println("");
        // Since our class SOList is an instance of Iterable, then we can use it on a foreach loop
        for (String lang : languagesList) {
            System.out.println(lang);
        }
    }
}