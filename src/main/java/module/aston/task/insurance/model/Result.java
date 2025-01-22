package module.aston.task.insurance.model;

import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * main output domain response object (flows from persisted layer over domain layer and outside of REST interface
 *
 * @param id           identifier of result
 * @param variant      input parameter of calculation
 * @param startDate    input parameter of calculation
 * @param endDate      input parameter of calculation
 * @param type         input parameter of calculation
 * @param cancellation input parameter of calculation
 * @param sport        input parameter of calculation
 * @param persons      input parameter of calculation
 * @param price        output result of calculation
 * @param created      datetime point of calculation
 */
public record Result(@Id Long id, InsuranceVariant variant, LocalDate startDate, LocalDate endDate, InsuranceType type,
                     Boolean cancellation, Boolean sport,
                     Integer persons, BigDecimal price, LocalDateTime created) {
}
