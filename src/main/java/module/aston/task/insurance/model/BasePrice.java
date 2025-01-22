package module.aston.task.insurance.model;

import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

public record BasePrice(@Id Long id, InsuranceVariant variant, InsuranceType type, BigDecimal price) {
}
