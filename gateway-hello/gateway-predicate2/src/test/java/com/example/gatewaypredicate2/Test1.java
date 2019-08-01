package com.example.gatewaypredicate2;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * 描述
 * @author Ryze
 * @date 2019-08-01 14:48
 */
public class Test1 {
    public static void main(String[] args) {
        ZonedDateTime zonedDateTime1 = LocalDateTime.now().minusMinutes(1).atZone(ZoneId.systemDefault());
        ZonedDateTime zonedDateTime2 = LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault());
        System.out.println(zonedDateTime1);
        System.out.println(zonedDateTime2);
    }
}
