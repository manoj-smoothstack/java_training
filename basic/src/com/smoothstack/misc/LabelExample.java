package com.smoothstack.misc;

public class LabelExample {
    public static void main(String[] args) {
        new LabelExample();
    }

    public LabelExample() {
        assert breakWithoutLabel() == 11;
        assert breakWithLabel() == 5;
        assert continueWithLabel() == 0;
    }

    public int breakWithoutLabel() {
        int counter = 0;
            for (int i = 0; i <= 10; i++) {
                for (int j = 0; j <= 10; j++) {
                    if (i == 5)
                        break;
                }
                counter++;
            }
        return counter;
    }

    public int breakWithLabel() {
        int counter = 0;
        start: {
            for (int i = 0; i <= 10; i++) {
                for (int j = 0; j <= 10; j++) {
                    if (i == 5)
                        break start;
                }
                counter++;
            }
        }
        return counter;
    }

    public int continueWithLabel() {
        int counter = 0;
        start: for (int i = 0; i < 5; i++) {
            System.out.println();
            for (int j = 0; j < 10; j++) {
                System.out.print("#");
                if (j >= i)
                    continue start;
            }
            counter++; // never gets executed
        }
        return counter;
    }
}
