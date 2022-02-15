package com.smoothstack.oop;

interface Life {
    String breathes();
}
interface Animal extends Life {}
interface Mammal extends Animal{}
abstract class Human implements Mammal {}
class Kid extends Human {
    public String breathes() { return "air"; }
    public int shouts() { return 2;}
}
class Person extends Human {
    @Override
    public String breathes() {
        return null;
    }
    public int cries() { return 4; }
}

public class InterfaceExample {
    public InterfaceExample() {
        Life kid = new Kid();
        assert kid.breathes() == "air";
        Kid anotherKid = (Kid)kid; // have to cast
        assert anotherKid.shouts() == 2;
        try {
            Person person = (Person) kid;  // works, but is this safe
            person.cries();
        } catch (ClassCastException cce) {
            System.out.println(cce.getMessage());
            assert true;
        }
    }
}