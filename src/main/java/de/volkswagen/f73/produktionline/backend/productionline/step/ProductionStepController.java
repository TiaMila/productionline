package de.volkswagen.f73.produktionline.backend.productionline.step;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author d1as7bk - Jordan, Andy(SE-A/31)
 */
@RestController
public class ProductionStepController {
    @Autowired
    ProductionStepService productionStepService;


    @GetMapping("/api/v1/robots")
    public ResponseEntity<List<ProductionStepDAO>> getAllRobots() {
        List<ProductionStepDAO> list = this.productionStepService.getAllRobots();
        return list.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(list);
    }

    @GetMapping("/api/v1/stations")
    public ResponseEntity<List<ProductionStepDAO>> getAllManuals() {
        List<ProductionStepDAO> list = this.productionStepService.getAllStations();
        return list.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(list);
    }

    @PostMapping(value = "/api/v1/robot", consumes = {"application/json"})
    public ResponseEntity<ProductionStepDTO> postNewRobot(@RequestBody ProductionStepDTO dto) {
        return ResponseEntity.ok(this.productionStepService.postNewProductionStep(dto).get());
    }

    @PostMapping(value = "/api/v1/station", consumes = {"application/json"})
    public ResponseEntity<ProductionStepDTO> postNewStation(@RequestBody ProductionStepDTO dto) {
        return ResponseEntity.ok(this.productionStepService.postNewProductionStep(dto).get());
    }

    @PutMapping(value = "/api/v1/robot", consumes = {"application/json"})
    public ResponseEntity<ProductionStepDTO> putNewRobot(@RequestBody ProductionStepDTO dto) {
        return ResponseEntity.ok(this.productionStepService.postNewProductionStep(dto).get());
    }

    @PutMapping(value = "/api/v1/station", consumes = {"application/json"})
    public ResponseEntity<ProductionStepDTO> putNewStation(@RequestBody ProductionStepDTO dto) {
        return ResponseEntity.ok(this.productionStepService.postNewProductionStep(dto).get());
    }

}