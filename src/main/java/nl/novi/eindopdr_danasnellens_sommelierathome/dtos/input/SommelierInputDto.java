package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input;

public class SommelierInputDto {
    public String userName;
    public String firstName;
    public String lastName;
    public String email;
    //TODO moet password wel public zijn? Of uberhaupt in de input dto staan?
    public String password;
    public String profilePictureUrl;

    public String sommelierDescription;
    public String certificates;
    public Integer experienceInYears;
    public String curriculumVitae;
    public String specialization;
}
