package SimsDal.repository;

import models.Tip;

public class TipRepository extends AbstractRepository<Tip, Integer> {
    @Override
    public Class<Tip> getDomainClass() {
        return Tip.class;
    }
}
