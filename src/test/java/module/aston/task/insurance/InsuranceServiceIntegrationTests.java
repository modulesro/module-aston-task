package module.aston.task.insurance;

import module.aston.task.insurance.domain.service.InsuranceService;
import module.aston.task.insurance.model.InsuranceParameters;
import module.aston.task.insurance.model.InsuranceType;
import module.aston.task.insurance.model.InsuranceVariant;
import module.aston.task.insurance.model.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
public class InsuranceServiceIntegrationTests {

    private static final BigDecimal expectedCustomExtendedSportPrice = BigDecimal.valueOf(14.04);

    @Autowired
    private InsuranceService insuranceService;

    @BeforeEach
    void setUp() {
        assertThat(insuranceService).isNotNull();
    }

    @Test
    void testCalculateInsuranceExpectedPrice() {
        final var parameters = new InsuranceParameters(InsuranceVariant.CUSTOM, LocalDate.parse("2025-01-09"), LocalDate.parse("2025-01-11"), InsuranceType.EXTENDED, false, true, 2);
        Result result = insuranceService.calculateInsurance(parameters);
        assertThat(result).isNotNull();
        assertThat(result.price()).isEqualTo(expectedCustomExtendedSportPrice);
    }

    @Test
    void testAllResultsExpectedSize() {
        final var parameters = new InsuranceParameters(InsuranceVariant.CUSTOM, LocalDate.parse("2025-01-09"), LocalDate.parse("2025-01-11"), InsuranceType.EXTENDED, false, true, 2);
        insuranceService.calculateInsurance(parameters);
        insuranceService.calculateInsurance(parameters);
        insuranceService.calculateInsurance(parameters);
        final var results = insuranceService.allResults();
        assertThat(results.size()).isEqualTo(3);
    }

    @Test
    void testResultByIdPersisted() {
        final var parameters = new InsuranceParameters(InsuranceVariant.CUSTOM, LocalDate.parse("2025-01-09"), LocalDate.parse("2025-01-11"), InsuranceType.EXTENDED, false, true, 2);
        final var result = insuranceService.calculateInsurance(parameters);
        final var loadedResult = insuranceService.resultById(result.id());
        assertThat(loadedResult).isNotNull();
        assertThat(loadedResult.orElseThrow().price()).isEqualTo(expectedCustomExtendedSportPrice);
    }

}
