package de.laboranowitsch.jndimulti;

import java.util.HashMap;
import java.util.Map;

/**
 * Implements the {@link MultiDataSourcePlatformConfigurator}.
 * Going to be injected at {@link de.laboranowitsch.jndimulti.repo.ItemRepoImpl}
 * and {@link de.laboranowitsch.jndimulti.repo.PeopleRepoImpl}.
 *
 * Created by cla on 3/12/16.
 */
public class MultiDataSourcePlatformConfiguratorImpl implements MultiDataSourcePlatformConfigurator {

    private Map<String, DataSourceDetails> dataSourceDetailsMap = new HashMap<>();

    @Override
    public void addDetails(String key, DataSourceDetails dataSourceDetails) {
        dataSourceDetailsMap.put(key, dataSourceDetails);
    }

    @Override
    public DataSourceDetails getDetails(String key) {
        return dataSourceDetailsMap.get(key);
    }
}
