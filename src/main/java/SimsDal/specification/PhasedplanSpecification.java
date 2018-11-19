package SimsDal.specification;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class PhasedplanSpecification {
    private PhasedplanSpecification() {
        throw new IllegalStateException("Utility class");
    }

    public static Specifiable getByName(String name) {
        if (name == null)
            throw new IllegalArgumentException();

        return new AbstractSpecification() {
            @Override
            public Criterion toCriterion() {
                return Restrictions.eq("name", name.toLowerCase());
            }
        };
    }
}
