package de.volkswagen.f73.produktionline.backend.productionline.carmodel;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author d1as7bk - Jordan, Andy(SE-A/31)
 */
@RestController
public class CarModelController {

    private final CarModelService carModelService;

    public CarModelController(CarModelService carModelService) {
        this.carModelService = carModelService;
    }

    @GetMapping("/api/v1/carmodels")
    public ResponseEntity<List<CarModel>> getAll() {
        List<CarModel> carModelList = this.carModelService.getAll();
        return carModelList.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(carModelList);
    }

    @GetMapping("/api/v1/carmodel")
    public ResponseEntity<CarModel> getCarModelById(long id) {
        return ResponseEntity.ok(this.carModelService.getById(id));

    }

    @PostMapping("/api/v1/carmodel")
    public ResponseEntity<CarModel> postCarModel(@Valid @RequestBody CarModel carModel) {
        return ResponseEntity.ok(this.carModelService.createNewCarModel(carModel));
    }

    @PutMapping("/api/v1/carmodel")
    public ResponseEntity<CarModel> putCarModel(@Valid @RequestBody CarModel carModel) {
        return this.carModelService.putCarModel(carModel).isPresent() ?
                ResponseEntity.ok(carModel) :
                ResponseEntity.notFound().build();
    }

}
