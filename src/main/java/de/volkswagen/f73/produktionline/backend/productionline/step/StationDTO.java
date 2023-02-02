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
        defaultImpl = StationDTO.class
)
@JsonSubTypes({@JsonSubTypes.Type(value = StationDTO.class, name = "STATION")})
public class StationDTO extends ProductionStepDTO {

    private String[] employees;

    public StationDTO(long id, ProductionElementType productionElementType, String name, String productionStep, long productionTime, String[] employees) {
        super(id, productionElementType, name, productionStep, productionTime);
        this.employees = employees;
    }

    public String[] getEmployees() {
        return this.employees;
    }
}
