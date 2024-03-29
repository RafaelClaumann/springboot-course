/*

********* THESE SETTINGS ARE MADE IN APPLICATION.PROPERTIES FILE! *********
********* THESE SETTINGS ARE MADE IN APPLICATION.PROPERTIES FILE! *********
********* THESE SETTINGS ARE MADE IN APPLICATION.PROPERTIES FILE! *********
********* THESE SETTINGS ARE MADE IN APPLICATION.PROPERTIES FILE! *********

package br.com.eventoapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

@Configuration
public class DataBaseConfiguration {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/eventosapp");
        dataSource.setUsername("root");
        dataSource.setPassword("<password>");
        return dataSource;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);
        // chaged from org.hibernate.dialect.MySQLDialect to org.hibernate.dialect.MySQL5Dialect
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
        adapter.setPrepareConnection(true);
        return adapter;
    }
}
*/
