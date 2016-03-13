package de.laboranowitsch.jndimulti;

import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Combines {@link DataSource} and {@link PlatformTransactionManager}
 * under a common key {@link DataBaseIdentifiers}.
 *
 * Created by cla on 3/12/16.
 */
public class DataSourceDetails {

    private final DataSource dataSource;
    private final PlatformTransactionManager platformTransactionManager;

    public DataSourceDetails(final DataSource dataSource, final PlatformTransactionManager platformTransactionManager) {
        this.dataSource = dataSource;
        this.platformTransactionManager = platformTransactionManager;
    }

    public static DataSourceDetails of(final DataSource dataSource, final PlatformTransactionManager platformTransactionManager) {
        return new DataSourceDetails(dataSource, platformTransactionManager);
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public PlatformTransactionManager getPlatformTransactionManager() {
        return platformTransactionManager;
    }
}
