package module.aston.task.insurance.domain.service;

import module.aston.task.insurance.model.InsuranceParameters;

import java.math.BigDecimal;


public class YearlyCancellationSportCommand extends AbstractYearlyCommand {

    public YearlyCancellationSportCommand(final InsuranceParameters parameters) {
        super(parameters);
    }

    @Override
    BigDecimal applyAdditionalCharge() {
        return cancellationYearlyRate.multiply(sportYearlyRate);
    }

}
