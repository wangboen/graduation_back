package nju.edu.graduation.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
public class DruidConfig {
    @Bean(initMethod = "init",destroyMethod = "close")
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DruidDataSource druidDataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        List<Filter> filters = new ArrayList<>();
        StatFilter statFilter = new StatFilter();

        statFilter.setLogSlowSql(true);
        statFilter.setSlowSqlMillis(2);
        filters.add(statFilter);
        druidDataSource.setProxyFilters(filters);
        return druidDataSource;
    }

    @Bean
    public ServletRegistrationBean druidServlet(){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean<>(new StatViewServlet());
        Set<String> set = new HashSet<>();
        set.add("/druid/*");
        servletRegistrationBean.setUrlMappings(set);
        servletRegistrationBean.addInitParameter("loginUsername","admin");
        servletRegistrationBean.addInitParameter("loginPassword","123");
        return servletRegistrationBean;
    }
}