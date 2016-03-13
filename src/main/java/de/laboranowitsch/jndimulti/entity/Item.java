package de.laboranowitsch.jndimulti.entity;

/**
 * Item database entity.
 *
 * Created by cla on 3/11/16.
 */
public class Item {

    private Long id;
    private String item;

    private Item(Builder builder) {
        setId(builder.id);
        setItem(builder.item);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public static Builder builder() {
        return new Builder();
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Item{");
        sb.append("id=").append(id);
        sb.append(", item='").append(item).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static final class Builder {
        private Long id;
        private String item;

        public Builder() {
        }

        public Builder id(Long val) {
            id = val;
            return this;
        }

        public Builder item(String val) {
            item = val;
            return this;
        }

        public Item build() {
            return new Item(this);
        }
    }
}
