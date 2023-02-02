package de.volkswagen.f73.produktionline.backend.simulation.simresult;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author d1as7bk - Jordan, Andy(SE-A/31)
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class SimResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long refId;
    private long wholeProductionTime;
    private long quantity;
    private String message;


}
