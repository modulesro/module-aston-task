package module.aston.task.insurance;

import com.fasterxml.jackson.databind.ObjectMapper;
import module.aston.task.insurance.model.InsuranceParameters;
import module.aston.task.insurance.model.InsuranceType;
import module.aston.task.insurance.model.InsuranceVariant;
import module.aston.task.insurance.model.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class InsuranceControllerIntegrationTests {

    private static final BigDecimal expectedCustomExtendedSportPrice = BigDecimal.valueOf(14.04);
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCalculationSuccess() throws Exception {
        final var parameters = new InsuranceParameters(InsuranceVariant.CUSTOM, LocalDate.parse("2025-01-09"), LocalDate.parse("2025-01-11"), InsuranceType.EXTENDED, false, true, 2);
        final var request = objectMapper.writeValueAsString(parameters);
        final var result = this.mockMvc.perform(post("/insurance/calculation").content(request).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn();
        final var response = objectMapper.readValue(result.getResponse().getContentAsString(), Result.class);
        assertThat(response).isNotNull();
        assertThat(response.id()).isNotNull();
        assertThat(response.created()).isNotNull();
        assertThat(response.variant()).isEqualTo(InsuranceVariant.CUSTOM);
        assertThat(response.type()).isEqualTo(InsuranceType.EXTENDED);
        assertThat(response.startDate()).isEqualTo(LocalDate.parse("2025-01-09"));
        assertThat(response.endDate()).isEqualTo(LocalDate.parse("2025-01-11"));
        assertThat(response.price()).isEqualTo(expectedCustomExtendedSportPrice);
        assertThat(response.persons()).isEqualTo(2);

    }

    @Test
    void testCalculationInvalidVariantError() throws Exception {
        final var request = """
                {"variant":"INVALID_VARIANT","startDate":"2025-01-09", "endDate":"2025-01-11", "type": "EXTENDED", "sport": true, "numberOfPersons": 2}
                """;
        this.mockMvc.perform(post("/insurance/calculation").content(request).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andExpect(content().string(containsString("from String \\\"INVALID_VARIANT\\\": not one of the values accepted for Enum class")));
    }

    @Test
    void testCalculationInvalidTypeError() throws Exception {
        final var request = """
                {"variant":"CUSTOM","startDate":"2025-01-09", "endDate":"2025-01-11", "type": "INVALID_TYPE", "sport": true, "numberOfPersons": 2}
                """;
        this.mockMvc.perform(post("/insurance/calculation").content(request).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andExpect(content().string(containsString("from String \\\"INVALID_TYPE\\\": not one of the values accepted for Enum class")));
    }

    @Test
    void testCalculationNumberOfPersonsOutOfRangeError() throws Exception {
        final var request = """
                {"variant":"CUSTOM","startDate":"2025-01-09", "endDate":"2025-01-11", "type": "EXTENDED", "sport": true, "numberOfPersons": 11}
                """;
        this.mockMvc.perform(post("/insurance/calculation").content(request).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andExpect(content().string(containsString("calculateInsurance.parameters.numberOfPersons: must be less than or equal to 3")));
    }

    @Test
    void testCalculationCustomSportEndDateMissingError() throws Exception {
        final var request = """
                {"variant":"CUSTOM","startDate":"2025-01-09", "type": "EXTENDED", "sport": true, "numberOfPersons": 2}
                """;
        this.mockMvc.perform(post("/insurance/calculation").content(request).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andExpect(content().string(containsString("customSportCommand.parameters.endDate: must not be null")));
    }

    @Test
    void testCalculationVariantMissingError() throws Exception {
        final var request = """
                {"startDate":"2025-01-09", "endDate":"2025-01-11", "type": "EXTENDED", "sport": true, "numberOfPersons": 2}
                """;
        this.mockMvc.perform(post("/insurance/calculation").content(request).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andExpect(content().string(containsString("calculateInsurance.parameters.variant: must not be null")));
    }

    @Test
    void testCalculationTypeMissingError() throws Exception {
        final var request = """
                {"variant":"CUSTOM","startDate":"2025-01-09", "endDate":"2025-01-11", "sport": true, "numberOfPersons": 2}
                """;
        this.mockMvc.perform(post("/insurance/calculation").content(request).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andExpect(content().string(containsString("calculateInsurance.parameters.type: must not be null")));
    }

    @Test
    void testCalculationStartDateMissingError() throws Exception {
        final var request = """
                {"variant":"CUSTOM","endDate":"2025-01-11", "type": "EXTENDED", "sport": true, "numberOfPersons": 2}
                """;
        this.mockMvc.perform(post("/insurance/calculation").content(request).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andExpect(content().string(containsString("calculateInsurance.parameters.startDate: must not be null")));
    }

    @Test
    void testCalculationYearlySportEndDateMissingSuccess() throws Exception {
        final var request = """
                {"variant":"YEARLY","startDate":"2025-01-09", "type": "EXTENDED", "sport": true, "numberOfPersons": 2}
                """;
        this.mockMvc.perform(post("/insurance/calculation").content(request).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }

    @Test
    void testResultNotFound() throws Exception {
        this.mockMvc.perform(post("/insurance/calculation/11")).andExpect(status().isNotFound());
    }

}
