package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input;

import lombok.Data;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.*;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

@Data
public class WineAdviceInputDto {
    private String personalMessage;
    private String adviceExplanation;

    private Set<Long> wineIdSet = new HashSet<>();

    private Long wineAdviceRequestId;
}
