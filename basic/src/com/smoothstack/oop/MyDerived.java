package com.smoothstack.oop;

abstract class DummyAbstract {} // do not need methods, dummy abstract

public class MyDerived extends DummyAbstract {} // implemented

abstract class RevertToAbstract extends MyDerived {} // abstract again