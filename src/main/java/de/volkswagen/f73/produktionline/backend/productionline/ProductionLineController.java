package de.volkswagen.f73.produktionline.backend.productionline;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author d1as7bk - Jordan, Andy(SE-A/31)
 */
@RestController
public class ProductionLineController {

    @Autowired
    private ProductionLineService productionLineService;

    @GetMapping("/api/v1/plines/")
    public ResponseEntity<List<ProductionLineDTO>> getAll() {
        List<ProductionLineDTO> list = this.productionLineService.getall();
        return list.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(list);
    }

    @PostMapping(value = "/api/v1/pline/", consumes = {"application/json"})
    public ResponseEntity<ProductionLineDTO> postNewLine(@Valid @RequestBody ProductionLineDTO productionLine) {
        this.productionLineService.putNewLine(productionLine);
        return ResponseEntity.ok(productionLine);
    }

    @PutMapping(value = "/api/v1/pline/", consumes = {"application/json"})
    public ResponseEntity<ProductionLineDTO> putNewLine(@Valid @RequestBody ProductionLineDTO productionLine) {
        this.productionLineService.putUpdateLine(productionLine);
        return ResponseEntity.ok(productionLine);
    }

    @DeleteMapping(value = "/api/v1/pline/", consumes = {"application/json"})
    public ResponseEntity<String> deletProductionLine(@Valid @RequestBody long id) {
        this.productionLineService.deleteLine(id);
        return ResponseEntity.ok("ok");
    }
}

