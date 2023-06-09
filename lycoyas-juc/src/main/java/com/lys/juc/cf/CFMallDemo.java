package com.lys.juc.cf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author Lycoyas
 * @create 2023-05-04 17:12
 * @description
 */
public class CFMallDemo {

    static List<NetMall> list = Arrays.asList(
            new NetMall("jd"),
            new NetMall("dangdang"),
            new NetMall("taobao")
    );

    static List<String> getPrice(List<NetMall> list, String productName) {

        return list
                .stream()
                .map(netMall ->
                        String.format(productName + "in %s price is %.2f",
                                netMall.getNetMallName(),
                                netMall.calPrice(productName)))
                .collect(Collectors.toList());

    }

    static List<String> getPriceCF(List<NetMall> list, String productName) {

        return list.stream().map(netMall -> CompletableFuture.supplyAsync(() ->
                String.format(productName + "in %s price is %.2f",
                        netMall.getNetMallName(),
                        netMall.calPrice(productName))
        )).collect(Collectors.toList())
                .stream()
                .map(s -> s.join())
                .collect(Collectors.toList());

    }
    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();
        List<String> list1 = getPriceCF(list, "mysql");
        list1.forEach(System.out::println);
        long endTime = System.currentTimeMillis();
        System.out.println("------costTime: " + (endTime - startTime) + " 毫秒");

    }
}

class NetMall {
    @Getter
    private String netMallName;

    public NetMall(String netMallName) {
        this.netMallName = netMallName;
    }

    public double calPrice(String productName) {

        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return ThreadLocalRandom.current().nextDouble() * 2 + productName.charAt(0);

    }
}
