package de.laboranowitsch.jndimulti.repo;

import de.laboranowitsch.jndimulti.DataBaseIdentifiers;
import de.laboranowitsch.jndimulti.MultiDataSourcePlatformConfigurator;
import de.laboranowitsch.jndimulti.entity.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * Implementation of Item repository. {@link ItemRepo}
 *
 * Created by cla on 3/10/16.
 */
@Service
public class ItemRepoImpl implements ItemRepo {

    private final DataSource dataSource;
    private final PlatformTransactionManager platformTransactionManager;
    private static Logger LOG = LoggerFactory.getLogger(ItemRepoImpl.class);

    @Autowired
    public ItemRepoImpl(final MultiDataSourcePlatformConfigurator multiDataSourcePlatformConfigurator) {
        this.dataSource = multiDataSourcePlatformConfigurator.getDetails(DataBaseIdentifiers.DATABASE_TWO).getDataSource();
        this.platformTransactionManager = multiDataSourcePlatformConfigurator.getDetails(DataBaseIdentifiers.DATABASE_TWO).getPlatformTransactionManager();
    }


    @Override
    public Integer insertItem(String item) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        TransactionTemplate transactionTemplate = new TransactionTemplate(platformTransactionManager);
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("item", item);
        Integer updateCount = transactionTemplate.execute(status -> {
            Integer count = 0;
            try {
                count = namedParameterJdbcTemplate.update("insert into send_items (item) values (:item)", sqlParameterSource);
            } catch(Exception exc) {
                LOG.error("Error happened: {}", exc.getStackTrace());
                status.setRollbackOnly();
            } finally {
                LOG.info("Update count: {}", count);
                return count;
            }
        });
        return updateCount;
    }

    @Override
    public List<Item> collectItems(String item) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("item", item);
        return namedParameterJdbcTemplate.query("select * from send_items where item = :item", sqlParameterSource, ((resultSet, i) -> Item.builder()
                .id(resultSet.getLong(1))
                .item(resultSet.getString(2)).build()));
    }
}
