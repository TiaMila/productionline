package de.volkswagen.f73.produktionline.backend.productionline;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

/**
 * @author d1as7bk - Jordan, Andy(SE-A/31)
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class ProductionLineDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @NonNull
    private Long id;
    private String name;
    @JoinColumn(name = "production_line_id", referencedColumnName = "car_model_id ")
    private long carModel;
    private List<Long> listOfId;
}
