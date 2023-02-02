package de.volkswagen.f73.produktionline.backend.simulation.simresult;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author d1as7bk - Jordan, Andy(SE-A/31)
 */
@Repository
public interface SimResultRepo extends CrudRepository<SimResult, Long> {
    @Query(value = "SELECT * FROM sim_result WHERE ref_id = ?1", nativeQuery = true)
    Optional<SimResult> findByRefId(Long ref_id);
}
