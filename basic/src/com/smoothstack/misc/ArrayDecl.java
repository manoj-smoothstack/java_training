package com.smoothstack.misc;

public class ArrayDecl {
    public ArrayDecl() {
        // uninitialized arrays
        int[] arrayRefVar;
        float arrayRefVar1[];
        String[][] arrayRefVar2D;
        int[][][][][][][][][][][][][][][][][] crazyArr; // n dim array

        short[][][] arrayRefVar3D = new short[2][2][2];
        arrayRefVar3D[0][1][1] = 4; // watch for out of bounds

        arrayRefVar2D = new String[][] {   // 2D array init
                {"abc", "bcd"}, {"def"}
        };

        arrayRefVar = new int[] {1, 2, 3};
    }
}
