package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers;

import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.SommelierInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.SommelierOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Role;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Sommelier;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.WineAdviceRequest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SommelierMapper {

    public static Sommelier sommelierInputDtoToModel(SommelierInputDto sommelierInputDto) {
        Sommelier sommelier = new Sommelier();
        //TODO UserName krijgen we mee vanuit het object (via security) Zie hw-klas votes (16) 28 min.
        sommelier.setUsername(sommelierInputDto.getUsername());
        sommelier.setFirstName(sommelierInputDto.getFirstName());
        sommelier.setLastName(sommelierInputDto.getLastName());
        sommelier.setEmail(sommelierInputDto.getEmail());
        sommelier.setPassword(sommelierInputDto.getPassword());
        sommelier.setProfilePictureUrl(sommelierInputDto.getProfilePictureUrl());
        sommelier.setProfilePictureAlt(sommelierInputDto.getProfilePictureAlt());
//        sommelier.setRoleSet(sommelierInputDto.getRoleSet());
        sommelier.setSommelierDescription(sommelierInputDto.getSommelierDescription());
        sommelier.setCertificates(sommelierInputDto.getCertificates());
        sommelier.setExperienceInYears(sommelierInputDto.getExperienceInYears());
        sommelier.setCurriculumVitae(sommelierInputDto.getCurriculumVitae());
        sommelier.setSpecialization(sommelierInputDto.getSpecialization());

        return sommelier;
    }

    //from model to dto
    public static SommelierOutputDto sommelierModelToOutput(Sommelier sommelier) {
        SommelierOutputDto sommelierOutputDto = new SommelierOutputDto();
        sommelierOutputDto.setId(sommelier.getId());
        sommelierOutputDto.setUsername(sommelier.getUsername());
        sommelierOutputDto.setFirstName(sommelier.getFirstName());
        sommelierOutputDto.setLastName(sommelier.getLastName());
        sommelierOutputDto.setEmail(sommelier.getEmail());
        sommelierOutputDto.setProfilePictureUrl(sommelier.getProfilePictureUrl());
        sommelierOutputDto.setProfilePictureAlt(sommelier.getProfilePictureAlt());

        sommelierOutputDto.setSommelierDescription(sommelier.getSommelierDescription());
        sommelierOutputDto.setCertificates(sommelier.getCertificates());
        sommelierOutputDto.setExperienceInYears(sommelier.getExperienceInYears());
        sommelierOutputDto.setCurriculumVitae(sommelier.getCurriculumVitae());
        sommelierOutputDto.setSpecialization(sommelier.getSpecialization());

        if (sommelier.getRoleSet() != null) {
            Set<String> roleNameSet = new HashSet<>();
            for (Role r : sommelier.getRoleSet()) {
                roleNameSet.add(r.getRoleName());
            }
            sommelierOutputDto.setRoleNameSet(roleNameSet);
        }

        if (sommelier.getWineAdviceRequestSet() != null) {
            Set<Long> wineAdviceRequestIdSet = new HashSet<>();

            for (WineAdviceRequest war : sommelier.getWineAdviceRequestSet()) {
                wineAdviceRequestIdSet.add(war.getId());
            }
            sommelierOutputDto.setWineAdviceRequestIdSet(wineAdviceRequestIdSet);
        }
        return sommelierOutputDto;
    }

    public static List<SommelierOutputDto> sommelierModelListToOutputList(List<Sommelier> sommelierList) {
        List<SommelierOutputDto> sommelierOutputDtoList = new ArrayList<>();
        sommelierList.forEach( (sommelier) -> sommelierOutputDtoList.add(sommelierModelToOutput(sommelier)));
        return sommelierOutputDtoList;
    }
}
