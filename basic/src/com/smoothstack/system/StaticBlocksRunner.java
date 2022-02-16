package com.smoothstack.system;

import com.smoothstack.misc.StaticBlocks;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class StaticBlocksRunner {
    public static void main(String args[]) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        String myClassName = StaticBlocks.class.getName();
        Class<?> myClass = Class.forName(myClassName);
        Constructor<?> ctr = myClass.getConstructor();
        ctr.newInstance();
    }
}
