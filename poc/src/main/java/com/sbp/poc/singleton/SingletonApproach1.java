package com.sbp.poc.singleton;

public class SingletonApproach1 {
    private SingletonApproach1 () {}

    private static class MyWrapper {
        static SingletonApproach1 INSTANCE = new SingletonApproach1();
    }

    public static SingletonApproach1 getInstance() {
        return MyWrapper.INSTANCE;
    }
}
