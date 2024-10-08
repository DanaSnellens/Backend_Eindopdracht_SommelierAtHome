package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers;

import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.SommelierInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.SommelierOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.SommelierOutputDtoShort;
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
        sommelierOutputDto.setUserName(sommelier.getUserName());
        sommelierOutputDto.setFirstName(sommelier.getFirstName());
        sommelierOutputDto.setLastName(sommelier.getLastName());
        sommelierOutputDto.setEmail(sommelier.getEmail());
        sommelierOutputDto.setProfilePictureUrl(sommelier.getProfilePictureUrl());
        sommelierOutputDto.setRoleSet(sommelier.getRoleSet());
        sommelierOutputDto.setSommelierDescription(sommelier.getSommelierDescription());
        sommelierOutputDto.setCertificates(sommelier.getCertificates());
        sommelierOutputDto.setExperienceInYears(sommelier.getExperienceInYears());
        sommelierOutputDto.setCurriculumVitae(sommelier.getCurriculumVitae());
        sommelierOutputDto.setSpecialization(sommelier.getSpecialization());

        //relaties
        //TODO Klpt het dat ik t hier OutputDto noem? of is dat als je ids gebruikt niet nodig?
        if (sommelier.getWineAdviceRequestSet() != null) {
            Set<Long> wineAdviceRequestIdOutputDtoSet = new HashSet<>();

            for (WineAdviceRequest war : sommelier.getWineAdviceRequestSet()) {
                wineAdviceRequestIdOutputDtoSet.add(war.getId());
            }
            sommelierOutputDto.setWineAdviceRequestIdOutputDtoSet(wineAdviceRequestIdOutputDtoSet);
        }

        if (sommelier.getWineAdviceSet() != null) {
            Set<Long> wineAdviceIdOutputDtoSet = new HashSet<>();

            for (WineAdvice wa : sommelier.getWineAdviceSet()) {
                wineAdviceIdOutputDtoSet.add(wa.getId());
            }
            sommelierOutputDto.setWineAdviceIdOutputDtoSet(wineAdviceIdOutputDtoSet);
        }
        return sommelierOutputDto;
    }

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

        for (Sommelier s : sommelierList) {
            sommelierOutputDtoList.add(sommelierModelToOutput(s));
        }
        return sommelierOutputDtoList;
    }
}
