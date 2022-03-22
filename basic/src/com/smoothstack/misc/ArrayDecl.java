package com.smoothstack.misc;

import java.lang.reflect.Array;

class A { }

public class ArrayDecl {
    public static void main(String[] args) {
        new ArrayDecl();
    }
    public ArrayDecl() {
        // uninitialized arrays
        int[] arrayRefVar;
        float arrayRefVar1[];
        String[][] arrayRefVar2D;
        int[][][][][][][][][][][][][][][][][] crazyArr; // n dim array

        short[][][] arrayRefVar3D = new short[2][2][2];
        arrayRefVar3D[0][1][1] = 4; // watch for out of bounds
        System.out.println(arrayRefVar3D.length);
        assert arrayRefVar3D.length == 2;

        arrayRefVar2D = new String[][] {   // 2D array init
                {"abc", "bcd"}, {"def"}
        };

        arrayRefVar = new int[] {1, 2, 3};

        A[] aArray = new A[10];
        aArray[1] = new A();
        assert aArray[0] == null;
    }
}
