package de.volkswagen.f73.produktionline.backend.simulation.simdata;

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
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class SimData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long refId;
    private boolean isActive;
    private boolean isPause;
    private long turnBreakTime;
    private long startedTime;
    private long pausedTime;
    private long totalTime;
    private float timeModifier;

    public SimData(long id, long startedTime, float timeModifier) {
        this.refId = id;
        this.isActive = true;
        this.startedTime = startedTime;
        this.timeModifier = timeModifier;
    }
}
