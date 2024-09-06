package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input;

import nl.novi.eindopdr_danasnellens_sommelierathome.utils.Membership;

public class ClientInputDto {
    public String userName;
    public String firstName;
    public String lastName;
    public String email;
    //TODO moet password wel public zijn? Of uberhaupt in de input dto staan?
    public String password;
    public String profilePictureUrl;
    public Membership membership;

}
