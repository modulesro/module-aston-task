package module.aston.task.insurance.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import module.aston.task.insurance.domain.config.CustomValidationConstraint;

import java.time.LocalDate;

/**
 * main input object with values for calculation
 * @param variant validated must not be null for all calculations
 * @param startDate validated must not be null for all calculations
 * @param endDate validated must not be null for CUSTOM variant (see CustomValidationConstraint class)
 * @param type validated must not be null for all calculations
 * @param cancellation not validated => can be null, in this case default false is used
 * @param sport not validate => can be null, in this case default false is used
 * @param numberOfPersons validated, possible values are set of {1, 2, 3}
 */
public record InsuranceParameters(@NotNull InsuranceVariant variant, @NotNull LocalDate startDate,
                                  @NotNull(groups = CustomValidationConstraint.class) LocalDate endDate,
                                  @NotNull InsuranceType type, boolean cancellation,
                                  boolean sport,
                                  @NotNull @Min(1) @Max(3) Integer numberOfPersons) {
}
