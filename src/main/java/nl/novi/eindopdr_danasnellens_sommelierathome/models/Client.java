package nl.novi.eindopdr_danasnellens_sommelierathome.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
// @EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "clients")
@Data
public class Client extends User {

    @Enumerated(EnumType.STRING)
    private Membership membership;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private Set<WineAdviceRequest> WineAdviceRequestSet = new HashSet<>();

    //Deze kan (denk ik) weg, want deze relatie verloopt via WineAdviceRequest
    //MSs een aparte outputDTO maken waar ook WA bij zit
/*    @OneToMany(mappedBy = "client")
    private Set<WineAdvice> WineAdviceSet = new HashSet<>();*/
}
