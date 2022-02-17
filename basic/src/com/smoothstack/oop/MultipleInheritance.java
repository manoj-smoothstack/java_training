package com.smoothstack.oop;

//class Musician { // cannot use classes
interface Musician {
    public String getInstrument();
}
//class Teacher {
interface Teacher {
    public String getSchool();
}
//class MusicTeacher extends Musician, Teacher { // class multiple inheritance not possible
class MusicTeacher implements Musician, Teacher {
    @Override
    public String getInstrument() {
        return null;
    }
    @Override
    public String getSchool() {
        return null;
    }
}

public class MultipleInheritance {
    public MultipleInheritance() {
        MusicTeacher musicTeacher = new MusicTeacher();
    }
}
