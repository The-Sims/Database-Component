package SimsDal.repository;


import models.IncidentDescription;

public class IncidentDescriptionRepository extends AbstractRepository<IncidentDescription, Integer> {
    @Override
    public Class<IncidentDescription> getDomainClass() {
        return IncidentDescription.class;
    }
}
