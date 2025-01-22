package module.aston.task.insurance.domain.service;

import module.aston.task.insurance.model.CalculationType;
import module.aston.task.insurance.model.InsuranceParameters;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InsuranceCommandFactory implements CommandFactory<InsuranceCommand> {

    private final ObjectProvider<CustomCommand> customCommandProvider;
    private final ObjectProvider<CustomCancellationCommand> customCancellationCommandProvider;
    private final ObjectProvider<CustomSportCommand> customSportCommandProvider;
    private final ObjectProvider<CustomCancellationSportCommand> customCancellationSportCommandProvider;
    private final ObjectProvider<YearlyCommand> yearlyCommandProvider;
    private final ObjectProvider<YearlyCancellationCommand> yearlyCancellationCommandProvider;
    private final ObjectProvider<YearlySportCommand> yearlySportCommandProvider;
    private final ObjectProvider<YearlyCancellationSportCommand> yearlyCancellationSportCommandProvider;

    public InsuranceCommandFactory(@Autowired ObjectProvider<CustomCommand> customCommandProvider, @Autowired ObjectProvider<CustomCancellationCommand> customCancellationCommandProvider, @Autowired ObjectProvider<CustomSportCommand> customSportCommandProvider, @Autowired ObjectProvider<CustomCancellationSportCommand> customCancellationSportCommandProvider, @Autowired ObjectProvider<YearlyCommand> yearlyCommandProvider, @Autowired ObjectProvider<YearlyCancellationCommand> yearlyCancellationCommandProvider, @Autowired ObjectProvider<YearlySportCommand> yearlySportCommandProvider, @Autowired ObjectProvider<YearlyCancellationSportCommand> yearlyCancellationSportCommandProvider) {
        this.customCommandProvider = customCommandProvider;
        this.customCancellationCommandProvider = customCancellationCommandProvider;
        this.customSportCommandProvider = customSportCommandProvider;
        this.customCancellationSportCommandProvider = customCancellationSportCommandProvider;
        this.yearlyCommandProvider = yearlyCommandProvider;
        this.yearlyCancellationCommandProvider = yearlyCancellationCommandProvider;
        this.yearlySportCommandProvider = yearlySportCommandProvider;
        this.yearlyCancellationSportCommandProvider = yearlyCancellationSportCommandProvider;
    }

    @Override
    public InsuranceCommand createCommand(final InsuranceParameters parameters) {
        return switch (CalculationType.getInstance(parameters)) {
            case CUSTOM -> customCommandProvider.getObject(parameters);
            case CUSTOM_CANCELLATION -> customCancellationCommandProvider.getObject(parameters);
            case CUSTOM_SPORT -> customSportCommandProvider.getObject(parameters);
            case CUSTOM_CANCELLATION_SPORT -> customCancellationSportCommandProvider.getObject(parameters);
            case YEARLY -> yearlyCommandProvider.getObject(parameters);
            case YEARLY_CANCELLATION -> yearlyCancellationCommandProvider.getObject(parameters);
            case YEARLY_SPORT -> yearlySportCommandProvider.getObject(parameters);
            case YEARLY_CANCELLATION_SPORT -> yearlyCancellationSportCommandProvider.getObject(parameters);
        };
    }
}
