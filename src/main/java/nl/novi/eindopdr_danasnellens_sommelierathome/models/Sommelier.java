package nl.novi.eindopdr_danasnellens_sommelierathome.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
//TODO Klopt @EqualsAndHashCode(callSuper = true) wel?
// Sommelier.java
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sommeliers")
@Getter
@Setter
@NoArgsConstructor
@ToString
/*@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")*/
public class Sommelier extends User {

    private String sommelierDescription;
    private String certificates;
    private Integer experienceInYears;
    private String curriculumVitae;
    private String specialization;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sommelier", cascade = CascadeType.ALL)
    private Set<WineAdviceRequest> wineAdviceRequestSet = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "sommelier_roles",
            joinColumns = @JoinColumn(name = "sommelier_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roleSet = new HashSet<>();

    public void addRole(Role role) {this.roleSet.add(role);}
    public void removeRole(Role role) {this.roleSet.remove(role);}
}