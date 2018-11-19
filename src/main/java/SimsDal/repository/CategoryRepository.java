package SimsDal.repository;

import models.Category;

public class CategoryRepository extends AbstractRepository<Category, Integer> {
    @Override
    public Class<Category> getDomainClass() {
        return Category.class;
    }
}
