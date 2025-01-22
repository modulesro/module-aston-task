package module.aston.task.insurance.model;

import module.aston.task.insurance.exception.CalculationTypeNotFoundException;

import java.util.Arrays;

/**
 * enum representing all possible calculations use cases
 */
public enum CalculationType {

    CUSTOM(InsuranceVariant.CUSTOM, false, false),
    CUSTOM_CANCELLATION(InsuranceVariant.CUSTOM, true, false),
    CUSTOM_SPORT(InsuranceVariant.CUSTOM, false, true),
    CUSTOM_CANCELLATION_SPORT(InsuranceVariant.CUSTOM, true, true),
    YEARLY(InsuranceVariant.YEARLY, false, false),
    YEARLY_CANCELLATION(InsuranceVariant.YEARLY, true, false),
    YEARLY_SPORT(InsuranceVariant.YEARLY, false, true),
    YEARLY_CANCELLATION_SPORT(InsuranceVariant.YEARLY, true, true);

    private final InsuranceVariant variant;
    private final boolean cancellation;
    private final boolean sport;

    CalculationType(final InsuranceVariant variant, final boolean cancellation, final boolean sport) {
        this.variant = variant;
        this.cancellation = cancellation;
        this.sport = sport;
    }

    public static CalculationType getInstance(final InsuranceParameters parameters) {
        return Arrays.stream(CalculationType.values()).filter(item -> item.variant.equals(parameters.variant()) && item.cancellation == parameters.cancellation() && item.sport == parameters.sport()).findAny().orElseThrow(() -> new CalculationTypeNotFoundException(String.format("Calculation type for value %s not found", parameters)));
    }

}
