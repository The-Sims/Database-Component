package SimsDal.repository;

import models.Phasedplan;

public class PhasedplanRepository extends AbstractRepository<Phasedplan, Integer> {

    @Override
    public Class<Phasedplan> getDomainClass() {
        return Phasedplan.class;
    }
}
