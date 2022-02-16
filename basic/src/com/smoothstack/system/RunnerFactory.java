package com.smoothstack.system;

import com.smoothstack.misc.StaticBlocks;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class RunnerFactory {
    public RunnerFactory(Class[] list, Class exclude) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        String classToExclude = exclude.getName();
        for (Class c: list) {
            //System.out.println("Run: " + c.getName() + ", exclude: " + exclude.getName());
            String classToRun = c.getName();
            if (!classToRun.equals(classToExclude) && !classToRun.contains("com.smoothstack.system"))
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
