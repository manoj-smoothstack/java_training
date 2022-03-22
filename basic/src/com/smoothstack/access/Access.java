package com.smoothstack.access;

import com.smoothstack.anotherpkg.AnotherPkgClass;

class Defaulted {
    void accessible() {} // default modifier
}

public class Access {
    public Access() {
        new Defaulted().accessible(); // can access default modifier
        new AnotherClass().inaccessible();
        //new AnotherPkgClass().inaccessible();
        new AnotherPkgClass().accessible(); // this is accessible
    }
}
