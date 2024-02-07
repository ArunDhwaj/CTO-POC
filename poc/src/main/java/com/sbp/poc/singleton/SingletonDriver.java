package com.sbp.poc.singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sbp.poc.singleton.*;

public class SingletonDriver {
    private static Logger Log = LoggerFactory.getLogger(SingletonDriver.class);

    public static void main(String[] args) {
        Log.info("Poc: SingletonDriver");

        //Testing singleton
        createSingleton();
    }

    private static void createSingleton(){
        Log.info("1. pointer: "+SingletonApproach1.getInstance());
        Log.info("2. pointer: "+SingletonApproach1.getInstance());

        Log.info("3. pointer: "+ SingletonApproach2.getInstance());
        Log.info("4. pointer: "+ SingletonApproach2.getInstance());

        Log.info("5. pointer: "+ SingletonApproach2.getInstance());
    }
}
