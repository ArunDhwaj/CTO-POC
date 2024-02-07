package com.sbp.poc.lambda;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDriver {
    private static Logger Log = LoggerFactory.getLogger(StreamDriver.class);

    public static void main(String[] args) {
        Log.info("Poc: StreamDriver");

        // 1) Filter Product,
        // 2) Transform the Product to keep only name,
        // 3) collect it into list
        filterTransformCollect();
    }

    private static void filterTransformCollect(){

        List<Product> products = Arrays.asList(new Product(1, "iPhone1", 19000.00f, "1st Version of iPhone1"),
                new Product(2, "iPhone2", 29000.00f, "2nd Version of iPhone2"),
                new Product(3, "iPhone3", 39000.00f, "3rd Version of iPhone3"),
                new Product(4, "iPhone4", 49000.00f, "4th Version of iPhone4"),
                new Product(5, "iPhone5", 59000.00f, "5th Version of iPhone5"));

        approach1(products);
        approach2(products);
    }
    private static void approach1(List<Product> products){

        List<String> slimedList = products
                .stream()
                .filter(p -> p.getPrice() > 40000.00f)
                .map(p ->p.getName())
                .collect(Collectors.toList());

        Log.info("1. Approach1: slimedList: "+ slimedList);
    }

    private static void approach2(List<Product> products){
        Stream<String> slimedStream = products
                .stream()
                .filter(p -> p.getPrice() > 40000.00f)
                .map(p ->p.getName());

        Log.info("1. Before terminator calling: slimedStream: "+ slimedStream);
        List<String> slimedList = slimedStream.collect(Collectors.toList());
        Log.info("2. After terminator calling: slimedList: "+ slimedList);
    }
}
