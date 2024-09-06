package nl.novi.eindopdr_danasnellens_sommelierathome.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.io.File;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public abstract class User {

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

    public User() {

    }

    public User(Long id, String userName, String firstName, String lastName, String email, String password, String profilePictureUrl) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.profilePictureUrl = profilePictureUrl;
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
