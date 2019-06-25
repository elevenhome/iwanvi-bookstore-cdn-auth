package com.iwanvi.bookstore.cdn.auth.comm.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;


/**
 * freezwsc 免电数据库配置（重库）
 */
@Configuration
@MapperScan(basePackages = "com.iwanvi.bookstore.cdn.auth.dao.freezwsc", sqlSessionTemplateRef = "freeZwscSlaveSqlSessionTemplate")
public class FreeZWSCSlave {
    @Bean(name = "freeZwscSlaveDataSource")
    @ConfigurationProperties(prefix="spring.datasource.freezwscslave")
    public DataSource setDataSource() {
		return new DruidDataSource();
    }

	@Bean(name = "freeZwscSlaveTransactionManager")
	public DataSourceTransactionManager setTransactionManager(@Qualifier("freeZwscSlaveDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "freeZwscSlaveSqlSessionFactory")
	public SqlSessionFactory setSqlSessionFactory(@Qualifier("freeZwscSlaveDataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/freezwsc/*.xml"));
		return bean.getObject();
	}

	@Bean(name = "freeZwscSlaveSqlSessionTemplate")
	public SqlSessionTemplate setSqlSessionTemplate(@Qualifier("freeZwscSlaveSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}