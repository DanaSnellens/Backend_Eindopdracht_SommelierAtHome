package nl.novi.eindopdr_danasnellens_sommelierathome.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Entity
@Table(name = "Wines")
@Getter
@Setter
public class Wine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Long id;

    //relaties

    private String name;
    private String country;
    private String region;
    private String grapeVarietal;
    private String producer;
    private String wineStyle;
    private String wineType;
    private String foodPairing;
    private String year;
    private Double price;                        //toevoegen aan frontend?
    private String aromas;
    private String imageLink;
    private String imageAlt;
    private String shortDescription;
    private String longDescription;

    public Wine() {
    }

    public Wine(Long id, String name, String country, String region, String grapeVarietal, String producer, String wineStyle, String wineType, String foodPairing, String year, Double price, String aromas, String imageLink, String imageAlt, String shortDescription, String longDescription) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.region = region;
        this.grapeVarietal = grapeVarietal;
        this.producer = producer;
        this.wineStyle = wineStyle;
        this.wineType = wineType;
        this.foodPairing = foodPairing;
        this.year = year;
        this.price = price;
        this.aromas = aromas;
        this.imageLink = imageLink;
        this.imageAlt = imageAlt;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
    }
}
