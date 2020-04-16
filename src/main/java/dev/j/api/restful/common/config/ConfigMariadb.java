package dev.j.api.restful.common.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import dev.j.api.restful.common.component.ComponentEncrypt;


@Configuration
public class ConfigMariadb {

    @Autowired
	private ComponentEncrypt encryptor;

	@Value("${ENV}")
	String env;

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		return sqlSessionFactoryBean.getObject();
	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

    @Bean
    public DataSource dataSource(){
        
        BasicDataSource basicDataSource = new BasicDataSource();
		
		basicDataSource.setDriverClassName("org.mariadb.jdbc.Driver");

		if(env == "PRD" || env.equals("PRD")){
			// 운영 db 정보
			basicDataSource.setUrl("jdbc:mariadb://mariadb/blog");
			basicDataSource.setUsername(encryptor.decrypt("njRjmp15jEZmYCVlspcGjw=="));
			basicDataSource.setPassword(encryptor.decrypt("2NJPGt3ZM0/hjP1MnRvWCmacfRdKo/tF"));

		}else if(env == "DEV" || env.equals("DEV")){
			// 개발 db 정보
			basicDataSource.setUrl("jdbc:mariadb://127.0.0.1:3306/blog");
			basicDataSource.setPassword("1234");
			basicDataSource.setUsername("root");
		}

		
		basicDataSource.setDefaultAutoCommit(false);

		return basicDataSource;
    }

    @Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] { "dev.j.api.restful.common.vo", "dev.j.api.restful.blog.vo"});

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
		// properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.format_sql", "true");
		properties.setProperty("hibernate.jdbc.lob.non_contextual_creation", "true");
		properties.setProperty("hibernate.default_schema", "temp");
		
		em.setJpaProperties(properties);

		return em;
	}
    
}