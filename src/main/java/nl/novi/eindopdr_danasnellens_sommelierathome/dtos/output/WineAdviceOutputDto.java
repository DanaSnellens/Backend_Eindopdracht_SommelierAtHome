package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output;

import lombok.Data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
public class  WineAdviceOutputDto {
    private Long id;
    private String personalMessage;
    private String adviceExplanation;

    private Map<Long, String> wineIdSet = new HashMap<>();

    private Long wineAdviceRequestId;
    //private Long sommelierId; // Hoeft niet meer gekoppeld te worden, want de sommelier is al gekoppeld aan de wineAdviceRequest

}
