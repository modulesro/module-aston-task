package module.aston.task.insurance.domain.service;

import module.aston.task.insurance.domain.config.BasePriceLoader;
import module.aston.task.insurance.model.InsuranceParameters;
import module.aston.task.insurance.model.InsuranceType;
import module.aston.task.insurance.model.InsuranceVariant;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class InsuranceCommandUnitTests {

    private static final BigDecimal expectedCustomSportPrice = BigDecimal.valueOf(14.04);
    private static final BigDecimal sportCustomRate = BigDecimal.valueOf(1.3);
    private static final BigDecimal customExtendedRate = BigDecimal.valueOf(1.8);
    private final BasePriceLoader basePriceLoader = Mockito.mock(BasePriceLoader.class);

    @Test
    void testCustomExtendedSportExpectedPrice() {
        final var parameters = new InsuranceParameters(InsuranceVariant.CUSTOM, LocalDate.parse("2025-01-09"), LocalDate.parse("2025-01-11"), InsuranceType.EXTENDED, false, true, 2);
        final var command = new CustomSportCommand(parameters);
        command.sportCustomRate = sportCustomRate;
        command.basePriceLoader = basePriceLoader;
        Mockito.when(command.getBasePrice()).thenReturn(customExtendedRate);
        BigDecimal price = command.calculatePrice();
        assertThat(price).isEqualTo(expectedCustomSportPrice);
    }

}
