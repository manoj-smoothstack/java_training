package com.smoothstack.basic;

import com.smoothstack.access.Access;
import com.smoothstack.collections.ComparableDemo;
import com.smoothstack.collections.IteratorExample;
import com.smoothstack.collections.VectorDemo;
import com.smoothstack.io.BufferedIO;
import com.smoothstack.io.CreateFile;
import com.smoothstack.io.SerDser;
import com.smoothstack.io.TempFile;
import com.smoothstack.misc.*;
import com.smoothstack.oop.*;
import com.smoothstack.oop.MultipleInheritance;

import javax.management.MBeanServerDelegate;
import java.io.IOException;

public class BasicMain {
    public static void main(String[] args) throws IOException {
        StaticBlocks staticBlocks = new StaticBlocks();
        ArrayDecl arrayDecl = new ArrayDecl();
        ForEach forEach = new ForEach();
        Access access = new Access();
        ExceptionTypes exceptionTypes = new ExceptionTypes();
        MyDerived myDerived = new MyDerived();
        PureAbstractSuggest pureAbstractSuggest = new PureAbstractSuggest();
        SuperDemo superDemo = new SuperDemo();
        BogusTriplePlus bogusTriplePlus = new BogusTriplePlus();
        ScanningStdin scanningStdin = new ScanningStdin(); // actually dummy, it's a main()
        DefaultMethods defaultMethods = new DefaultMethods();
        HashCodes hashCodes = new HashCodes();
        FinalExample finalExample = new FinalExample();
        MethodOverload methodOverload = new MethodOverload();
        CastExample castExample = new CastExample();
        RandomExample randomExample = new RandomExample();
        InterfaceExample interfaceExample = new InterfaceExample();
        MultipleInheritance multipleInheritance1 = new MultipleInheritance();
        new CreateFile("test").create();
        TempFile tempFile = new TempFile("test", ".txt", "rwxrwxrwx");
        tempFile.create();
        tempFile.testFile();
        BufferedIO bufferedIO = new BufferedIO();
        SerDser serDemo = new SerDser();
        VectorDemo vectorDemo = new VectorDemo();

        IteratorExample iteratorExample = new IteratorExample();
        ComparableDemo comparableDemo = new ComparableDemo();
    }
}
