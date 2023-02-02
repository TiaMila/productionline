package de.volkswagen.f73.produktionline.backend.simulation.simdata;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author d1as7bk - Jordan, Andy(SE-A/31)
 */
@Repository
public interface SimDataRepo extends CrudRepository<SimData, Long> {
    @Query(value = "SELECT * FROM sim_data WHERE ref_id = ?1", nativeQuery = true)
    Optional<SimData> findByRefId(Long ref_id);
}
