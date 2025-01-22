package module.aston.task.insurance.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class InsuranceCalculationTypeUnitTests {

    @Test
    void testCustomSportCalculationTypeExpected() {
        final var parameters = new InsuranceParameters(InsuranceVariant.CUSTOM, LocalDate.parse("2025-01-09"), LocalDate.parse("2025-01-11"), InsuranceType.EXTENDED, false, true, 2);
        final var calculationType = CalculationType.getInstance(parameters);
        assertThat(calculationType).isEqualTo(CalculationType.CUSTOM_SPORT);
    }

}
