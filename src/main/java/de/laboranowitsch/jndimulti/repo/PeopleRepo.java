package de.laboranowitsch.jndimulti.repo;

import de.laboranowitsch.jndimulti.entity.People;

import java.util.List;

/**
 * Repository to collect {@link People}s from the database.
 *
 * Created by cla on 3/10/16.
 */
public interface PeopleRepo {

    /**
     * Collect peoples from the database
     * details see {@link PeopleRepoImpl}
     *
     * @return list of collected peoples
     */
    List<People> collectPeople();

}
