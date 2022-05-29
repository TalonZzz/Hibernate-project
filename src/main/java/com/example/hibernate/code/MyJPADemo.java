package com.example.hibernate.code;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;

import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnitUtil;
import javax.sql.DataSource;
import java.util.Properties;

public class MyJPADemo {
    public static void main(String[] args) {
        MyJPADemo jpaDemo = new MyJPADemo();
        DataSource dataSource = jpaDemo.getDataSource();
        Properties properties = jpaDemo.getProperties();
        EntityManagerFactory entityManagerFactory = jpaDemo.entityManagerFactory(dataSource, properties);
        EntityManager em = entityManagerFactory.createEntityManager();
        PersistenceUnitUtil unitUtil = entityManagerFactory.getPersistenceUnitUtil();

        insertToCustomer(em);
    }

    private static void insertToCustomer(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Customer c = new Customer();
        c.setId("1");
        c.setName("Jerry");
        em.merge(c);
        tx.commit();
    }


    private DataSource getDataSource() {
        final MysqlDataSource dataSource = new MysqlDataSource();
        //dataSource.setDatabaseName("customer");
        dataSource.setUrl("jdbc:mysql://localhost:3306/customerservice");
        dataSource.setUser("root");
        dataSource.setPassword("password");

        return dataSource;
    }

    private Properties getProperties() {
        final Properties properties = new Properties();
        properties.put( "hibernate.dialect", "org.hibernate.dialect.MySQLDialect" );
        properties.put( "hibernate.connection.driver_class", "com.mysql.jdbc.Driver" );
//        properties.put("hibernate.show_sql", "true");
        return properties;
    }

    private EntityManagerFactory entityManagerFactory(DataSource dataSource, Properties hibernateProperties ){
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com/example/hibernate/code");
        em.setJpaVendorAdapter( new HibernateJpaVendorAdapter() );
        em.setJpaProperties( hibernateProperties );
        em.setPersistenceUnitName( "demo-unit" );
        em.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        em.afterPropertiesSet();
        return em.getObject();
    }
}
