package nl.novi.eindopdr_danasnellens_sommelierathome.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import java.awt.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
public abstract class User implements UserDetails  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;


    @Column(nullable = false, unique = true)
    private String userName;
    private String firstName;
    private String lastName;
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    private String profilePictureUrl;

    //Klopt onderstaande? Ivm enum geen manytomany annotatie?
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roleSet = new HashSet<>();


    public User() {
    }

    public User(Long id, String userName, String firstName, String lastName, String email, String password, String profilePictureUrl, Set<Role> roleSet) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.profilePictureUrl = profilePictureUrl;
        this.roleSet = roleSet;
    }


    private String getFullName() {return firstName + lastName;}

    //relaties
//Onderstaande gekopieerd uit TechItEasyRowan
/*    @OneToMany(
            targetEntity = Authority.class,
            mappedBy = "username",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    private Set<Authority> authorities = new HashSet<>();*/
}
