package com.smoothstack.basic;

import com.smoothstack.system.ReflectionsRunner;
import com.smoothstack.system.RunnerFactory;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Use the VM option
 * -ea
 * to allow for assertions.
 */
public class BasicMain {
    public static void main(String[] args) throws IOException, InterruptedException, InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException {
        ReflectionsRunner reflectionsRunner = new ReflectionsRunner();
        Class[] classes = reflectionsRunner.getClasses("com.smoothstack");
        new RunnerFactory(classes, BasicMain.class);
    }
}