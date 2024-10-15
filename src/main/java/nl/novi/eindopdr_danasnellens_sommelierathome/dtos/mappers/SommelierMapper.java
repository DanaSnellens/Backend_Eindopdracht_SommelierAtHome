package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers;

import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.SommelierInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.SommelierOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Sommelier;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.WineAdviceRequest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SommelierMapper {
    //from dto to model
    public static Sommelier sommelierInputDtoToModel(SommelierInputDto sommelierInputDto, String userName) {
        Sommelier sommelier = new Sommelier();
        //UserName krijgen we mee vanuit het object (via security) Zie hw-klas votes (16) 28 min.
        sommelier.setUsername(userName);
        sommelier.setFirstName(sommelierInputDto.getFirstName());
        sommelier.setLastName(sommelierInputDto.getLastName());
        sommelier.setEmail(sommelierInputDto.getEmail());
        sommelier.setPassword(sommelierInputDto.getPassword());
        sommelier.setProfilePictureUrl(sommelierInputDto.getProfilePictureUrl());
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
/*
        sommelierOutputDto.setRoleSet(sommelier.getRoleSet());
*/
        sommelierOutputDto.setSommelierDescription(sommelier.getSommelierDescription());
        sommelierOutputDto.setCertificates(sommelier.getCertificates());
        sommelierOutputDto.setExperienceInYears(sommelier.getExperienceInYears());
        sommelierOutputDto.setCurriculumVitae(sommelier.getCurriculumVitae());
        sommelierOutputDto.setSpecialization(sommelier.getSpecialization());

        //relaties
        //TODO Klpt het dat ik t hier OutputDto noem? of is dat als je ids gebruikt niet nodig?
        if (sommelier.getWineAdviceRequestSet() != null) {
            Set<Long> wineAdviceRequestIdSet = new HashSet<>();

            for (WineAdviceRequest war : sommelier.getWineAdviceRequestSet()) {
                wineAdviceRequestIdSet.add(war.getId());
            }
            sommelierOutputDto.setWineAdviceRequestIdSet(wineAdviceRequestIdSet);
        }
        return sommelierOutputDto;
    }

/*        if (sommelier.getWineAdviceSet() != null) {
            Set<Long> wineAdviceIdSet = new HashSet<>();

            for (WineAdvice wa : sommelier.getWineAdviceSet()) {
                wineAdviceIdSet.add(wa.getId());
            }
            sommelierOutputDto.setWineAdviceIdSet(wineAdviceIdSet);
        }
        return sommelierOutputDto;
    }*/

/*
    public static SommelierOutputDtoShort sommelierOutputDtoShort(Sommelier sommelier) {
        SommelierOutputDtoShort sommelierOutputDtoShort = new SommelierOutputDtoShort();
        sommelierOutputDtoShort.setId(sommelier.getId());
        sommelierOutputDtoShort.setFirstName(sommelier.getUserName());
        sommelierOutputDtoShort.setLastName(sommelier.getLastName());
        sommelierOutputDtoShort.setEmail(sommelier.getEmail());
        sommelierOutputDtoShort.setProfilePictureUrl(sommelier.getProfilePictureUrl());
        sommelierOutputDtoShort.setRoleSet(sommelier.getRoleSet());

*/
    //from list to list
    public static List<SommelierOutputDto> sommelierModelListToOutputList(List<Sommelier> sommelierList) {
        List<SommelierOutputDto> sommelierOutputDtoList = new ArrayList<>();
        sommelierList.forEach( (sommelier) -> sommelierOutputDtoList.add(sommelierModelToOutput(sommelier)));
        return sommelierOutputDtoList;
    }
}
