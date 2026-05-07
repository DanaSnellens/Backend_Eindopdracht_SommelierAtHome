package nl.novi.eindopdr_danasnellens_sommelierathome.controllers;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
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

import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
@Transactional
public class WineControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    WineController wineController;


     @Test
     @WithMockUser(username = "testuser", roles = "ADMIN")
     void shouldCreateCorrectWine() throws Exception {

         String requestJson = """
                 {
                     "wineName" : "Château Margaux",
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
//Uitgecommente deel staat in code van laatste college
/*         MvcResult result = */this.mockMvc
                 .perform(MockMvcRequestBuilders.post("/wines")
                 .contentType(APPLICATION_JSON)
                 .content(requestJson))
                 .andDo(MockMvcResultHandlers.print())
                 .andExpect(MockMvcResultMatchers.status().isCreated());
/*
         String createdWineJson = result.getResponse().getContentAsString();
         assertThat(result.getResponse().getHeader())*/

     }

}
