package module.aston.task.insurance.domain.service;


import module.aston.task.insurance.domain.config.BasePriceLoader;
import module.aston.task.insurance.model.InsuranceParameters;
import module.aston.task.insurance.model.Result;
import module.aston.task.insurance.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * responsible for all logic regarding calculation itself and persisting calculated result
 */
public abstract class InsuranceCommand {

    final InsuranceParameters parameters;
    final BigDecimal noChargeRate = BigDecimal.valueOf(1);
    @Autowired
    BasePriceLoader basePriceLoader;
    @Autowired
    ResultRepository resultRepository;

    InsuranceCommand(final InsuranceParameters parameters) {
        this.parameters = parameters;
    }

    /**
     * command interface method responsible for calculation and persisting of calculated result
     *
     * @return result object containing calculation together with intput params
     */
    Result execute() {
        final var price = calculatePrice();
        return persistResult(price);
    }

    private Result persistResult(final BigDecimal price) {
        final var now = LocalDateTime.now();
        final var result = new Result(null, parameters.variant(), parameters.startDate(), parameters.endDate(), parameters.type(), parameters.cancellation(), parameters.sport(), parameters.numberOfPersons(), price, now);
        return resultRepository.save(result);
    }

    BigDecimal calculatePrice() {
        return calculateBasePrice().multiply(applyAdditionalCharge()).multiply(BigDecimal.valueOf(parameters.numberOfPersons()));
    }

    abstract BigDecimal calculateBasePrice();

    abstract BigDecimal applyAdditionalCharge();

    BigDecimal getBasePrice() {
        return basePriceLoader.getPrice(parameters.type().name(), parameters.variant().name());
    }

}
