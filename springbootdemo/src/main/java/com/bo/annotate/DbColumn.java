package com.bo.annotate;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DbColumn {
    /**
     * 属性名称
     * @return
     */
     String name() default "";

    /**
     * 是否确认修改
     */
    boolean canModify() default false;

}
