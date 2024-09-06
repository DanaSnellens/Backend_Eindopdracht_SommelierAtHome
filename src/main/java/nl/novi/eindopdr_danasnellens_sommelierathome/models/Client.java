package nl.novi.eindopdr_danasnellens_sommelierathome.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import nl.novi.eindopdr_danasnellens_sommelierathome.utils.Membership;

@Entity
@Table(name = "clients")
@Getter
@Setter
public class Client extends User {
    private Membership membership;

    public Client() {

    }

    public Client(Long id, String userName, String firstName, String lastName, String email, String password, String profilePictureUrl, Membership membership) {
        super(id, userName, firstName, lastName, email, password, profilePictureUrl);
        this.membership = membership;
    }



}
