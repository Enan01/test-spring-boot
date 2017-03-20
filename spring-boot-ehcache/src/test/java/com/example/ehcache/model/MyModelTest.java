package com.example.ehcache.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Enan on 17/3/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyModelTest {
    @Autowired
    private MyModel myModel;

    @Autowired
    private CacheManager cacheManager;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    /**
     * 测试ehcache的过期策略是否是针对key的
     * 测试结果为：是针对key的，每个新put进去的key都是从put进去的时间开始计时。
     * @throws Exception
     */
    @Test
    public void findByStr() throws Exception {
        myModel.findByStr("1");
        System.out.println(myModel.findByStr("1"));
        System.out.println(myModel.findByStr("1"));
        Thread.sleep(2000);
        myModel.findByStr("2");
        System.out.println(myModel.findByStr("2"));
        System.out.println(myModel.findByStr("2"));
        Thread.sleep(2000);
//        myModel.findByStr("3");
//        Thread.sleep(1000);

        Thread.sleep(1000);
        System.out.println(myModel.findByStr("1"));
        System.out.println(myModel.findByStr("2"));
        Thread.sleep(1000);
        System.out.println(myModel.findByStr("1"));
        System.out.println(myModel.findByStr("2"));
        Thread.sleep(1000);
        System.out.println(myModel.findByStr("1"));
        System.out.println(myModel.findByStr("2"));
        Thread.sleep(1000);
        System.out.println(myModel.findByStr("1"));
        System.out.println(myModel.findByStr("2"));
        Thread.sleep(1000);
        System.out.println(myModel.findByStr("1"));
        System.out.println(myModel.findByStr("2"));
        Thread.sleep(1000);
        System.out.println(myModel.findByStr("1"));
        System.out.println(myModel.findByStr("2"));


//        System.out.println(myModel.findByStr("2"));
//        System.out.println(myModel.findByStr("3"));
    }

}