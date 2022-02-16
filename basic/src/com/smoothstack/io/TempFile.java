package com.smoothstack.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

public class TempFile {
    private String prefix, suffix;
    private Path mypath;
    private FileAttribute<Set<PosixFilePermission>> rwx;
    public TempFile(String prefix, String suffix, String mode) {
        rwx = PosixFilePermissions.asFileAttribute(PosixFilePermissions.fromString(mode));
    }
    public void create() throws IOException {
        mypath = Files.createTempFile(prefix, suffix, rwx);
        System.out.println(mypath);
        assert Files.exists(mypath);
    }
    Path getFile() { return mypath; }
    public void testFile() throws IOException {
        Files.writeString(mypath, "Hello");
        assert Files.readString(mypath).equals("Hello");
    }
}
