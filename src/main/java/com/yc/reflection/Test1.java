package com.yc.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * @program: reflectionAndAnnotation
 * @description:
 * @avthor: Lucky
 * @create: 2021-03-29 19:53
 */
public class Test1 {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Scanner sc=new Scanner(System.in);

        while (true){
            System.out.println("请输入类路径:");
            String path=sc.nextLine();
            System.out.println("待加载的类为:"+path);

            Class c=Class.forName(path);
            String name=c.getName();
            System.out.println(name);

            //c.getFields()
            Field[] fs=c.getDeclaredFields();
            if(fs!=null && fs.length>0){
                for(Field f:fs){
                    String modifier="";
                    switch (f.getModifiers()){
                        case 1:
                            modifier="public";
                            break;
                        case 2:
                            modifier="private";
                            break;
                    }
                    System.out.println(modifier+"\t"+f.getName());

                }
            }

            Method[] ms=c.getDeclaredMethods();
            if(ms!=null &&ms.length>0){
                for(Method m:ms){
                    System.out.println(m.getModifiers()+"\t"+m.getReturnType().toString()+"\t"+m.getName());
                }
            }

            Constructor[] cs=c.getConstructors();
            if(cs!=null && cs.length>0){
                for(Constructor m:cs){
                    System.out.println(m.getModifiers()+"\t"+m.getName());
                }
            }

            //利用反射完成实例化操作
            Object o=c.newInstance();//无参构造方法
            if(o instanceof Showable){
                Showable p= (Showable) o;
                p.show();
            }

            //利用反射调用某个方法
            System.out.println("====================");
            if(ms!=null && ms.length>0){
                for(Method m:ms){
                    if(m.getName().startsWith("sh")){
                        m.invoke(o);
                    }
                }
            }

            Map<String,String> pmap=new HashMap<String, String>();
            pmap.put("name","张三");
            pmap.put("age",30+"");
            Object oo=setValues(pmap,c);
            System.out.println(oo.toString());

        }
    }


    public static Object setValues(Map<String,String> map,Class cls) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Object o=null;
        o=cls.newInstance();
        Method[] ms=cls.getDeclaredMethods();
        if(ms!=null && ms.length>0){
            for(Method m:ms){
                if(m.getName().startsWith("set")){
                    String mName=m.getName();
                    String fName=mName.substring(3).toLowerCase();
                    String value=map.get(fName);
                    if("Integer".equalsIgnoreCase(m.getParameterTypes()[0].getTypeName()) || "Int".equalsIgnoreCase(m.getParameterTypes()[0].getTypeName())){
                        m.invoke(o,Integer.parseInt(value));
                    }else{
                        m.invoke(o);
                    }
                }
            }
        }
        return 0;
    }
}
