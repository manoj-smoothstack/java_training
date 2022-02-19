package com.smoothstack.system;

import com.smoothstack.misc.StaticBlocks;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class RunnerFactory {
    public RunnerFactory(Class[] list, Class exclude) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        String classToExclude = exclude.getName();
        int cntr = 0;
        int tcntr = 0;
        for (Class c : list) {
            tcntr++;
            String classToRun = c.getName();
            if (!classToRun.equals(classToExclude) && !classToRun.contains("com.smoothstack.system")) {
                //System.out.println("Run: " + c.getName() + ", exclude: " + exclude.getName());
                System.out.println("********************* Running class: " + classToRun + ". ");
                if (run(c))
                    ++cntr;
                System.out.println("********************* Done class: " + classToRun + ".\n");
            }
        }
        System.out.println("Ran " + cntr + " tests. Total: " + tcntr++ + " tests.");
    }

    public class MyRunner implements Runnable {
        Class myClass;
        public MyRunner(Class myClass) {
            this.myClass = myClass;
        }
        public void run() {
            Constructor<?> ctr = null;
            try {
                ctr = myClass.getConstructor();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            try {
                ctr.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean run(Class c) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        String cname = c.getName();
        Class<?> myClass = Class.forName(cname);
        if (isSkippable(myClass))
            return false;
        new Thread(new MyRunner(myClass)).start();
        return true;
    }

    public boolean isSkippable(Class c) {
        Annotation[] cans = c.getDeclaredAnnotations();
        for (Annotation a: cans) {
            if (a.toString().contains("Deprecated"))
                return true;
            if (a.toString().contains("SkipMe"))
                return true;
        }
        Method[] methods = c.getDeclaredMethods();
        for (Method method : methods) {
            Annotation[] anos = method.getDeclaredAnnotations();
            for (Annotation a : anos)
                if (a.toString().contains("Deprecated"))
                    return true;
        }
        return false;
    }
}
