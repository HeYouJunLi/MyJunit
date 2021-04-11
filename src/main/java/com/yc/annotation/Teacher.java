package com.yc.annotation;

/**
 * @program: reflectionAndAnnotation
 * @description:
 * @avthor: Lucky
 * @create: 2021-03-29 21:04
 */
@MyHelloWorld
public class Teacher {

    @MyHelloWorld
    private String name;

    @MyHelloWorld
    public String show(@MyHelloWorld  String s) {
        System.out.println(s);
        return null;
    }
}
