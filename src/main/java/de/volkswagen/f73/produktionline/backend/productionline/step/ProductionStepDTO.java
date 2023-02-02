package de.volkswagen.f73.produktionline.backend.productionline.step;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * @author d1as7bk - Jordan, Andy(SE-A/31)
 */
@JsonTypeInfo(
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "productionElementType",
        use = JsonTypeInfo.Id.NAME,
        visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = RobotDTO.class, name = "ROBOT"),
        @JsonSubTypes.Type(value = StationDTO.class, name = "STATION")
})
public abstract class ProductionStepDTO {
    final private long id;

    final private ProductionElementType productionElementType;

    final private String name;

    final private String productionStepName;

    final private long productionTime;

    public ProductionStepDTO(long id, ProductionElementType productionElementType, String name, String productionStepName, long productionTime) {
        this.id = id;
        this.productionElementType = productionElementType;
        this.name = name;
        this.productionStepName = productionStepName;
        this.productionTime = productionTime;
    }

    public long getId() {
        return this.id;
    }

    public ProductionElementType getProductionElementType() {
        return this.productionElementType;
    }

    public String getName() {
        return this.name;
    }

    public String getProductionStepName() {
        return this.productionStepName;
    }

    public long getProductionTime() {
        return this.productionTime;
    }
}
