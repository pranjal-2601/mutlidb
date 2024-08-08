package com.multiDb.test;

public class Singleton {
    private static volatile Singleton instance = null;

    private Singleton() {
    }

    private static final class InstanceHolder {
        private static final Singleton singleton = new Singleton();
    }

    public static Singleton singleton() {
        return InstanceHolder.singleton;
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) instance = new Singleton();
            }
        }
        return instance;
    }
}
