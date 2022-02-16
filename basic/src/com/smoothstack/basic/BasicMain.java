package com.smoothstack.basic;

import com.smoothstack.access.Access;
import com.smoothstack.collections.ComparableDemo;
import com.smoothstack.collections.ComparatorDemo;
import com.smoothstack.collections.IteratorExample;
import com.smoothstack.collections.VectorDemo;
import com.smoothstack.generics.GenericsMethod;
import com.smoothstack.io.BufferedIO;
import com.smoothstack.io.CreateFileExample;
import com.smoothstack.io.SerDser;
import com.smoothstack.io.TempFileExample;
import com.smoothstack.misc.*;
import com.smoothstack.oop.*;
import com.smoothstack.system.ReflectionsRunner;
import com.smoothstack.system.RunnerFactory;
import com.smoothstack.threads.ThreadDemo;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class BasicMain {
    public static void main(String[] args) throws IOException, InterruptedException, InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException {
        ReflectionsRunner reflectionsRunner = new ReflectionsRunner();
        Class[] classes = reflectionsRunner.getClasses("com.smoothstack");
        new RunnerFactory(classes, BasicMain.class);
    }
}