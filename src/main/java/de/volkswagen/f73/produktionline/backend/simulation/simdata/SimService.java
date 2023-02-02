package de.volkswagen.f73.produktionline.backend.simulation.simdata;

import de.volkswagen.f73.produktionline.backend.simulation.SimMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author d1as7bk - Jordan, Andy(SE-A/31)
 */
@Service
public class SimService {
    private final SimDataRepo repo;
    private final SimMapper simResultMapper;

    public SimService(SimDataRepo repo, SimMapper simResultMapper) {
        this.repo = repo;
        this.simResultMapper = simResultMapper;
    }

    public SimData startSim(SimParadigmas paradigmas) {
        return (!this.repo.findByRefId(paradigmas.idFromProductionLine()).isPresent()) ? this.startNewSim(paradigmas) :
                this.resumeSim(paradigmas);
    }

    public Optional<SimData> pauseSim(SimParadigmas paradigmas) {
        Optional<SimData> optionalOfSimData = this.repo.findById(paradigmas.idFromProductionLine());
        return optionalOfSimData.isPresent() ? Optional.of(this.doPause(optionalOfSimData, paradigmas)) : Optional.empty();
    }

    private SimData doPause(Optional<SimData> optionalOfSimData, SimParadigmas paradigmas) {
        SimData simData = optionalOfSimData.get();
        simData.setPause(true);
        simData.setPausedTime(System.currentTimeMillis() / 1000);
        simData.setTurnBreakTime(simData.getPausedTime() - simData.getStartedTime());
        simData.setTotalTime(simData.getTotalTime() + ((long) (simData.getTurnBreakTime() * (paradigmas.timeFactor() * 10) / 10)));
        simData.setActive(false);
        simData.setStartedTime(0);
        this.simResultMapper.createResult(simData, "ProductionSimulation wurde pausiert");
        return this.repo.save(simData);
    }


    private SimData startNewSim(SimParadigmas paradigmas) {
        long startTimeNow = System.currentTimeMillis() / 1000;
        SimData simData = new SimData(paradigmas.idFromProductionLine(), startTimeNow, paradigmas.timeFactor());
        simData.setPause(false);
        return this.repo.save(simData);
    }

    private SimData resumeSim(SimParadigmas paradigmas) {
        SimData simData = this.repo.findByRefId(paradigmas.idFromProductionLine()).get();
        simData.setActive(true);
        simData.setPause(false);
        simData.setPausedTime(0);
        simData.setStartedTime(System.currentTimeMillis() / 1000);
        return this.repo.save(simData);
    }

    public String stopSim(long id) {
        Optional<SimData> optional = this.repo.findByRefId(id);
        SimData simData = optional.get();
        this.simResultMapper.createResult(simData, "ProductionSimulation wurde beendet");
        this.repo.delete(simData);
        return null;
    }

    public Optional<Boolean> isThisSimulationRunning(Long id) {
        Optional<SimData> optional = this.repo.findByRefId(id);
        Optional.of(optional.isPresent() && optional.get().isActive());
        return optional.isPresent() ? Optional.of(optional.get().isActive()) : Optional.empty();
    }
}
