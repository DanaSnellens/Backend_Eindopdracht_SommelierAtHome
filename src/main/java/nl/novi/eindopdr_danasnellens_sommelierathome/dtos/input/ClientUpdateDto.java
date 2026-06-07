package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Membership;
import org.hibernate.validator.constraints.URL;

@Data
public class ClientUpdateDto {
    private String firstName;
    private String lastName;
    private String profilePictureUrl;
    private String profilePictureAlt;
    private Membership membership;
}

