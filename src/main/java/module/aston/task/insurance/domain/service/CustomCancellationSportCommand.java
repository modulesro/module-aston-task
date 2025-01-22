package module.aston.task.insurance.domain.service;

import module.aston.task.insurance.model.InsuranceParameters;

import java.math.BigDecimal;


public class CustomCancellationSportCommand extends AbstractCustomCommand {

    public CustomCancellationSportCommand(final InsuranceParameters parameters) {
        super(parameters);
    }

    @Override
    BigDecimal applyAdditionalCharge() {
        return cancellationCustomRate.multiply(sportCustomRate);
    }

}
