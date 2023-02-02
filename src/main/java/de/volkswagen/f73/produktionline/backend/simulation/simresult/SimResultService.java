package de.volkswagen.f73.produktionline.backend.simulation.simresult;

import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author d1as7bk - Jordan, Andy(SE-A/31)
 */
@Service
public class SimResultService {
    private final SimResultRepo repo;

    public SimResultService(SimResultRepo repo) {
        this.repo = repo;
    }

    public Optional<SimResult> getSimResultById(long id) {
        return this.repo.findById(id);
    }
}
