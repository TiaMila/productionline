package de.volkswagen.f73.produktionline.backend.productionline.step;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

/**
 * @author d1as7bk - Jordan, Andy(SE-A/31)
 */

@Inheritance(strategy = InheritanceType.JOINED)
@Entity(name = "productionstep")
@DiscriminatorColumn(name = "TYPE", discriminatorType = DiscriminatorType.STRING)
public class ProductionStepDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column
    @Enumerated(value = EnumType.STRING)
    @JsonProperty("ProductionElementType")
    private ProductionElementType productionElementType;
    @Column
    private String name;
    @Column
    private String productionStepName;
    @Column
    private long productionTime;

    public ProductionStepDAO() {
    }

    public ProductionStepDAO(long id, String name, String productionStepName, long productionTime, ProductionElementType productionElementType) {

        this.id = id;
        this.name = name;
        this.productionStepName = productionStepName;
        this.productionTime = productionTime;
        this.productionElementType = productionElementType;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductionStepName() {
        return this.productionStepName;
    }

    public void setProductionStepName(String productionStepName) {
        this.productionStepName = productionStepName;
    }

    public long getProductionTime() {
        return this.productionTime;
    }

    public void setProductionTime(long productionTime) {
        this.productionTime = productionTime;
    }

    public ProductionElementType getType() {
        return this.productionElementType;
    }

    public void setProductionElementType(ProductionElementType productionElementType) {
        this.productionElementType = productionElementType;
    }

    public ProductionElementType getProductionElementType() {
        return this.productionElementType;
    }
}
