package SimsDal.repository;

import models.Incident;

public class IncidentRepository extends AbstractRepository<Incident, Integer> {
    @Override
    public Class<Incident> getDomainClass() {
        return Incident.class;
    }
}
