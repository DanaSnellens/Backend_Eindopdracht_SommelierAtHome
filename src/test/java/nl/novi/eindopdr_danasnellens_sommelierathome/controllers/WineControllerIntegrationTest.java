package nl.novi.eindopdr_danasnellens_sommelierathome.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.WineInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.WineOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.repositories.WineRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
//@Transactional
//@ExtendWith(MockitoExtension.class)
public class WineControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private WineRepository wineRepository;

    @Autowired
    private WineController wineController;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void getWineById_shouldReturnNotFoundWhenWineDoesNotExist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/wines/{id}", 10003L))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }


/*     @Test
     void createWine_shouldReturnAlreadyExistsWhenWineAlreadyExist() throws Exception {

         String requestJson = """
                 {
                     "wineName" : "Chateau Margaux",
                     "country" : "France",
                     "region" : "Bordeaux",
                     "grapeVarietal" : "Cabernet Sauvignon",
                     "producer" : "Château Margaux",
                     "wineStyle" : "Full-bodied",
                     "wineType" : "Red",
                     "foodPairing" : "Red meat, game, strong cheeses",
                     "year" : 2015,
                     "price" : 999.99,
                     "aromas" : "Blackcurrant, cedar, tobacco",
                     "imageLink" : "https://example.com/chateau-margaux.jpg",
                     "imageAlt" : "A bottle of Château Margaux wine",
                     "shortDescription" : "A prestigious red wine from Bordeaux.",
                     "longDescription" : "Château Margaux is one of the most renowned wines in the world, known for its elegance and complexity. It offers rich flavors of blackcurrant, cedar, and tobacco, making it a perfect choice for special occasions."
                 }
                 """;
            MvcResult result = mockMvc
                 .perform(MockMvcRequestBuilders.post("/wines")
                 .contentType(APPLICATION_JSON)
                 .content(requestJson))
                 .andDo(MockMvcResultHandlers.print())
                    //entity alrea

                 .andReturn();

         WineOutputDto createdWine = objectMapper.readValue(result.getResponse().getContentAsString(), WineOutputDto.class);
         Assertions.assertEquals("Chateau Margaux", createdWine.getWineName());
         String createdWineJson = result.getResponse().getContentAsString();
*//*         assertThat(result.getResponse().getHeader())*//*;

     }*/

}
