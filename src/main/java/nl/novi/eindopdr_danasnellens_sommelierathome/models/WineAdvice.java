package nl.novi.eindopdr_danasnellens_sommelierathome.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.List;

@Entity
@Table(name = "wineAdvices")
@Getter
@Setter
public class WineAdvice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)               //KLOPT DIT?
    private Long id;

    //relaties
        // WineList<Wine>
        // Sommelier
        // Client
        // WineAdviceRequest
        // WineList
    /*    private List<Wine> wines;*/

    private String personalMessage;

    private File adviceExplanation;

    public WineAdvice() {
    }

    public WineAdvice(Long id, String personalMessage, File adviceExplanation) {
        this.id = id;
        this.personalMessage = personalMessage;
        this.adviceExplanation = adviceExplanation;
    }
}
