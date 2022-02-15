package com.smoothstack.basic;

import com.smoothstack.access.Access;
import com.smoothstack.misc.*;
import com.smoothstack.oop.*;
import com.smoothstack.oop.MultipleInheritance;

public class BasicMain {
    public static void main(String[] args) {
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
    }
}
