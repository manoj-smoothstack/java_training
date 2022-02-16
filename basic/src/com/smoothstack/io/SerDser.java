package com.smoothstack.io;

// Java code for serialization and deserialization
// of a Java object

import java.io.*;

class SerDe implements java.io.Serializable {
    public int a;
    public String b;

    // Default constructor
    public SerDe(int a, String b) {
        this.a = a;
        this.b = b;
    }
}

public class SerDser {
    public SerDser() {
        SerDe object = new SerDe(1, "Hello");
        String filename = "file.ser";

        // Serialization
        try {
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(object);

            out.close();
            file.close();

            System.out.println("Object has been serialized");

        } catch (IOException ex) {
            System.out.println("IOException is caught");
        }


        SerDe object1 = null;

        // Deserialization
        try {
            // Reading the object from a file
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            object1 = (SerDe) in.readObject();

            in.close();
            file.close();

            System.out.println("Object has been deserialized ");
            System.out.println("a = " + object1.a);
            System.out.println("b = " + object1.b);
        } catch (IOException ex) {
            System.out.println("IOException is caught");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }
    }
}

