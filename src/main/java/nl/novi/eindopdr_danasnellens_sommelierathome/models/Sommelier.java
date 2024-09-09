package nl.novi.eindopdr_danasnellens_sommelierathome.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sommeliers")
@Getter
@Setter
public class Sommelier extends User{

    private String sommelierDescription;
    private String certificates;
    private Integer experienceInYears;
    private String curriculumVitae;
    private String specialization;

    public Sommelier() {
    }

    public Sommelier(Long id, String userName, String firstName, String lastName, String email, String password, String profilePictureUrl, String sommelierDescription, String certificates, Integer experienceInYears, String curriculumVitae, String specialization) {
        super(id, userName, firstName, lastName, email, password, profilePictureUrl);
        this.sommelierDescription = sommelierDescription;
        this.certificates = certificates;
        this.experienceInYears = experienceInYears;
        this.curriculumVitae = curriculumVitae;
        this.specialization = specialization;
    }
}
