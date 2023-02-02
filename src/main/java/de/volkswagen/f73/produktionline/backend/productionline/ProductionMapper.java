package de.volkswagen.f73.produktionline.backend.productionline;

import de.volkswagen.f73.produktionline.backend.productionline.carmodel.CarModel;
import de.volkswagen.f73.produktionline.backend.productionline.carmodel.CarModelService;
import de.volkswagen.f73.produktionline.backend.productionline.step.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author d1as7bk - Jordan, Andy(SE-A/31)
 */
@Service
public class ProductionMapper {
    private final CarModelService carModelService;
    private final ProductionStepRepo productionStepRepo;

    public ProductionMapper(CarModelService carModelService, ProductionStepRepo productionStepRepo) {
        this.carModelService = carModelService;
        this.productionStepRepo = productionStepRepo;
    }


    public RobotDTO mapToRobotDTO(RobotDAO dao) {
        return new RobotDTO(dao.getId(),
                dao.getType(),
                dao.getName(),
                dao.getProductionStepName(),
                dao.getProductionTime(),
                dao.getServiceTime());
    }

    public RobotDAO mapToRobotDAO(RobotDTO dto) {
        return new RobotDAO(dto.getId(), dto.getName(), dto.getProductionStepName(), dto.getProductionTime(), dto.getProductionElementType(), dto.getServiceTime());
    }

    public StationDTO mapToStationDTO(StationDAO dao) {
        return new StationDTO(
                dao.getId(),
                dao.getType(),
                dao.getName(),
                dao.getProductionStepName(),
                dao.getProductionTime(),
                dao.getEmployees());
    }

    public StationDAO mapToStationDAO(StationDTO dto) {
        StationDAO dao = new StationDAO();
        dao.setId(dto.getId());
        dao.setName(dto.getName());
        dao.setProductionStepName(dto.getProductionStepName());
        dao.setProductionTime(dto.getProductionTime());
        dao.setProductionElementType(dto.getProductionElementType());
        dao.setEmployees(dto.getEmployees());
        return dao;
    }

    public ProductionLineDAO mapToProductionLineDAO(ProductionLineDTO dto) {
        Long newId = dto.getId() <= 0 ? null : dto.getId();
        this.carModelService.putCarModel(dto.getCarModel());
        long newCarModelid = this.carModelService.putCarModel(dto.getCarModel()).get().getId();
        List<Long> ListOfId = dto.getProductionSteps().stream().map(e -> e.getId()).collect(Collectors.toList());
        return new ProductionLineDAO(newId, dto.getName(), newCarModelid, ListOfId);

    }

    public ProductionLineDTO mapToProductionLineDTO(ProductionLineDAO dao) {
        List<ProductionStepDAO> listForDTO = dao.getListOfId().stream().map(e -> this.productionStepRepo.findById(e).get()).collect(Collectors.toList());
        List<ProductionStepDTO> list = listForDTO.stream().map(e -> {
            if (e.getType().equals(ProductionElementType.ROBOT)) {
                return this.mapToRobotDTO((RobotDAO) e);
            }
            return this.mapToStationDTO((StationDAO) e);
        }).collect(Collectors.toList());
        CarModel model = this
                .carModelService
                .getById(dao.getCarModel());
        ProductionLineDTO dto = new ProductionLineDTO(dao.getId(), dao.getName(), model, list);
        return dto;
    }

    public List<ProductionLineDAO> mapToListOfProductionDAO(List<ProductionLineDTO> list) {
        return list.stream().map(this::mapToProductionLineDAO).collect(Collectors.toList());
    }

    public ProductionStepDAO mapToProductionStepDAO(ProductionStepDTO dto) {
        if (dto.getProductionElementType().equals(ProductionElementType.STATION)) {
            return this.mapToStationDAO((StationDTO) dto);
        } else {
            return this.mapToRobotDAO((RobotDTO) dto);
        }
    }

    public ProductionStepDTO mapToProdctionStepDTO(ProductionStepDAO dao) {
        if (dao.getProductionElementType().equals(ProductionElementType.STATION)) {
            return this.mapToStationDTO((StationDAO) dao);
        } else {
            return this.mapToRobotDTO((RobotDAO) dao);
        }
    }
}



