package com.multiDb.config;

import com.zaxxer.hikari.HikariDataSource;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Map;
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "demoEntityManagerFactory",
        transactionManagerRef = "demoTransactionManager",
        basePackages = { "com.multiDb.db1.repos" }
)
public class PrimaryDatabaseConfig {


    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.demo")
    public DataSourceProperties demoDataSourceProperties() {
        return new DataSourceProperties();
    }


    @Bean
    public DataSource demoDataSource(DataSourceProperties demoDataSourceProperties) {
        return demoDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean demoEntityManagerFactory(
            DataSource demoDataSource) {
        Map<String, String> jpaProperties = Map.of(
                "hibernate.hbm2ddl.auto", "create-drop"
        );
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(demoDataSource);
        factoryBean.setPackagesToScan("com.multiDb.db1.entities");
        factoryBean.setJpaPropertyMap(jpaProperties);
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setPersistenceUnitName("demoDataSource");

        return factoryBean;


      /*  return entityManagerFactoryBuilder.dataSource(demoDataSource)
                .packages("com.multiDb.db1.entities")
                .persistenceUnit("demoDataSource")
                .properties(jpaProperties)
                .build();*/
    }

    @Bean
    public PlatformTransactionManager demoTransactionManager(
         EntityManagerFactory demoEntityManagerFactory
    ) {
        return new JpaTransactionManager(demoEntityManagerFactory);
    }
}
