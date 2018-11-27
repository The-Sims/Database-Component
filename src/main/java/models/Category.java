package models;

import javax.persistence.*;

@Entity
@Table(name="category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String category;

    public Category( String category) {
        this.category = category;
    }

    public Category() {
    }

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }
}
