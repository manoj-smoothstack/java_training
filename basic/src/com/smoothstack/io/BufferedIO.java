package com.smoothstack.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.nio.file.Path;

public class BufferedIO {
    public BufferedIO() throws IOException{
        try {
            TempFile tempFile = new TempFile("test", ".txt", "rwxrwxrwx");
            tempFile.create();
            Path mypath = tempFile.getFile();
            BufferedReader in4 = new BufferedReader(new StringReader("asdf"));
            PrintWriter out1 = new PrintWriter(new BufferedWriter(new FileWriter(mypath.toString())));
            int lineCount = 1;
            String s = null;
            while ((s = in4.readLine()) != null)
                System.out.println(s);
                out1.println(lineCount++ + ": " + s);
            out1.close();
        } catch (EOFException e) {
            System.err.println("End of stream");
        }

    }
}
