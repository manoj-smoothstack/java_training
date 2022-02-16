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
import com.smoothstack.system.RunnerFactory;
import com.smoothstack.threads.ThreadDemo;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class BasicMain {
    public static void main(String[] args) throws IOException, InterruptedException, InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException {
        Class[] classes = new Class[] {
                StaticBlocks.class,
                ArrayDecl.class,
                ForEach.class,
                Access.class,
                ExceptionTypes.class,
                MyDerived.class,
                PureAbstractSuggest.class,
                SuperDemo.class,
                BogusTriplePlus.class,
                ScanningStdin.class,
                DefaultMethods.class,
                HashCodes.class,
                FinalExample.class,
                MethodOverload.class,
                CastExample.class,
                RandomExample.class,
                InterfaceExample.class,
                MultipleInheritance.class,
                CreateFileExample.class,
                TempFileExample.class,
                BufferedIO.class,
                SerDser.class,
                VectorDemo.class,
                IteratorExample.class,
                ComparableDemo.class,
                ComparatorDemo.class,
                ThreadDemo.class,
                GenericsMethod.class
        };
        new RunnerFactory(classes);
    }
}