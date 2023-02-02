package de.volkswagen.f73.produktionline.backend.simulation;

import de.volkswagen.f73.produktionline.backend.productionline.ProductionLineDAO;
import de.volkswagen.f73.produktionline.backend.productionline.ProductionLineRepo;
import de.volkswagen.f73.produktionline.backend.productionline.ProductionMapper;
import de.volkswagen.f73.produktionline.backend.productionline.carmodel.CarModel;
import de.volkswagen.f73.produktionline.backend.productionline.carmodel.CarModelRepo;
import de.volkswagen.f73.produktionline.backend.productionline.step.ProductionElementType;
import de.volkswagen.f73.produktionline.backend.productionline.step.ProductionStepDTO;
import de.volkswagen.f73.produktionline.backend.productionline.step.RobotDTO;
import de.volkswagen.f73.produktionline.backend.simulation.simdata.SimData;
import de.volkswagen.f73.produktionline.backend.simulation.simresult.SimResult;
import de.volkswagen.f73.produktionline.backend.simulation.simresult.SimResultRepo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author d1as7bk - Jordan, Andy(SE-A/31)
 */
@Component
public class SimMapper {
    private final QuantityCalculator calculator;
    private final ProductionLineRepo productionLineRepo;
    private final CarModelRepo carModelRepo;
    private final ProductionMapper productionMapper;
    private final SimResultRepo resultRepo;

    public SimMapper(ProductionLineRepo productionLineRepo, CarModelRepo carModelRepo, ProductionMapper productionMapper, SimResultRepo resultRepo) {
        this.productionMapper = productionMapper;
        this.resultRepo = resultRepo;
        this.calculator = new QuantityCalculator();
        this.productionLineRepo = productionLineRepo;
        this.carModelRepo = carModelRepo;
    }

    public SimResult createResult(SimData simData, String message) {
        Optional<ProductionLineDAO> optional = this.productionLineRepo.findById(simData.getRefId());
        CarModel model = this.carModelRepo.findById(optional.get().getCarModel()).get();
        Long maxRunTimeByRobot = this.getMaxRunTimeByRobot(simData.getRefId());
        SimResult simResult = new SimResult();
        simResult.setMessage(message);
        if (maxRunTimeByRobot <= simData.getTotalTime()) {
            simResult.setMessage("Eine Roboter ist ausgefallen; Simulation wurde pausiert");
        }
        List<Long> longList = this.productionMapper.mapToProductionLineDTO(optional.get()).getProductionSteps().stream().map(e -> e.getProductionTime()).collect(Collectors.toList());

        simResult.setRefId(simData.getRefId());

        simResult.setQuantity(this.calculator.calculateQuantity(longList, simData.getTimeModifier(), model.getSpeedFactor(), simData.getTotalTime(), maxRunTimeByRobot));
        simResult.setRefId(optional.get().getId());

        Optional<SimResult> checkingQpt = this.resultRepo.findByRefId(simData.getRefId());
        if (checkingQpt.isPresent()) {
            simResult.setId(this.resultRepo.findByRefId(simResult.getRefId()).get().getId());
        } else {
            simResult.setRefId(simData.getRefId());
        }

        this.resultRepo.save(simResult);
        return simResult;
    }

    private long getMaxRunTimeByRobot(Long id) {
        long maxRunTimeByRobots = 0;
        Optional<ProductionLineDAO> opt = this.productionLineRepo.findById(id);
        List<ProductionStepDTO> productionStepDTOList = new ArrayList<>();
        List<RobotDTO> robotDTOS = new ArrayList<>();
        if (opt.isPresent()) {
            productionStepDTOList = (this.productionMapper.mapToProductionLineDTO(opt.get()).getProductionSteps());
        }
        productionStepDTOList.forEach(e -> {
            if (e.getProductionElementType() == ProductionElementType.ROBOT) {
                robotDTOS.add((RobotDTO) e);
            }
        });
        for (RobotDTO r : robotDTOS) {
            if (r.getServiceTime() <= maxRunTimeByRobots && maxRunTimeByRobots == 0) {
                maxRunTimeByRobots = r.getServiceTime();
            }
        }
        return maxRunTimeByRobots;

    }
}
