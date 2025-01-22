package module.aston.task.insurance.domain.service;

import module.aston.task.insurance.model.InsuranceParameters;
import module.aston.task.insurance.model.InsuranceType;
import module.aston.task.insurance.model.InsuranceVariant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;


@SpringBootTest
public class InsuranceCommandFactoryIntegrationTests {

    @Autowired
    private CommandFactory<InsuranceCommand> commandFactory;

    @Test
    void testExpectedCommandType() {
        final var parameters = new InsuranceParameters(InsuranceVariant.CUSTOM, LocalDate.parse("2025-01-09"), LocalDate.parse("2025-01-11"), InsuranceType.EXTENDED, false, true, 2);
        final var command = commandFactory.createCommand(parameters);
        assertThat(command, instanceOf(CustomSportCommand.class));
    }

}
