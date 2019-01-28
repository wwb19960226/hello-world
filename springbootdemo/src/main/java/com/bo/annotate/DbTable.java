package com.bo.annotate;

import java.lang.annotation.*;

/**
 * 此注解操作数据库
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DbTable {
    String name() default "";
}
