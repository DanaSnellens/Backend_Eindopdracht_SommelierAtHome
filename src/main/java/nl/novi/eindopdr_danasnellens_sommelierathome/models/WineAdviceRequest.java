package nl.novi.eindopdr_danasnellens_sommelierathome.models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.io.File;

import lombok.Data;

@Entity
@Table(name = "wineadvicerequests")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class WineAdviceRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "war_sequence_3021")
    @SequenceGenerator(name = "war_sequence_3021", sequenceName = "war_sequence_3021", initialValue = 3021, allocationSize = 1)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String dinnerOccasion;
    private String requestMessage;
    private String recipeLink;
    private File recipeFile;
    private Double minPricePerBottle;
    private Double maxPricePerBottle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sommelier_id")
    private Sommelier sommelier;

    @OneToOne(mappedBy = "wineAdviceRequest", cascade = CascadeType.ALL, orphanRemoval = true)
    private WineAdvice wineAdvice;
}
