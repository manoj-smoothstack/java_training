package com.smoothstack.basic;

import com.smoothstack.access.Access;
import com.smoothstack.misc.ArrayDecl;
import com.smoothstack.misc.ExceptionTypes;
import com.smoothstack.misc.ForEach;
import com.smoothstack.misc.StaticBlocks;
import com.smoothstack.oop.MyDerived;
import com.smoothstack.oop.PureAbstractSuggest;
import com.smoothstack.oop.SuperDemo;

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
    }
}
