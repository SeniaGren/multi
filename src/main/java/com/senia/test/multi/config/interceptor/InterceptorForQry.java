package com.senia.test.multi.config.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @author senia
 */
@Component
@Slf4j
@Intercepts({@Signature(type = Executor.class, method = "query", args = {MappedStatement.class,
        Object.class, RowBounds.class, ResultHandler.class})})
public class InterceptorForQry implements Interceptor {

    @Override
    @SuppressWarnings({"rawtypes"})
    public Object intercept(Invocation invocation)
            throws Throwable
    {
        Object result = invocation.proceed(); // 执行请求方法，并将所得结果保存到result中
        log.info("执行的结果：{}",result);
        return result;
    }

    @Override
    public Object plugin(Object target)
    {
        return Plugin.wrap(target, this);
    }
    @Override
    public void setProperties(Properties arg0)
    {}

}
