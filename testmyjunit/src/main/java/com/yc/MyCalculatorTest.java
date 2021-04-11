package com.yc;

import com.yc.junit.*;

/**
 * @program: reflectionAndAnnotation
 * @description:
 * @avthor: Lucky
 * @create: 2021-03-31 19:43
 */
public class MyCalculatorTest {
    private Calculatot cal;

    @MyBeforeClass
    public static void bc(){
        System.out.println("beforeclass");
    }

    @MyBefore
    public void setUp() throws Exception {
        System.out.println("before");
        cal=new Calculatot();
    }

    @MyAfter
    public void tearDown() throws Exception {
        System.out.println("after");
    }

    @MyAfterClass
    public static void ac(){
        System.out.println("afterclass");
    }

    @MyTest
    public void add() {
        System.out.println("add测试");

    }

    @MyTest
    public void sub() {
        System.out.println("sub测试");
    }
}
