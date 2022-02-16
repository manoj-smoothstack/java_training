package com.smoothstack.system;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;

public class ReflectionsRunner {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new ReflectionsRunner();
    }
    public ReflectionsRunner() throws IOException, ClassNotFoundException {
        Class[] classes = getClasses("com.smoothstack");
        for (Class c: classes) {
            //System.out.println(c.getName());
        }
    }
    public static Class[] getClasses(String packageName)
            throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<File>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class> classes = new ArrayList<Class>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes.toArray(new Class[classes.size()]);
    }
    private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<Class>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                Class cls = Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6));
                //System.out.println(cls.getModifiers());
                if (cls.getModifiers() == 1)
                    classes.add(cls);
            }
        }
        return classes;
    }
}
