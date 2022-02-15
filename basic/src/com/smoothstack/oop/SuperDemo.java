package com.smoothstack.oop;


class B {
    public static StringBuffer sb = new StringBuffer();

    public B() {
        sb.append("B");
    }

    public void construct() {
        sb.append("BC");
    }
}
class A extends  B {
    public A() {
        super();
        sb.append("A");
    }
    public void construct() {
        super.construct();
        sb.append("AC");
    }
}
public class SuperDemo {
    public SuperDemo() {
        new A().construct();
        System.out.println(A.sb);
        assert(A.sb.toString() == "BABCAC");
    }
}
