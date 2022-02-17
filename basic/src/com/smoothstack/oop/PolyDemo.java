package com.smoothstack.oop;

class Fruit {
    public String hello() { return "Fruit";}
}

class TropicalFruit extends Fruit {
    @Override
    public String hello() { return "Tropical Fruit"; }
}

class Mango extends TropicalFruit {
    @Override
    public String hello() { return "Mango"; }
}

public class PolyDemo {
    public PolyDemo() {
        Mango mango = new Mango();
        Fruit fruit = new TropicalFruit();
        TropicalFruit tropicalFruit = new Mango();

        assert(mango.hello() == "Mango");
        assert(fruit.hello() == "Tropical Fruit");
        assert(tropicalFruit.hello() == "Mango");

        TropicalFruit tropicalFruit1 = (TropicalFruit) fruit; // cast required!
        assert tropicalFruit1.hello() == "Tropical Fruit";
        //Mango mango1 = (Mango)fruit; // this will fail
    }
    public static void main(String[] args) {
        new PolyDemo();
    }
}
