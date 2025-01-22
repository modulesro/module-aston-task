package module.aston.task.insurance.domain.service;

import module.aston.task.insurance.model.InsuranceParameters;

import java.math.BigDecimal;


public class YearlySportCommand extends AbstractYearlyCommand {

    public YearlySportCommand(final InsuranceParameters parameters) {
        super(parameters);
    }

    @Override
    BigDecimal applyAdditionalCharge() {
        return sportYearlyRate;
    }

}
