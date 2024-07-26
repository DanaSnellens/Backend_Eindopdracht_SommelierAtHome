package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input;

import lombok.Data;

@Data
public class WineInputDto {

    private String wineName;
    private String country;
    private String region;
    private String grapeVarietal;
    private String producer;
    private String wineStyle;
    private String wineType;
    private String foodPairing;
    private String year;
    private Double price;
    private String aromas;
    private String imageLink;
    private String imageAlt;
    private String shortDescription;
    private String longDescription;
}
