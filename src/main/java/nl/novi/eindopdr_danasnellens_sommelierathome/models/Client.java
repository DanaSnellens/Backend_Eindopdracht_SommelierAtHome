package nl.novi.eindopdr_danasnellens_sommelierathome.models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Client extends User {
    @Enumerated(EnumType.STRING)
    private Membership membership;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private Set<WineAdviceRequest> wineAdviceRequestSet = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "client_roles",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roleSet = new HashSet<>();
}
