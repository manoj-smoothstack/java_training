package com.smoothstack.access;

class Defaulted {
    void accessible() {} // default modifier
}
public class Access {
    public Access() {
        new Defaulted().accessible(); // can access default modifier
    }
}
