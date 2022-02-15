package com.smoothstack.misc;

public class ExceptionTypes {
    public ExceptionTypes() {
        try {
            checkedException(); // checked exception
            assert false;
        } catch (MyPrivateException e) {
            // never hide in real life! shown for demonstration purposes only
        }
        try {
            uncheckedException();
            assert false;
        } catch (RuntimeException e) {
            // never hide in real life! shown for demonstration purposes only
        }
    }

    class MyPrivateException extends Exception {}

    private void checkedException() throws MyPrivateException {
        throw new MyPrivateException();
    }

    private void uncheckedException() {
        int x = 1/0;
    }
}
