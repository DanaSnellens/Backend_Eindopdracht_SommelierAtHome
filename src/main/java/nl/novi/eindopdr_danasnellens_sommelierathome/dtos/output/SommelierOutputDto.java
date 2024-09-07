package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output;

import lombok.Data;

@Data
public class SommelierOutputDto {
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    //TODO moet password wel in de output dto staan?
    private String password;
    private String profilePictureUrl;

    private String sommelierDescription;
    private String certificates;
    private Integer experienceInYears;
    private String curriculumVitae;
    private String specialization;
}
