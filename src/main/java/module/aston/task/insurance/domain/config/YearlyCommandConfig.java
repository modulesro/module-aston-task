package module.aston.task.insurance.domain.config;

import module.aston.task.insurance.domain.service.YearlyCancellationCommand;
import module.aston.task.insurance.domain.service.YearlyCancellationSportCommand;
import module.aston.task.insurance.domain.service.YearlyCommand;
import module.aston.task.insurance.domain.service.YearlySportCommand;
import module.aston.task.insurance.model.InsuranceParameters;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class YearlyCommandConfig {

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public YearlyCommand yearlyCommand(@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") final InsuranceParameters parameters) {
        return new YearlyCommand(parameters);
    }

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public YearlyCancellationCommand yearlyCancellationCommand(@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") final InsuranceParameters parameters) {
        return new YearlyCancellationCommand(parameters);
    }

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public YearlySportCommand yearlySportCommand(@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") final InsuranceParameters parameters) {
        return new YearlySportCommand(parameters);
    }

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public YearlyCancellationSportCommand yearlyCancellationSportCommand(@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") final InsuranceParameters parameters) {
        return new YearlyCancellationSportCommand(parameters);
    }

}
