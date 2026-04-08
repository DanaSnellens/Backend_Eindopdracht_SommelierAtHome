package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output;

import lombok.Builder;
import lombok.Data;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.WineAdvice;

import java.util.*;

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


    //TODO onderstaande verwijderen?
    /*    private Set<Long> wineAdviceIdSet = new HashSet<>();*/
    private Map<Long, String> recipeIdSet = new HashMap<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WineOutputDto that = (WineOutputDto) o;
        return Objects.equals(id, that.id) && Objects.equals(wineName, that.wineName) && Objects.equals(country, that.country) && Objects.equals(region, that.region) && Objects.equals(grapeVarietal, that.grapeVarietal) && Objects.equals(producer, that.producer) && Objects.equals(wineStyle, that.wineStyle) && Objects.equals(wineType, that.wineType) && Objects.equals(foodPairing, that.foodPairing) && Objects.equals(year, that.year) && Objects.equals(price, that.price) && Objects.equals(aromas, that.aromas) && Objects.equals(imageLink, that.imageLink) && Objects.equals(imageAlt, that.imageAlt) && Objects.equals(shortDescription, that.shortDescription) && Objects.equals(longDescription, that.longDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, wineName, country, region, grapeVarietal, producer, wineStyle, wineType, foodPairing, year, price, aromas, imageLink, imageAlt, shortDescription, longDescription);
    }
}


