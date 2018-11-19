package SimsDal.repository;

import models.User;

public class UserRepository extends AbstractRepository<User, Integer> {
    @Override
    public Class<User> getDomainClass() {
        return User.class;
    }
}
