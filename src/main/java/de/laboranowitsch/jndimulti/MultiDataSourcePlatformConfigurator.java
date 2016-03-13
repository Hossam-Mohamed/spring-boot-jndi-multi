package de.laboranowitsch.jndimulti;


/**
 * Proxy for database and transaction manager for different profiles.
 * Is going to injected where {@link javax.sql.DataSource}
 * or {@link org.springframework.transaction.PlatformTransactionManager}
 * is needed.
 *
 * Created by cla on 3/12/16.
 */
public interface MultiDataSourcePlatformConfigurator {

    /**
     * Add {@link DataSourceDetails}
     *
     * @param key
     * @param dataSourceDetails
     */
    void addDetails(String key, DataSourceDetails dataSourceDetails);

    /**
     * Retrieve {@link DataSourceDetails}
     *
     * @param key
     * @return
     */
    DataSourceDetails getDetails(String key);
}
