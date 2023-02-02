package de.volkswagen.f73.produktionline.backend.productionline.carmodel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author d1as7bk - Jordan, Andy(SE-A/31)
 */
@Repository
public interface CarModelRepo extends CrudRepository<CarModel, Long> {
    List<CarModel> findAll();
}
