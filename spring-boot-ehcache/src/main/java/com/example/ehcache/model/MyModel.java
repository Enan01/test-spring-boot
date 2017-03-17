package com.example.ehcache.model;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by Enan on 17/3/17.
 */
@Component
public class MyModel {

    @Cacheable(value = "incrementEmployees", key = "#s")
    public String findByStr(String s) {
        String result = UUID.randomUUID().toString();
//        System.out.println("key:" + s + ", val:" + result);
        return result;
    }
}
