package de.volkswagen.f73.produktionline.backend.productionline.step;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

/**
 * @author d1as7bk - Jordan, Andy(SE-A/31)
 */


@Data
@Entity
@DiscriminatorValue("STATION")
public final class StationDAO extends ProductionStepDAO {

    @Column
    private String[] employees;

    public StationDAO() {
    }

    public StationDAO(long id, String name, String productionStep, long productionTime, ProductionElementType productionElementType, String[] employees) {
        super(id, name, productionStep, productionTime, productionElementType);
        this.employees = employees;
    }
}
