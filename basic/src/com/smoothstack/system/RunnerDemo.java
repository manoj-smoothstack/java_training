package com.smoothstack.system;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class RunnerDemo {
    public static void main(String args[]) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        // Get class name as string.
        String myClassName = String.class.getName();
        // Create class of type Base.
        Class<?> myClass = Class.forName(myClassName);
        // Create constructor call with argument types.
        Constructor<?> ctr = myClass.getConstructor(String.class);
        // Finally create object of type Base and pass data to constructor.
        String arg1 = "My User Data";
        Object object = ctr.newInstance(new Object[]{arg1});
        // Type-cast and access the data from class Base.
        String base = (String) object;
        System.out.println(base);
    }
}
