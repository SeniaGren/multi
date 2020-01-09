package com.senia.test.multi.annotation;

import java.lang.annotation.*;


/**
 * 多数据源注解
 * @author senia
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {

    /**
     * 数据源key值
     * @return
     */
    String value();
}
