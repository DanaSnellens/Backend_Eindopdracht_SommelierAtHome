package nl.novi.eindopdr_danasnellens_sommelierathome.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "wineadvices")
@Data
public class WineAdvice {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wa_sequence_4021")
    @SequenceGenerator(name = "wa_sequence_4021", sequenceName = "wa_sequence_4021", initialValue = 4021, allocationSize = 1)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String personalMessage;
    private String adviceExplanation;

    //TODO Welke cascadeType? All is rigoreus, maar welke wel?Ook toevoegen/wijzigen bij anderen
    // Volgens mij zei Mark dat joinColumns & inversecolums omgedraaid moeten worden (wine_id en wine_advice), maar toch teruggedraaid, omdat code niet werkte en dit als oplossing werd aangedragen
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "wineadvices_wines",
            joinColumns = @JoinColumn(name = "wineadvice_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "wine_id", referencedColumnName = "id"))

    private Set<Wine> wineSet = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "wineadvicerequest_id")
    private WineAdviceRequest wineAdviceRequest;
}
