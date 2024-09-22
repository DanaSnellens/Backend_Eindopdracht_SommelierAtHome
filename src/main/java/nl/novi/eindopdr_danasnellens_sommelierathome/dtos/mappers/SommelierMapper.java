package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers;

import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.SommelierInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.SommelierOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.WineAdviceOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.WineAdviceRequestOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Sommelier;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.WineAdvice;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.WineAdviceRequest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers.WineAdviceMapper.wineAdviceModelToOutput;
import static nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers.WineAdviceRequestMapper.wineAdviceRequestModelToOutput;

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
        //Wordt later toegevoegd dmv assign

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
        if (sommelier.getWineAdviceRequestSet() != null) {
            Set<WineAdviceRequestOutputDto> wineAdviceRequestOutputDtoSet = new HashSet<>();

            Set<WineAdviceRequest> wineAdviceRequestSet = sommelier.getWineAdviceRequestSet();
            for (WineAdviceRequest war : wineAdviceRequestSet) {
                wineAdviceRequestOutputDtoSet.add(wineAdviceRequestModelToOutput(war));
            }
            sommelierOutputDto.setWineAdviceRequestOutputDtoSet(wineAdviceRequestOutputDtoSet);
        }

        if (sommelier.getWineAdviceSet() != null) {
            Set<WineAdviceOutputDto> wineAdviceOutputDtoSet = new HashSet<>();

            Set<WineAdvice> wineAdviceSet = sommelier.getWineAdviceSet();
            for (WineAdvice wa : wineAdviceSet) {
                wineAdviceOutputDtoSet.add(wineAdviceModelToOutput(wa));
            }
            sommelierOutputDto.setWineAdviceOutputDtoSet(wineAdviceOutputDtoSet);
        }
        return sommelierOutputDto;
    }

    //from list to list
    public static List<SommelierOutputDto> sommelierModelListToOutputList(List<Sommelier> sommelierList) {
        List<SommelierOutputDto> sommelierOutputDtoList = new ArrayList<>();
        //for loop
        for (Sommelier s : sommelierList) {
            sommelierOutputDtoList.add(sommelierModelToOutput(s));
        }
        return sommelierOutputDtoList;
    }
}
