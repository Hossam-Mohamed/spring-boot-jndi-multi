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
 * Production configuration for two JNDI datasources.
 *
 * Created by cla on 3/10/16.
 */
@Configuration
@Profile(value = {Profiles.PROD})
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class})
public class JndiProdConfiguration {

    @Autowired
    private JndiProdProperties jndiProdProperties;

    @Bean
    public DataSource dataSourceOne() throws NamingException {
        JndiTemplate jndiTemplate = new JndiTemplate();
        return (DataSource) jndiTemplate.lookup(jndiProdProperties.getJndiStringOne());
    }

    @Bean
    public DataSource dataSourceTwo() throws NamingException {
        JndiTemplate jndiTemplate = new JndiTemplate();
        return (DataSource) jndiTemplate.lookup(jndiProdProperties.getJndiStringTwo());
    }

    @Bean
    public PlatformTransactionManager transactionManagerTwo() throws NamingException {
        return new DataSourceTransactionManager(dataSourceTwo());
    }

    @Bean
    public PlatformTransactionManager transactionManagerOne() throws NamingException {
        return new DataSourceTransactionManager(dataSourceOne());
    }

    /**
     * Creates a {@link MultiDataSourcePlatformConfigurator} for production with
     * two separate Databases.
     *
     * @return {@link MultiDataSourcePlatformConfigurator}
     * @throws NamingException
     */
    @Bean
    public MultiDataSourcePlatformConfigurator multiDataSourcePlatformConfigurator() throws NamingException {
        MultiDataSourcePlatformConfigurator multiDataSourcePlatformConfigurator = new MultiDataSourcePlatformConfiguratorImpl();
        multiDataSourcePlatformConfigurator.addDetails(DataBaseIdentifiers.DATABASE_ONE, DataSourceDetails.of(dataSourceOne(), transactionManagerOne()));
        multiDataSourcePlatformConfigurator.addDetails(DataBaseIdentifiers.DATABASE_TWO, DataSourceDetails.of(dataSourceTwo(), transactionManagerTwo()));
        return multiDataSourcePlatformConfigurator;
    }
}
