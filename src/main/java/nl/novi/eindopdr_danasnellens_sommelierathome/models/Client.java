package nl.novi.eindopdr_danasnellens_sommelierathome.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "clients")
@Getter
@Setter
public class Client extends User {

    //TODO: moet de enum hier of in een aparte enummeration en waar dan (model? en dan ook in de andere lagen?)? Hoe hier noteren? https://www.baeldung.com/spring-boot-enum-mapping


}
