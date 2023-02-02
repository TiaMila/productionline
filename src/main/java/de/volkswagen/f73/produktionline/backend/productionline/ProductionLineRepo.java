package de.volkswagen.f73.produktionline.backend.productionline;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author d1as7bk - Jordan, Andy(SE-A/31)
 */
@Repository
public interface ProductionLineRepo extends JpaRepository<ProductionLineDAO, Long> {
    List<ProductionLineDAO> findAll();
}
