package nl.novi.eindopdr_danasnellens_sommelierathome.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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

    private Membership membership;

    @OneToMany(mappedBy = "client")
    private Set<WineAdviceRequest> WineAdviceRequestSet = new HashSet<>();

    //Deze kan (denk ik) weg, want deze relatie verloopt via WineAdviceRequest
    //MSs een aparte outputDTO maken waar ook WA bij zit
/*    @OneToMany(mappedBy = "client")
    private Set<WineAdvice> WineAdviceSet = new HashSet<>();*/
}
