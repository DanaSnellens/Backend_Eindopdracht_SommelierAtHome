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
    private String profilePictureUrl;

    private Membership membership;

    //relaties
    //TODO: klopt het dat ik hier OutputDtos meegeef?
    private Set<WineAdviceRequestOutputDto> wineAdviceRequestOutputDtoSet;
    private Set<WineAdviceOutputDto> wineAdviceOutputDtoSet;

    //TODO Nested DTOs for related entities (checken of dit nodig is, advies chatgpt)
 /*   public static class WineAdviceRequestOutputDto {
        private Long id;
        private String requestDetails; // Simplified view of the WineAdviceRequest entity

    }

    public static class WineAdviceOutputDto {
        private Long id;
        private String adviceDetails; // Simplified view of the WineAdvice entity

    }*/
}
