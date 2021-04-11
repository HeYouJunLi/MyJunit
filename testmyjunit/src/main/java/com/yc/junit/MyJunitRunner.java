package com.yc.junit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: reflectionAndAnnotation
 * @description:
 * @avthor: Lucky
 * @create: 2021-03-31 19:50
 */
public class MyJunitRunner {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class cls=Class.forName("com.yc.MyCalculatorTest");
        //TODO: 升级：按照maven约定的目录要求来扫描test/java下的单元测试类
        //1.获取这个类中所有方法
        Method[] ms=cls.getDeclaredMethods();
        List<Method> testMethods=new ArrayList<Method>();
        Method beforeMethod=null;
        Method afterMethod=null;
        Method beforeClassMethod=null;
        Method afterClassMethod=null;

        //2.循环这些方法，判断上面加了哪个注解
        for(Method m:ms){
            if(m.isAnnotationPresent(MyTest.class)){
                testMethods.add(m);
            }
            if(m.isAnnotationPresent(MyBefore.class)){
                beforeMethod=m;
            }
            if(m.isAnnotationPresent(MyAfter.class)){
                afterMethod=m;
            }
            if(m.isAnnotationPresent(MyBeforeClass.class)){
                beforeClassMethod=m;
            }
            if(m.isAnnotationPresent(MyAfterClass.class)){
                afterClassMethod=m;
            }
        }

        //4.按junit的运行生命周期来调用
        if(testMethods == null || testMethods.size()<=0){
            throw new RuntimeException("没有要测试的方法");
        }
        Object o=cls.newInstance();//实例化
        beforeClassMethod.invoke(o,null);
        for(Method m:testMethods){
            if(beforeMethod!=null){
                beforeMethod.invoke(o,null);
            }
            m.invoke(o,null);
            if(afterMethod!=null){
                afterMethod.invoke(o,null);
            }
        }
        afterClassMethod.invoke(o,null);

    }
}
