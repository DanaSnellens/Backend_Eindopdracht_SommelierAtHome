package nl.novi.eindopdr_danasnellens_sommelierathome.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

// @EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sommeliers")
@Data
public class Sommelier extends User{

    private String sommelierDescription;
    private String certificates;
    private Integer experienceInYears;
    private String curriculumVitae;
    private String specialization;

    @OneToMany(mappedBy = "sommelier")
    private Set<WineAdviceRequest> WineAdviceRequestSet = new HashSet<>();

    @OneToMany(mappedBy = "sommelier")
    private Set<WineAdvice> WineAdviceSet = new HashSet<>();
}
