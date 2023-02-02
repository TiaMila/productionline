package de.volkswagen.f73.produktionline.backend.productionline.carmodel;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author d1as7bk - Jordan, Andy(SE-A/31)
 */
@Service
public class CarModelService {
    private final CarModelRepo carModelRepo;

    public CarModelService(CarModelRepo carModelRepo) {
        this.carModelRepo = carModelRepo;
    }

    public CarModel getById(long id) {
        return this.carModelRepo.findById(id).get();
    }

    public List<CarModel> getAll() {
        return this.carModelRepo.findAll();
    }


    public CarModel createNewCarModel(CarModel carModel) {
        return this.carModelRepo.save(carModel);
    }

    public Optional<CarModel> putCarModel(CarModel carModel) {
        return Optional.of(this.carModelRepo.save(carModel));
    }
}
