package com.yc.annotation;

import java.lang.annotation.*;

@Target(value = {ElementType.TYPE,ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MyHelloWorld {
}
