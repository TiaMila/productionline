package de.volkswagen.f73.produktionline.backend.productionline;

import de.volkswagen.f73.produktionline.backend.productionline.carmodel.CarModelService;
import de.volkswagen.f73.produktionline.backend.productionline.step.ProductionStepRepo;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author d1as7bk - Jordan, Andy(SE-A/31)
 */
@Service
public class ProductionLineService {

    private final ProductionLineRepo productionLineRepo;
    private final CarModelService carModelService;
    private final ProductionMapper mapper;
    private final ProductionStepRepo productionStepRepo;


    public ProductionLineService(ProductionLineRepo productionLineRepo, CarModelService carModelService, ProductionMapper mapper, ProductionStepRepo productionStepRepo) {
        this.productionLineRepo = productionLineRepo;
        this.carModelService = carModelService;
        this.productionStepRepo = productionStepRepo;
        this.mapper = new ProductionMapper(this.carModelService, productionStepRepo);
    }

    public List<ProductionLineDTO> getall() {
        return this.productionLineRepo.findAll().stream().map(this.mapper::mapToProductionLineDTO).collect(Collectors.toList());
    }

    public ProductionLineDAO putNewLine(ProductionLineDTO productionLineDTO) {
        if (productionLineDTO.getId() <= 0) productionLineDTO.setId(this.productionLineRepo.findAll().size() + 1);
        return this.productionLineRepo.save(this.mapper.mapToProductionLineDAO(productionLineDTO));
    }


    public ProductionLineDAO putUpdateLine(ProductionLineDTO productionLineDTO) {
        return this.productionLineRepo.save(this.mapper.mapToProductionLineDAO(productionLineDTO));
    }

    public void deleteLine(@Valid long productionLineId) {
        this.productionLineRepo.deleteById(productionLineId);
    }
}

