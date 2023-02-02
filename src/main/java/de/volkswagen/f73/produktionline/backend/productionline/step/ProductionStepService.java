package de.volkswagen.f73.produktionline.backend.productionline.step;

import de.volkswagen.f73.produktionline.backend.productionline.ProductionMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author d1as7bk - Jordan, Andy(SE-A/31)
 */
@Service
public class ProductionStepService {
    private final ProductionStepRepo productionStepRepo;
    private final ProductionMapper productionMapper;


    public ProductionStepService(ProductionStepRepo productionStepRepo, ProductionMapper productionMapper) {
        this.productionStepRepo = productionStepRepo;
        this.productionMapper = productionMapper;
    }

    public List<ProductionStepDAO> getAllRobots() {
        return this.productionStepRepo.findAll().stream().filter(e -> e.getType().equals(ProductionElementType.ROBOT)).collect(Collectors.toList());
    }

    public List<ProductionStepDAO> getAllStations() {
        return this.productionStepRepo.findAll().stream().filter(e -> e.getType().equals(ProductionElementType.STATION)).collect(Collectors.toList());
    }

    public Optional<ProductionStepDTO> postNewProductionStep(ProductionStepDTO dto) {
        ProductionStepDAO dao = this.productionStepRepo.save(this.productionMapper.mapToProductionStepDAO(dto));
        return Optional.of(this.productionMapper.mapToProdctionStepDTO(dao));
    }
}
