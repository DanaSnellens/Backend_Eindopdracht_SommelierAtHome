package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output;

import lombok.Data;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.WineAdvice;

import java.util.HashSet;
import java.util.Set;

@Data
public class WineOutputDto {
    private Long id;
    private String wineName;
    private String country;
    private String region;
    private String grapeVarietal;
    private String producer;
    private String wineStyle;
    private String wineType;
    private String foodPairing;
    private Integer year;
    private Double price;
    private String aromas;
    private String imageLink;
    private String imageAlt;
    private String shortDescription;
    private String longDescription;

/*    private Set<Long> wineAdviceIdSet = new HashSet<>();*/
    private Set<Long> recipeIdSet = new HashSet<>();
}


