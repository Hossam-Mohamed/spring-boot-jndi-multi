package de.laboranowitsch.jndimulti.repo;

import de.laboranowitsch.jndimulti.DataBaseIdentifiers;
import de.laboranowitsch.jndimulti.MultiDataSourcePlatformConfigurator;
import de.laboranowitsch.jndimulti.entity.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

/**
 * Implementation of People repository. {@link PeopleRepo}
 *
 * Created by cla on 3/10/16.
 */
@Service
public class PeopleRepoImpl implements PeopleRepo {

    private final DataSource dataSource;

    @Autowired
    public PeopleRepoImpl(final MultiDataSourcePlatformConfigurator multiDataSourcePlatformConfigurator) {
        this.dataSource = multiDataSourcePlatformConfigurator.getDetails(DataBaseIdentifiers.DATABASE_ONE).getDataSource();
    }

    @Override
    public List<People> collectPeople() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.query("select * from people", ((resultSet, i) -> People.builder().personId(resultSet.getLong(1))
                .firstName(resultSet.getString(2))
                .lastName(resultSet.getString(3)).build()));
    }
}
