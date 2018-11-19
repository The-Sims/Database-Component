package SimsDal.repository;

public class ReinforceInfoRepository extends AbstractRepository<ReinforceInfoRepository, Integer> {
    @Override
    public Class<ReinforceInfoRepository> getDomainClass() {
        return ReinforceInfoRepository.class;
    }
}
