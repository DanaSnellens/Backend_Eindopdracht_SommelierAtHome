package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input;

import lombok.Data;

@Data
public class SommelierInputDto {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    //TODO moet password wel in de input dto staan?
    private String password;
    private String profilePictureUrl;
//    private Set<RoleOutput> roleOutputDtoSet;

    private String sommelierDescription;
    private String certificates;
    private Integer experienceInYears;
    private String curriculumVitae;
    private String specialization;
}
