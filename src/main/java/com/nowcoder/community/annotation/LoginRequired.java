package com.nowcoder.community.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) //注解可以标在哪里
@Retention(RetentionPolicy.RUNTIME)  //什么时候有效
public @interface LoginRequired {

}
