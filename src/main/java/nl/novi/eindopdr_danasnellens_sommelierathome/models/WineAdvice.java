package nl.novi.eindopdr_danasnellens_sommelierathome.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.List;

@Entity
@Table(name = "WineAdvices")
@Getter
@Setter
public class WineAdvice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter(AccessLevel.NONE)               //KLOPT DIT?
    private Long id;

    //relaties
        // WineList<Wine>
        // Sommelier
        // Client
        // WineAdviceRequest
        // WineList

    private String personalMessage;
/*    private List<Wine> wines;*/
    private File adviceExplanation;
}
