package com.iwanvi.bookstore.cdn.auth.comm.config.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;


/**
 * usercenter 中文书城的用户中心库 （重库）
 */
@Configuration
@MapperScan(basePackages = "com.iwanvi.bookstore.cdn.auth.dao.usercenter", sqlSessionTemplateRef = "userCenterSlaveSqlSessionTemplate")
public class UserCenterSalve {
	@Primary
	@Bean(name = "userCenterSlaveDataSource")
	@Qualifier("userCenterSlaveDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.usercenterslave")
	public DataSource setDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "userCenterSlaveTransactionManager")
	@Primary
	public DataSourceTransactionManager setTransactionManager(@Qualifier("userCenterSlaveDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "userCenterSlaveSqlSessionFactory")
	@Primary
	public SqlSessionFactory setSqlSessionFactory(@Qualifier("userCenterSlaveDataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/usercenter/*.xml"));
		return bean.getObject();
	}

	@Bean(name = "userCenterSlaveSqlSessionTemplate")
	@Primary
	public SqlSessionTemplate setSqlSessionTemplate(@Qualifier("userCenterSlaveSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
