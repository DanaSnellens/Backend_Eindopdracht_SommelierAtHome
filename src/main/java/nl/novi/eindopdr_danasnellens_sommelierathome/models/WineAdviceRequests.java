package nl.novi.eindopdr_danasnellens_sommelierathome.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "WineAdviceRequests")
@Getter
@Setter
public class WineAdviceRequests {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;

    //relaties


}
