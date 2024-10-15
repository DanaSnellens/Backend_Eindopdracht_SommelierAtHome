package nl.novi.eindopdr_danasnellens_sommelierathome.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)   //TODO Of moet dit JOINED zijn?
@DiscriminatorColumn(name = "user_type")  // Optional: You can use this to differentiate types in the table
@Data
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence_21")
    @SequenceGenerator(name = "user_sequence_21", sequenceName = "user_sequence_21", initialValue = 21, allocationSize = 1)
    @Setter(AccessLevel.NONE)
    private Long id;


    @Column(nullable = false, unique = true)
    private String username;
    private String firstName;
    private String lastName;
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    private String profilePictureUrl;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roleSet = new HashSet<>();

    private String getFullName() {return firstName + lastName;}
}
