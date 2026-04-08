package nl.novi.eindopdr_danasnellens_sommelierathome.services;

import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.WineInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.WineOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.exceptions.EntityAlreadyExistsException;
import nl.novi.eindopdr_danasnellens_sommelierathome.exceptions.RecordNotFoundException;
import nl.novi.eindopdr_danasnellens_sommelierathome.models.Wine;
import nl.novi.eindopdr_danasnellens_sommelierathome.repositories.WineRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WineServiceTest {

    @Mock
    private WineRepository wineRepository;

    @InjectMocks
    private WineService wineService;

    private Wine wine;
    private WineInputDto wineInputDto;
/*    private WineOutputDto wineOutputDto;*/

    @BeforeEach
    void setUp() {
        wine = Wine.builder()
                .id(1L)
                .wineName("Awesome Chardonnay")
                .country("France")
                .region("Burgundy")
                .grapeVarietal("Chardonnay")
                .producer("Chateau de Beaune")
                .wineStyle("Dry")
                .wineType("White")
                .foodPairing("Seafood, Poultry, Soft cheeses")
                .year(2020)
                .price(19.99)
                .aromas("Green apple, Citrus, Butter")
                .imageLink("https://example.com/images/awesome-chardonnay.jpg")
                .imageAlt("Image of Awesome Chardonnay wine bottle")
                .shortDescription("A crisp and refreshing white wine with vibrant acidity and a touch of oak.")
                .longDescription("The Awesome Chardonnay from Chateau de Beaune is a classic expression of the Chardonnay grape, showcasing the unique terroir of Burgundy. With its bright acidity and balanced oak influence, this wine offers a delightful combination of green apple, citrus, and buttery notes. Perfect for pairing with seafood, poultry, or soft cheeses, it's an excellent choice for any occasion.")
                .build();

        wineInputDto = WineInputDto.builder()
                .wineName("Awesome Chardonnay")
                .country("France")
                .region("Burgundy")
                .grapeVarietal("Chardonnay")
                .producer("Chateau de Beaune")
                .wineStyle("Dry")
                .wineType("White")
                .foodPairing("Seafood, Poultry, Soft cheeses")
                .year(2020)
                .price(19.99)
                .aromas("Green apple, Citrus, Butter")
                .imageLink("https://example.com/images/awesome-chardonnay.jpg")
                .imageAlt("Image of Awesome Chardonnay wine bottle")
                .shortDescription("A crisp and refreshing white wine with vibrant acidity and a touch of oak.")
                .longDescription("The Awesome Chardonnay from Chateau de Beaune is a classic expression of the Chardonnay grape, showcasing the unique terroir of Burgundy. With its bright acidity and balanced oak influence, this wine offers a delightful combination of green apple, citrus, and buttery notes. Perfect for pairing with seafood, poultry, or soft cheeses, it's an excellent choice for any occasion.")
                .year(2020)
                .build();

/*        wineOutputDto = WineOutputDto.builder()
                .id(1L)
                .wineName("Awesome Chardonnay")
                .country("France")
                .region("Burgundy")
                .grapeVarietal("Chardonnay")
                .producer("Chateau de Beaune")
                .wineStyle("Dry")
                .wineType("White")
                .foodPairing("Seafood, Poultry, Soft cheeses")
                .year(2020)
                .price(19.99)
                .aromas("Green apple, Citrus, Butter")
                .imageLink("https://example.com/images/awesome-chardonnay.jpg")
                .imageAlt("Image of Awesome Chardonnay wine bottle")
                .shortDescription("A crisp and refreshing white wine with vibrant acidity and a touch of oak.")
                .longDescription("The Awesome Chardonnay from Chateau de Beaune is a classic expression of the Chardonnay grape, showcasing the unique terroir of Burgundy. With its bright acidity and balanced oak influence, this wine offers a delightful combination of green apple, citrus, and buttery notes. Perfect for pairing with seafood, poultry, or soft cheeses, it's an excellent choice for any occasion.")
                .build();         */
    }

    @AfterEach
    void tearDown() {
        wine = null;
        wineInputDto = null;
/*        wineOutputDto = null;*/
    }

    //GET ALL
    @Test
    @DisplayName("Should return list of wines")
    void testGetAllWines_ShouldReturnListOfWines() {
        //arrange
        when(wineRepository.findAll()).thenReturn(List.of(wine));

        //Act
        List<WineOutputDto> wineDtoList = wineService.getAllWines();

        //Assert
        assertEquals(1, wineDtoList.size());
        assertEquals(1L, wineDtoList.getFirst().getId());
        assertEquals("Awesome Chardonnay", wineDtoList.getFirst().getWineName());
        verify(wineRepository, times(1)).findAll();
    }

    //GET BY ID
    @Test
    @DisplayName("Should return wineOutputDto by ID")
    void testGetWineById_ShouldReturnOutputDto() {
        //arrange
        when(wineRepository.findById(anyLong())).thenReturn(Optional.of(wine));

        //Act
        WineOutputDto wineOutputDto = wineService.getWineById(1L);


        //Assert
        assertEquals(1L, wineOutputDto.getId());
        assertEquals("Awesome Chardonnay", wineOutputDto.getWineName());
            //assertequals DTO/object:     assertEquals(wineOutputDto, wineDto);
        verify(wineRepository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("Should throw exception with ID when wine not found")
    void testGetWineById_ShouldThrowExceptionWithIdWhenNotFound() {
        //Arrange
        when(wineRepository.findById(anyLong())).thenReturn(Optional.empty());

        //Act & Assert
        assertThatThrownBy(() -> wineService.getWineById(1L))
                .isInstanceOf(RecordNotFoundException.class)
                .hasMessage(String.format("No wine found with id: %d", 1L));
    }

    //CREATE

    @Test
    @DisplayName("Should add a new wine and return wineOutputDto")
    void testCreateWine_shouldAddNewWineAndReturnWineOutputDto() {
        //arrange
        when(wineRepository.existsWineByWineName(anyString())).thenReturn(false);
        when(wineRepository.save(any(Wine.class))).thenReturn(wine);

        //Act

        WineOutputDto createdWineDto = wineService.createWine(wineInputDto);

        //Assert
        assertEquals("Awesome Chardonnay", createdWineDto.getWineName());
        verify(wineRepository, times(1)).existsWineByWineName(anyString());
        verify(wineRepository, times(1)).save(any(Wine.class));
    }

    @Test
    @DisplayName("Should throw an exception with wine name when trying to add a wine with an existing name")
    void testCreateWine_shouldThrowExceptionWhenAlreadyExists() {
        //arrange
        when(wineRepository.existsWineByWineName(anyString())).thenReturn(true);

        //Act & Assert
        assertThatThrownBy(() -> wineService.createWine(wineInputDto))
                .isInstanceOf(EntityAlreadyExistsException.class)
                .hasMessage(String.format("Wine with name: %s already exists.", wineInputDto.getWineName()));
        verify(wineRepository, never()).save(any(Wine.class));
    }


    //UPDATE

    @Test
    @DisplayName("Should update an existing wine and return updated wineOutputDto")
    void testUpdateWineById_shouldUpdateAndReturnWineOutputDto() {
        //arrange
        when(wineRepository.findById(anyLong())).thenReturn(Optional.of(wine));
        when(wineRepository.save(any(Wine.class))).thenReturn(wine);

        //Act
        WineOutputDto updatedwineDto = wineService.updateWineById(1L, wineInputDto);

        //Assert
        assertEquals("Awesome Chardonnay", updatedwineDto.getWineName());
        verify(wineRepository, times(1)).findById(anyLong());
        verify(wineRepository, times(1)).save(any(Wine.class));
    }

    @Test
    @DisplayName("Should throw an exception with ID when trying to update a non-existing wine")
    void testUpdateWineById_shouldThrowExceptionWhenNotFound() {
        //arrange
        when(wineRepository.findById(anyLong())).thenReturn(Optional.empty());

        //Act & Assert
        assertThatThrownBy(() -> wineService.updateWineById(1L, wineInputDto))
                .isInstanceOf(RecordNotFoundException.class)
                .hasMessage(String.format("No wine found with id: %d", 1L));
        verify(wineRepository, times(1)).findById(anyLong());
        verify(wineRepository, never()).save(any(Wine.class));
    }

    //DELETE
    @Test
    @DisplayName("Should delete an existing wine by ID")
    void testDeleteWineById_shouldDeleteWine() {
        //arrange
        when(wineRepository.findById(anyLong())).thenReturn(Optional.of(wine));
        doNothing().when(wineRepository).deleteById(anyLong());

        //Act
        wineService.deleteWineById(1L);

        //Assert
        verify(wineRepository, times(1)).findById(anyLong());
        verify(wineRepository, times(1)).deleteById(anyLong());
    }

    @Test
    @DisplayName("Should throw an exception when trying to delete a non-existing wine")
    void testDeleteWineById_shouldThrowExceptionWhenNotFound() {
        //arrange
        when(wineRepository.findById(anyLong())).thenReturn(Optional.empty());

        //Act & Assert
        assertThatThrownBy(() -> wineService.deleteWineById(1L))
                .isInstanceOf(RecordNotFoundException.class)
                .hasMessage(String.format("No wine found with id: %d", 1L));
        verify(wineRepository, times(1)).findById(anyLong());
        verify(wineRepository, never()).deleteById(anyLong());
    }
}

