package com.smoothstack.system;

import com.smoothstack.misc.StaticBlocks;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class RunnerFactory {
    public RunnerFactory(Class[] list) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        for (Class c: list) {
            run(c);
        }
    };

    public void run(Class c) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        String cname = c.getName();
        Class<?> myClass = Class.forName(cname);
        Constructor<?> ctr = myClass.getConstructor();
        ctr.newInstance();
    }
}
