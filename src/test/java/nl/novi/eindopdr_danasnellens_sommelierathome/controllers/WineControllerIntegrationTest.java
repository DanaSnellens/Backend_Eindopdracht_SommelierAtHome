package nl.novi.eindopdr_danasnellens_sommelierathome.controllers;
import jakarta.transaction.Transactional;
import nl.novi.eindopdr_danasnellens_sommelierathome.repositories.WineRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class WineControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private WineRepository wineRepository;


    @Test
    void getWineById_shouldReturnNotFoundWhenWineDoesNotExist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/wines/{id}", 10003L))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @Transactional
    void deleteWineById_shouldReturnOkAndWineShouldNoLongerExist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/wines/{id}", 1005L))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
        assertThat(wineRepository.findById(1005L)).isEmpty();
    }
}
