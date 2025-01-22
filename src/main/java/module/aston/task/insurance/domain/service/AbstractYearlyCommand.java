package module.aston.task.insurance.domain.service;

import module.aston.task.insurance.model.InsuranceParameters;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;

public abstract class AbstractYearlyCommand extends InsuranceCommand {

    @Value("${insurance.cancellation.yearly.rate}")
    BigDecimal cancellationYearlyRate;
    @Value("${insurance.sport.yearly.rate}")
    BigDecimal sportYearlyRate;

    AbstractYearlyCommand(final InsuranceParameters parameters) {
        super(parameters);
    }

    @Override
    BigDecimal calculateBasePrice() {
        return getBasePrice();
    }

    abstract BigDecimal applyAdditionalCharge();

}
