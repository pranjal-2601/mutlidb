package com.multiDb.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "carEntityManager",
        transactionManagerRef = "carTransactionManager",
        basePackages = {"com.multiDb.db2.repos"}
)
public class CarDatabaseConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.cars")
    public DataSourceProperties carDatabaseProperties() {
        return new DataSourceProperties();
    }
    @Bean
    public DataSource carDataSource(DataSourceProperties carDatabaseProperties) {
        return carDatabaseProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean carEntityManager(
            DataSource carDataSource

    ) {
        Map<String, String> jpaProperties = Map.of(
                "hibernate.hbm2ddl.auto", "create-drop"
        );

        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(carDataSource);
        factoryBean.setPackagesToScan("com.multiDb.db2.entities");
        factoryBean.setJpaPropertyMap(jpaProperties);
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setPersistenceUnitName("carDataSource");

        return factoryBean;

        /*return entityManagerFactoryBuilder.dataSource(carDataSource)
                .packages("com.multiDb.db2.entities")
                .persistenceUnit("carDataSource")
                .properties(jpaProperties)
                .build();*/
    }
    @Bean
    public PlatformTransactionManager carTransactionManager(
            EntityManagerFactory carEntityManager
    ) {
     return new JpaTransactionManager(carEntityManager);
    }
}
