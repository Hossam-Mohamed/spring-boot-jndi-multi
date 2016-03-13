package de.laboranowitsch.jndimulti.repo;

import de.laboranowitsch.jndimulti.entity.Item;

import java.util.List;

/**
 * Repository to collect {@link Item}s from the database.
 *
 * Created by cla on 3/10/16.
 */
public interface ItemRepo {

    /**
     * inserts one item
     *
     * @param item
     * @return number of changed records
     */
    Integer insertItem(final String item);

    /**
     * collects all items with same name
     *
     * @param item
     * @return list of all items
     */
    List<Item> collectItems(final String item);
}
