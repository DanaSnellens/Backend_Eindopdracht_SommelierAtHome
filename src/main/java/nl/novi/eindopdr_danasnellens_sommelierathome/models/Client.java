package nl.novi.eindopdr_danasnellens_sommelierathome.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import nl.novi.eindopdr_danasnellens_sommelierathome.utils.Membership;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "clients")
@Getter
@Setter
public class Client extends User {
    //TODO is dit ook een OneToMany relatie?
    private Membership membership;

    //Relaties
        //WineAdviceRequest
    @OneToMany(mappedBy = "client")
    private Set<WineAdviceRequest> WineAdviceRequestSet = new HashSet<>();

        //WineAdvice
    @OneToMany(mappedBy = "client")
    private Set<WineAdvice> WineAdviceSet = new HashSet<>();

    public Client() {
    }

    public Client(Long id, String userName, String firstName, String lastName, String email, String password, String profilePictureUrl, Membership membership) {
        super(id, userName, firstName, lastName, email, password, profilePictureUrl);
        this.membership = membership;
    }
}
