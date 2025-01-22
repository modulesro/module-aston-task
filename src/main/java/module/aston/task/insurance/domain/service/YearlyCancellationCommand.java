package module.aston.task.insurance.domain.service;

import module.aston.task.insurance.model.InsuranceParameters;

import java.math.BigDecimal;


public class YearlyCancellationCommand extends AbstractYearlyCommand {

    public YearlyCancellationCommand(final InsuranceParameters parameters) {
        super(parameters);
    }

    @Override
    BigDecimal applyAdditionalCharge() {
        return cancellationYearlyRate;
    }

}
