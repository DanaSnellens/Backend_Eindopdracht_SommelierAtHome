package nl.novi.eindopdr_danasnellens_sommelierathome.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class WineAdvice {
    @Id
    @GeneratedValue
    @Getter(AccessLevel.NONE)               //KLOPT DIT?
    private Long id;

    private String advice;

    private String personalMessage;

}
