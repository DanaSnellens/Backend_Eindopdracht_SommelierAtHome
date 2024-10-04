package nl.novi.eindopdr_danasnellens_sommelierathome.dtos.mappers;

import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.WineInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.WineOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Recipe;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Wine;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.WineAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class WineMapper {

    //from dto to model

    public static Wine wineInputDtoToModel(WineInputDto wineInputDto) {
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

        //relaties
/*        wine.setWineAdviceSet(wineInputDto.getWineAdviceSet());
        wine.setRecipeSet(wineInputDto.getRecipeSet());*/

        return wine;

    }
    //from model to dto
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

        //TODO relaties
/*        wineOutputDto.setWineAdviceSet(wine.getWineAdviceSet());
        wineOutputDto.setRecipeSet(wine.getRecipeSet());*/

        return wineOutputDto;
    }

    //from list to list
    public static List<WineOutputDto> wineModelListToOutputList(List<Wine> wineList) {
        List<WineOutputDto> wineOutputDtoList = new ArrayList<>();
        for (Wine wine : wineList) {
            wineOutputDtoList.add(wineModelToOutput(wine));
        }
        return wineOutputDtoList;
    }
}
