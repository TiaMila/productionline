package de.volkswagen.f73.produktionline.backend.simulation.simresult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author d1as7bk - Jordan, Andy(SE-A/31)
 */
@RestController
public class SimResultController {
    @Autowired
    private SimResultService service;

    @GetMapping("/api/v1/simresult/{id}")
    public ResponseEntity<SimResult> getSimResult(@PathVariable long id) {
        return this.service.getSimResultById(id).isPresent() ?
                ResponseEntity.ok(this.service.getSimResultById(id).get()) :
                ResponseEntity.notFound().build();
    }
}
