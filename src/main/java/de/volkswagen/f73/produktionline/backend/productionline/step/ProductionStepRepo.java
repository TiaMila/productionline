package de.volkswagen.f73.produktionline.backend.productionline.step;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author d1as7bk - Jordan, Andy(SE-A/31)
 */
@Repository
public interface ProductionStepRepo extends CrudRepository<ProductionStepDAO, Long> {
    List<ProductionStepDAO> findAll();
}
