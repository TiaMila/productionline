package de.volkswagen.f73.produktionline.backend.productionline.step;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * @author d1as7bk - Jordan, Andy(SE-A/31)
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "ProductionElementType",
        defaultImpl = RobotDTO.class
)
@JsonSubTypes({@JsonSubTypes.Type(value = RobotDTO.class, name = "ROBOT")})
public final class RobotDTO extends ProductionStepDTO {

    private long serviceTime;

    public RobotDTO(long id, ProductionElementType productionElementType, String name, String productionStep, long productionTime, long serviceTime) {
        super(id, productionElementType, name, productionStep, productionTime);
        this.serviceTime = serviceTime;
    }

    public long getServiceTime() {
        return this.serviceTime;
    }
}
