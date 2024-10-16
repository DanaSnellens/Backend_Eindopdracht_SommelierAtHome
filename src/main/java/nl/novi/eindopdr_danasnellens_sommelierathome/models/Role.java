package nl.novi.eindopdr_danasnellens_sommelierathome.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "roles")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roles_sequence_9021")
    @SequenceGenerator(name = "roles_sequence_9021", sequenceName = "roles_sequence_9021", initialValue = 9021, allocationSize = 1)
    private Long id;
    @Column(nullable = false)
    private String roleName;

    @ManyToMany(mappedBy = "roleSet")
    private Set<User> userSet = new HashSet<>();
}
