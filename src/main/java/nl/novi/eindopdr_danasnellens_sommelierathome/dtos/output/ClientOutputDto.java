package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output;

import jakarta.persistence.Column;
import lombok.Data;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Membership;

import java.util.HashSet;
import java.util.Set;

@Data
public class ClientOutputDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String profilePictureUrl;
    private String profilePictureAlt;
    //TODO RolenameSet of ID. Waarom String unused
    private Set<String> roleNameSet = new HashSet<>();
    private Membership membership;
    private Set<Long> WineAdviceRequestIdSet = new HashSet<>();
}
