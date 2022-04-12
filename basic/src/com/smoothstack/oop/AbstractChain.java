package com.smoothstack.oop;

class A { }

class B extends A { }

abstract class C extends B { }

class D extends C { }

class AbstractChain {
	public static void main(String[] args) {
		D d = new D();
    //C c = new C(); // cannot be instantiated
		B b = new B();
	}
}


