package module.aston.task.insurance.domain.config;

import jakarta.validation.Valid;
import module.aston.task.insurance.domain.service.CustomCancellationCommand;
import module.aston.task.insurance.domain.service.CustomCancellationSportCommand;
import module.aston.task.insurance.domain.service.CustomCommand;
import module.aston.task.insurance.domain.service.CustomSportCommand;
import module.aston.task.insurance.model.InsuranceParameters;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.validation.annotation.Validated;

@Configuration
@Validated
public class CustomCommandConfig {

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    @Validated(CustomValidationConstraint.class)
    public CustomCommand customCommand(@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") @Valid final InsuranceParameters parameters) {
        return new CustomCommand(parameters);
    }

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    @Validated(CustomValidationConstraint.class)
    public CustomCancellationCommand customCancellationCommand(@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") @Valid final InsuranceParameters parameters) {
        return new CustomCancellationCommand(parameters);
    }

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    @Validated(CustomValidationConstraint.class)
    public CustomSportCommand customSportCommand(@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") @Valid final InsuranceParameters parameters) {
        return new CustomSportCommand(parameters);
    }

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    @Validated(CustomValidationConstraint.class)
    public CustomCancellationSportCommand customCancellationSportCommand(@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") @Valid final InsuranceParameters parameters) {
        return new CustomCancellationSportCommand(parameters);
    }

}
