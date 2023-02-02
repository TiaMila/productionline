package de.volkswagen.f73.produktionline.backend.simulation.simdata;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author d1as7bk - Jordan, Andy(SE-A/31)
 */
@RestController
public class SimController {

    private final SimService simService;

    public SimController(SimService simService) {
        this.simService = simService;
    }

    @GetMapping("api/v1/isSimulationRunning/{id}")
    public ResponseEntity<Boolean> isSimulationRunning(@PathVariable Long id) {
        return this.simService.isThisSimulationRunning(id).isPresent() ?
                ResponseEntity.ok(this.simService.isThisSimulationRunning(id).get()) :
                ResponseEntity.noContent().build();
    }

    @PostMapping("api/v1/simstart")
    public ResponseEntity<SimData> startSim(@RequestBody SimParadigmas paradigmas) {
        return ResponseEntity.ok(this.simService.startSim(paradigmas));
    }

    @PostMapping("api/v1/simpause")
    public ResponseEntity<SimData> pauseSim(@RequestBody SimParadigmas paradigmas) {
        return this.simService.pauseSim(paradigmas).isPresent() ?
                ResponseEntity.ok(this.simService.pauseSim(paradigmas).get()) :
                ResponseEntity.noContent().build();
    }

    @PostMapping("api/v1/simstop/")
    public void stopSim(@RequestBody SimParadigmas paradigmas) {
        this.simService.stopSim(paradigmas.idFromProductionLine());
    }
}
