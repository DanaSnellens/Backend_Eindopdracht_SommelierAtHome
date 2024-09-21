package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output;

import lombok.Data;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.WineAdvice;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.WineAdviceRequest;
import nl.novi.eindopdr_danasnellens_sommelierathome.utils.Membership;

import java.util.Set;

@Data
public class ClientOutputDto {
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    //TODO moet password wel in de output dto staan?
    private String password;
    private String profilePictureUrl;

    private Membership membership;

    //relaties
    //TODO: klopt het dat ik hier OutputDtos meegeef?
    private Set<WineAdviceRequestOutputDto> WineAdviceRequestOutputDtoSet;
    private Set<WineAdviceOutputDto> WineAdviceOutputDtoSet;
}
