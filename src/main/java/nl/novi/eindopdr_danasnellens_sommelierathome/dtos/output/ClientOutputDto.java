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
    @Column(nullable = true)
    private Set<String> roleNameSet = new HashSet<String>();

    @Column(nullable = true)
    private Membership membership;

    @Column(nullable = true)
    private Set<Long> WineAdviceRequestIdSet = new HashSet<>();
}
