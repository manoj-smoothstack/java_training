package com.smoothstack.misc;

public class BogusTriplePlus {
    public BogusTriplePlus() {
        int x = 2, y = 3;
        if (x+++y < 6) {
            assert(x == 3 && y == 3);
            return;
        }
        assert false;
    }
}
