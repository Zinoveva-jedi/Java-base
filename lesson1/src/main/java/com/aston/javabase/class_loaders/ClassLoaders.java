package com.aston.javabase.class_loaders;


import com.aston.javabase.Homework;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Arrays;


public class ClassLoaders {

    /*
     * Для того, чтобы получить загрузчик, которым был загружен класс,
     * необходимо воспользоваться методом "Класс".class.getClassLoader().
     */
    public void printBootstrapClassLoaders() {

        // все базовые классы загружены базовым загрузчиком, доступа к которому из управляемой среды нет
        System.out.println("\nBootstrap Classloaders");

        System.out.println("Classloader of ArrayList: " + ArrayList.class.getClassLoader()); // null

        System.out.println("Classloader of Boolean: " + Boolean.class.getClassLoader()); // null
    }

    public void printPlatformClassLoaders() {
        System.out.println("\nPlatform Classloaders");

        System.out.println("Classloader of DriverManager: " + DriverManager.class.getClassLoader()); // ClassLoaders$PlatformClassLoader

        System.out.println(ClassLoader.getPlatformClassLoader().getDefinedPackages()[0]); // package java.sql
    }

    public void printApplicationClassLoaders() {
        System.out.println("Application Classloaders\n");

        System.out.println("Classloader of Homework: " + Homework.class.getClassLoader()); // ClassLoaders$AppClassLoader
    }

    public void printReflectionExample() {
        System.out.println("Reflection\n");
        Arrays.stream(Homework.class.getMethods())
                .forEach(method -> System.out.println(method.getName()));
    }
}
