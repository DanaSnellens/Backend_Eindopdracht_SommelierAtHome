package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output;

import lombok.Data;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Role;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.WineAdvice;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.WineAdviceRequest;

import java.util.HashSet;
import java.util.Set;

@Data
public class SommelierOutputDto {
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String profilePictureUrl;
    private String sommelierDescription;
    private String certificates;
    private Integer experienceInYears;
    private String curriculumVitae;
    private String specialization;

    private Set<RoleOutputDto> roleOutputDtoSet = new HashSet<>();



    //relaties
    private Set<Long> wineAdviceRequestIdSet = new HashSet<>();
    private Set<Long> wineAdviceIdSet = new HashSet<>();
}
