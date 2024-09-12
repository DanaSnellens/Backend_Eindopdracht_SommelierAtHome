package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers;

import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.SommelierInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.SommelierOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Sommelier;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.WineAdvice;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.WineAdviceRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SommelierMapper {
    //from dto to model
    public static Sommelier sommelierInputDtoToModel(SommelierInputDto sommelierInputDto, String userName) {
        Sommelier sommelier = new Sommelier();
        //UserName krijgen we mee vanuit het object (via security) Zie hw-klas votes (16) 28 min.
        sommelier.setUserName(userName);
        sommelier.setFirstName(sommelierInputDto.getFirstName());
        sommelier.setLastName(sommelierInputDto.getLastName());
        sommelier.setEmail(sommelierInputDto.getEmail());
        sommelier.setPassword(sommelierInputDto.getPassword());
        sommelier.setProfilePictureUrl(sommelierInputDto.getProfilePictureUrl());
        sommelier.setSommelierDescription(sommelierInputDto.getSommelierDescription());
        sommelier.setCertificates(sommelierInputDto.getCertificates());
        sommelier.setExperienceInYears(sommelierInputDto.getExperienceInYears());
        sommelier.setCurriculumVitae(sommelierInputDto.getCurriculumVitae());
        sommelier.setSpecialization(sommelierInputDto.getSpecialization());

        //relaties
        sommelier.setWineAdviceRequestSet(sommelierInputDto.getWineAdviceRequestSet());
        sommelier.setWineAdviceSet(sommelierInputDto.getWineAdviceSet());

        return sommelier;
    }

    //from model to dto
    public static SommelierOutputDto sommelierModelToOutput(Sommelier sommelier) {
        SommelierOutputDto sommelierOutputDto = new SommelierOutputDto();
        sommelierOutputDto.setId(sommelier.getId());
        sommelierOutputDto.setUserName(sommelier.getUserName());
        sommelierOutputDto.setFirstName(sommelier.getFirstName());
        sommelierOutputDto.setLastName(sommelier.getLastName());
        sommelierOutputDto.setEmail(sommelier.getEmail());
        sommelierOutputDto.setPassword(sommelier.getPassword());
        sommelierOutputDto.setProfilePictureUrl(sommelier.getProfilePictureUrl());
        sommelierOutputDto.setSommelierDescription(sommelier.getSommelierDescription());
        sommelierOutputDto.setCertificates(sommelier.getCertificates());
        sommelierOutputDto.setExperienceInYears(sommelier.getExperienceInYears());
        sommelierOutputDto.setCurriculumVitae(sommelier.getCurriculumVitae());
        sommelierOutputDto.setSpecialization(sommelier.getSpecialization());

        //relaties
        sommelierOutputDto.setWineAdviceRequestSet(sommelier.getWineAdviceRequestSet());
        sommelierOutputDto.setWineAdviceSet(sommelier.getWineAdviceSet());

        return sommelierOutputDto;
    }

    //from list to list
    public static List<SommelierOutputDto> sommelierModelListToOutputList(List<Sommelier> sommelierList) {
        List<SommelierOutputDto> sommelierOutputDtoList = new ArrayList<>();
        //for loop
        for (Sommelier s : sommelierList) {
            sommelierOutputDtoList.add(sommelierModelToOutput(s));
        }
        // lambda
        // sommelierList.forEach(s -> sommelierOutputDtoList.add(sommelierFromModelToOutput(s)));
        return sommelierOutputDtoList;
    }
}
