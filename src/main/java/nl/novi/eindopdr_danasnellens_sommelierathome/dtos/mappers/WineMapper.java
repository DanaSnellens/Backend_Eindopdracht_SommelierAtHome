package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers;

import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.WineInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.WineOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Wine;

import java.util.ArrayList;
import java.util.List;

public class WineMapper {

    //from dto to model

    public static Wine wineInputToModel(WineInputDto wineInputDto) {
        Wine wine = new Wine();
        wine.setWineName(wineInputDto.getWineName());
        wine.setCountry(wineInputDto.getCountry());
        wine.setRegion(wineInputDto.getRegion());

        wine.setGrapeVarietal(wineInputDto.getGrapeVarietal());
        wine.setProducer(wineInputDto.getProducer());
        wine.setWineStyle(wineInputDto.getWineStyle());
        wine.setWineType(wineInputDto.getWineType());
        wine.setFoodPairing(wineInputDto.getFoodPairing());
        wine.setYear(wineInputDto.getYear());
        wine.setPrice(wineInputDto.getPrice());
        wine.setAromas(wineInputDto.getAromas());
        wine.setImageLink(wineInputDto.getImageLink());
        wine.setImageAlt(wineInputDto.getImageAlt());
        wine.setShortDescription(wineInputDto.getShortDescription());
        wine.setLongDescription(wineInputDto.getLongDescription());

        return wine;

    }

    //TODO is dit nodig? (een aparte update-mapper, of kan dit ook via de input-mapper?)
    public static Wine updateWineMapper(Wine savedWine, WineInputDto updatedWineInputDto) {
        savedWine.setWineName(updatedWineInputDto.getWineName());
        savedWine.setCountry(updatedWineInputDto.getCountry());
        savedWine.setRegion(updatedWineInputDto.getRegion());
        savedWine.setGrapeVarietal(updatedWineInputDto.getGrapeVarietal());
        savedWine.setProducer(updatedWineInputDto.getProducer());
        savedWine.setWineStyle(updatedWineInputDto.getWineStyle());
        savedWine.setWineType(updatedWineInputDto.getWineType());
        savedWine.setFoodPairing(updatedWineInputDto.getFoodPairing());
        savedWine.setYear(updatedWineInputDto.getYear());
        savedWine.setPrice(updatedWineInputDto.getPrice());
        savedWine.setAromas(updatedWineInputDto.getAromas());
        savedWine.setImageLink(updatedWineInputDto.getImageLink());
        savedWine.setImageAlt(updatedWineInputDto.getImageAlt());
        savedWine.setShortDescription(updatedWineInputDto.getShortDescription());
        savedWine.setLongDescription(updatedWineInputDto.getLongDescription());

        return savedWine;
    }

    public static WineOutputDto wineModelToOutput(Wine wine) {
        WineOutputDto wineOutputDto = new WineOutputDto();
        wineOutputDto.setId(wine.getId());
        wineOutputDto.setWineName(wine.getWineName());
        wineOutputDto.setCountry(wine.getCountry());
        wineOutputDto.setRegion(wine.getRegion());
        wineOutputDto.setGrapeVarietal(wine.getGrapeVarietal());
        wineOutputDto.setProducer(wine.getProducer());
        wineOutputDto.setWineStyle(wine.getWineStyle());
        wineOutputDto.setWineType(wine.getWineType());
        wineOutputDto.setFoodPairing(wine.getFoodPairing());
        wineOutputDto.setYear(wine.getYear());
        wineOutputDto.setPrice(wine.getPrice());
        wineOutputDto.setAromas(wine.getAromas());
        wineOutputDto.setImageLink(wine.getImageLink());
        wineOutputDto.setImageAlt(wine.getImageAlt());
        wineOutputDto.setShortDescription(wine.getShortDescription());
        wineOutputDto.setLongDescription(wine.getLongDescription());
/*
        if (wine.getWineAdviceSet() != null) {
            Set<Long> wineAdviceIdSet = new HashSet<>();
            for (WineAdvice wa : wine.getWineAdviceSet()) {
                wineAdviceIdOutputDtoSet.add(wa.getId());
            }
            wineOutputDto.setWineAdviceIdSet(wineAdviceIdOutputDtoSet);
        }*/

        //TODO Recipe relatie?

        return wineOutputDto;
    }

    public static List<WineOutputDto> wineModelListToOutputList(List<Wine> wineList) {
        List<WineOutputDto> wineOutputDtoList = new ArrayList<>();
        for (Wine wine : wineList) {
            wineOutputDtoList.add(wineModelToOutput(wine));
        }
        return wineOutputDtoList;
    }
}
