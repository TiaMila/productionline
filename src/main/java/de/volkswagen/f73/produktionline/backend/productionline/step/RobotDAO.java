package de.volkswagen.f73.produktionline.backend.productionline.step;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

/**
 * @author d1as7bk - Jordan, Andy(SE-A/31)
 */
@Entity
@Data
@DiscriminatorValue("ROBOT")
public final class RobotDAO extends ProductionStepDAO {

    @Column
    private long serviceTime;

    public RobotDAO() {
    }

    public RobotDAO(long id, String name, String productionStep, long productionTime, ProductionElementType productionElementType, long serviceTime) {
        super(id, name, productionStep, productionTime, productionElementType);
        this.serviceTime = serviceTime;
    }
}


