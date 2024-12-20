package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class SommelierOutputDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String profilePictureUrl;
    private String profilePictureAlt;
    private String sommelierDescription;
    private String certificates;
    private Integer experienceInYears;
    private String curriculumVitae;
    private String specialization;

    private Set<String> roleNameSet = new HashSet<>();

    private Set<Long> wineAdviceRequestIdSet = new HashSet<>();
    //Kan weg? want deze relatie verloopt via WineAdviceRequest
/*    private Set<Long> wineAdviceIdSet = new HashSet<>();*/
}
