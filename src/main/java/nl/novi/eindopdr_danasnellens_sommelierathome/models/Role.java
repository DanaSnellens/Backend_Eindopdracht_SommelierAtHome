package nl.novi.eindopdr_danasnellens_sommelierathome.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "roles")
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roles_sequence_9021")
    @SequenceGenerator(name = "roles_sequence_9021", sequenceName = "roles_sequence_9021", initialValue = 9021, allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String roleName;

/*    @ManyToMany(mappedBy = "roleSet")
    @JsonIgnore
    private Set<User> userSet = new HashSet<>();*/

    @ManyToMany(mappedBy = "roleSet")
    @JsonManagedReference // Om een infinite loop te voorkomen vanwege de dubbele relatie (clients & sommeliers)
    private Set<Sommelier> sommeliers = new HashSet<>();

    @ManyToMany(mappedBy = "roleSet")
    @JsonManagedReference // Om een infinite loop te voorkomen vanwege de dubbele relatie (clients & sommeliers)
    private Set<Client> clients = new HashSet<>();
}
