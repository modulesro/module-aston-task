package module.aston.task.insurance.domain.service;

import module.aston.task.insurance.model.InsuranceParameters;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

public abstract class AbstractCustomCommand extends InsuranceCommand {

    @Value("${insurance.cancellation.custom.rate}")
    BigDecimal cancellationCustomRate;
    @Value("${insurance.sport.custom.rate}")
    BigDecimal sportCustomRate;

    AbstractCustomCommand(final InsuranceParameters parameters) {
        super(parameters);
    }

    private long getInsuranceDays() {
        return ChronoUnit.DAYS.between(parameters.startDate(), parameters.endDate()) + 1;
    }

    @Override
    BigDecimal calculateBasePrice() {
        return getBasePrice().multiply(BigDecimal.valueOf(getInsuranceDays()));
    }

    abstract BigDecimal applyAdditionalCharge();
}
