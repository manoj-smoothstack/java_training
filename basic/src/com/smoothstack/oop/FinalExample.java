package com.smoothstack.oop;

final class FinalClass {}
//class Optimistic extends FinalClass {} // cannot inherit from a final class

class FinalMethod {
    final void finalMethod() {}
    void nonFinalMethod() {}
}

class FinalExtender extends FinalMethod {
    //void finalMethod() {} // cannot override final method
    @Override
    void nonFinalMethod() {}
}

public class FinalExample {
    public FinalExample() {
        FinalClass finalClass = new FinalClass();
        FinalMethod finalMethod = new FinalMethod();
    }
}
