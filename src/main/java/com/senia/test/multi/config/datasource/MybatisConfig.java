package com.senia.test.multi.config.datasource;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import com.senia.test.multi.config.interceptor.InterceptorForQry;
import com.senia.test.multi.config.interceptor.PageInterceptor;
import com.senia.test.multi.config.interceptor.SqlCostInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;



@Configuration
//可以进行引入 利用autowired进行注入 或者直接利用bean进行注入
//@Import({InterceptorForQry.class,SqlCostInterceptor.class})
public class MybatisConfig  {
    @Bean("master")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource master() {
        return DataSourceBuilder.create().build();
    }

    @Bean("slave")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slave() {
        return DataSourceBuilder.create().build();
    }


    @Bean("dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> dataSourceMap = new HashMap<>(2);
        dataSourceMap.put("master", master());
        dataSourceMap.put("slave", slave());
        // 将 master 数据源作为默认指定的数据源
        dynamicDataSource.setDefaultDataSource(master());
        // 将 master 和 slave 数据源作为指定的数据源
        dynamicDataSource.setDataSources(dataSourceMap);
        return dynamicDataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        // 配置数据源，此处配置为关键配置，如果没有将 dynamicDataSource作为数据源则不能实现切换
        sessionFactory.setDataSource(dynamicDataSource());
        // 做主从分离时，自定义了SqlSessionFactory，导致此拦截器没有注入
        sessionFactory.setPlugins(new Interceptor[]{sqlCostInterceptor(),interceptorForQry(),pageInterceptor()});
//        sessionFactory.setTypeAliasesPackage("com.senia.test.multi.entity");    // 扫描Model
//        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        sessionFactory.setMapperLocations(resolver.getResources("classpath*:**/mapper/*.xml"));    // 扫描映射文件
        return sessionFactory;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        // 配置事务管理, 使用事务时在方法头部添加@Transactional注解即可
        return new DataSourceTransactionManager(dynamicDataSource());
    }

    @Bean
    public SqlCostInterceptor sqlCostInterceptor(){
        // 查询时间
        // https://www.jianshu.com/p/36fb90db79ce
        return new SqlCostInterceptor();
    }
    @Bean
    public InterceptorForQry interceptorForQry(){
        //查询结果
        return new InterceptorForQry();
    }
    @Bean
    public PageInterceptor pageInterceptor(){
        //查询结果
        return new PageInterceptor();
    }
}
