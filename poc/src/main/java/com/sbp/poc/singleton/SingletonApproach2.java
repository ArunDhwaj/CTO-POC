package com.sbp.poc.singleton;

public class SingletonApproach2 {
    private static final SingletonApproach2 singleObject = new SingletonApproach2();
    private SingletonApproach2() {}
    public static SingletonApproach2 getInstance(){
        return singleObject;
    }
}
