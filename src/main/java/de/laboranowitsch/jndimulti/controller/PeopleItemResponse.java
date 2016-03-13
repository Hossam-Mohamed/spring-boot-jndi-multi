package de.laboranowitsch.jndimulti.controller;

import de.laboranowitsch.jndimulti.entity.Item;
import de.laboranowitsch.jndimulti.entity.People;

import java.util.List;

/**
 * Response for the Rest Request.
 *
 * Created by cla on 3/11/16.
 */
public class PeopleItemResponse {

    private List<People> peoples;
    private List<Item> items;

    public PeopleItemResponse(List<People> peoples, List<Item> items) {
        this.peoples = peoples;
        this.items = items;
    }

    public List<People> getPeoples() {
        return peoples;
    }

    public void setPeoples(List<People> peoples) {
        this.peoples = peoples;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PeopleItemResponse{");
        sb.append("peoples=").append(peoples);
        sb.append(", items=").append(items);
        sb.append('}');
        return sb.toString();
    }
}
