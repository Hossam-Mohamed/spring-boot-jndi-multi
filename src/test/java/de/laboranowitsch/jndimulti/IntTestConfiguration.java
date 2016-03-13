package de.laboranowitsch.jndimulti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jndi.JndiTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Configuration Class for environment: -Dspring.profiles.active=int-test
 *
 * Created by cla on 3/10/16.
 */
@Configuration
@Profile(value = {Profiles.INT_TEST}) // Profile for integration testing
@EnableAutoConfiguration // here we are going with auto-configuration -> h2 embedded database
public class IntTestConfiguration {

    /**
     * Autoconfiguration
     */
    @Autowired
    private DataSource dataSource;
    @Autowired
    private PlatformTransactionManager platformTransactionManager;


    /**
     * MultiDataSourcePlatformConfigurator goes with one database both
     * repositories are mapped to one database
     *
     * @return Configuration mapped to only one database (schema)
     * @throws NamingException
     */
    @Bean
    public MultiDataSourcePlatformConfigurator multiDataSourcePlatformConfigurator() throws NamingException {
        MultiDataSourcePlatformConfigurator multiDataSourcePlatformConfigurator = new MultiDataSourcePlatformConfiguratorImpl();
        multiDataSourcePlatformConfigurator.addDetails(DataBaseIdentifiers.DATABASE_ONE, new DataSourceDetails(dataSource, platformTransactionManager));
        multiDataSourcePlatformConfigurator.addDetails(DataBaseIdentifiers.DATABASE_TWO, new DataSourceDetails(dataSource, platformTransactionManager));
        return multiDataSourcePlatformConfigurator;
    }
}
