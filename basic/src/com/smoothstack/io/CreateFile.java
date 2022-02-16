package com.smoothstack.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

class MyThread extends Thread {
    private Path path;
    public MyThread(Path path) {
        this.path = path;
    }
    public void run() {
        try {
            Files.delete(path);
            assert(!Files.exists(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class CreateFile {
    private Path path;
    public CreateFile(String prefix) {
        path = Paths.get(prefix + new Random().nextInt());
    }
    public void create() throws IOException {
        FileAttribute<Set<PosixFilePermission>> rwx =
                PosixFilePermissions.asFileAttribute(PosixFilePermissions.fromString("rwxrwxrwx"));
        Path mypath = Files.createFile(path, rwx);
        System.out.println(mypath);
        assert Files.exists(path);
        Runtime.getRuntime().addShutdownHook(new MyThread(path));
    }
}
