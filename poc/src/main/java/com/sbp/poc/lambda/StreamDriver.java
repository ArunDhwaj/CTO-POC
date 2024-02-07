package com.sbp.poc.lambda;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
public class StreamDriver {
    private static Logger Log = LoggerFactory.getLogger(StreamDriver.class);

    public static void main(String[] args) {
        Log.info("Poc: StreamDriver");

        List<Product> products = createProducts();

        // 1) Filter Product,
        // 2) Transform the Product to keep only name,
        // 3) collect it into list
        filterTransformCollect(products);

        // 1) Filter Product,
        // 2) Do the GroupingBy
        // 3) Transform the Product to keep only name,
        // 4) collect it into list
        filterGroupingByTransformCollect(products);

    }

    private static List<Product> createProducts(){
                List<Product> products = Arrays.asList(new Product(1, "iPhone1", 19000.00f, "1st Version of iPhone1"),
                        new Product(2, "iPhone2", 29000.00f, "2nd Version of iPhone2"),
                        new Product(3, "iPhone3", 39000.00f, "3rd Version of iPhone3"),
                        new Product(3, "iPhone3.1", 41000.00f, "3rd Version of iPhone3.1"),
                        new Product(4, "iPhone4", 49000.00f, "4th Version of iPhone4"),
                        new Product(4, "iPhone4.1", 51000.00f, "4th Version of iPhone4.1"),
                        new Product(4, "iPhone4.2", 54000.00f, "4th Version of iPhone4.2"),
                        new Product(5, "iPhone5", 59000.00f, "5th Version of iPhone5"),
                        new Product(5, "iPhone5.1", 66000.00f, "5th Version of iPhone5.1"),
                        new Product(5, "iPhone5.2", 71000.00f, "5th Version of iPhone5.2")
                );
        return products;
    }

    private static void filterTransformCollect(List<Product> products){
        approach1(products);
        approach2(products);
    }


    private static void filterGroupingByTransformCollect(List<Product> products){
        approach3(products);
        approach4(products);
        approach5(products);
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

    private static void approach3(List<Product> products){

        Map<Integer, List<Product>> slimedMap = products
                .stream()
                .filter(p -> p.getPrice() > 40000.00f)
                .collect(groupingBy(Product::getId));

        Log.info("3. Approach3: slimedMap: "+ slimedMap);
    }

    private static void approach4(List<Product> products){

        Map<Integer, List<Float>> slimedMap = products
                .stream()
                .filter(p -> p.getPrice() > 40000.00f)
                .collect(groupingBy(Product::getId, collectingAndThen(toList(), list->{
                    List<Float> priceList = list.stream()
                    .map(Product::getPrice)
                    .collect(toList());

                    Log.info("4 priceList: "+ priceList);

                    Collections.sort(priceList, Collections.reverseOrder());
                    return priceList;
                })));

        Log.info("4. Approach4: slimedMap: value in reversed: "+ slimedMap);
    }

    private static void approach5(List<Product> products){

        Map<Integer, Optional<Product>> slimedMap = products.stream()
                .filter(p -> p.getPrice() > 40000.00f)
                .collect(groupingBy(
                        Product::getId,
                        collectingAndThen(toList(),
                                list -> list.stream()
                                                .sorted(Comparator.comparing(Product::getPrice).reversed()).skip(1).findFirst()

                )));

        Log.info("5. Approach5: slimedMap: value in reversed: get 2nd element"+ slimedMap);
    }
}
