package com.smoothstack.oop;

abstract class AbstractSuggest { // have to make class abstract
    abstract void pureFunc(); // like a pure virtual function in C++
}
public class PureAbstractSuggest extends AbstractSuggest  {
    @Override // if specified will check for ambiguities in signature
    void pureFunc() {}
}
