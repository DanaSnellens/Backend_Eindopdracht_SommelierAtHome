package nl.novi.eindopdr_danasnellens_sommelierathome.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
//TODO Klopt @EqualsAndHashCode(callSuper = true) wel?
// Sommelier.java
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sommeliers")
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Sommelier extends User {

    private String sommelierDescription;
    private String certificates;
    private Integer experienceInYears;
    private String curriculumVitae;
    private String specialization;

    @OneToMany(mappedBy = "sommelier", cascade = CascadeType.ALL)
    private Set<WineAdviceRequest> wineAdviceRequestSet = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "sommelier_roles",
            joinColumns = @JoinColumn(name = "sommelier_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @JsonBackReference // Om een infinite loop te voorkomen vanwege de dubbele relatie (clients & sommeliers)
    private Set<Role> roleSet = new HashSet<>();
}