package nl.novi.eindopdr_danasnellens_sommelierathome.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.io.File;

@Entity
@Table(name = "wineAdviceRequests")
@Data
public class WineAdviceRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String dinnerOccasion;
    private String requestMessage;
    private String recipeLink;
    private Double minPricePerBottle;
    private Double maxPricePerBottle;

    //relaties

    //client
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    //sommelier
    @ManyToOne
    @JoinColumn(name = "sommelier_id)")
    private Sommelier sommelier;

    //wineAdvice
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "wineAdvice_id")
    private WineAdvice wineAdvice;
}
