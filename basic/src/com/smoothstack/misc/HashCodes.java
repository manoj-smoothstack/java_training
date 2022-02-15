package com.smoothstack.misc;

public class HashCodes {
    public HashCodes() {
        String s1 = "Hello";
        String s2 = "Hello";
        assert(s1 == s2); // references pointing to same constant space
        assert (s1.equals(s2)); // compare content
        assert(s1.hashCode() == s2.hashCode()); // compares content efficiently

        String s3 = s1;
        assert(s1.hashCode() == s3.hashCode());

        String s4 = new String("Hello"); // on the heap
        String s5 = new String("Hello");
        assert(s4 != s5); // now we are comparing references
    }
}
