package de.volkswagen.f73.produktionline.backend.productionline;

import de.volkswagen.f73.produktionline.backend.productionline.carmodel.CarModel;
import de.volkswagen.f73.produktionline.backend.productionline.step.ProductionStepDTO;

import java.util.List;

/**
 * @author d1as7bk - Jordan, Andy(SE-A/31)
 */
public class ProductionLineDTO {
    private long id;
    private String name;
    private CarModel carModel;
    private List<ProductionStepDTO> productionSteps;


    public ProductionLineDTO(long id, String name, CarModel carModel, List<ProductionStepDTO> productionSteps) {
        this.id = id;
        this.name = name;
        this.carModel = carModel;
        this.productionSteps = productionSteps;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CarModel getCarModel() {
        return this.carModel;
    }

    public List<ProductionStepDTO> getProductionSteps() {
        return this.productionSteps;
    }

}
