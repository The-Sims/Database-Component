package SimsRESTServer.response;

import models.Category;

public class CategoryJson {
    private int id;
    private String category;

    public CategoryJson(Category c) {
        this.id = c.getId();
        this.category = c.getCategory();
    }

    public CategoryJson() {
    }

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }
}
