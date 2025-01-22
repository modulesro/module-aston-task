package module.aston.task.insurance.domain.service;

import module.aston.task.insurance.model.InsuranceParameters;

/**
 * defines generic factory contract for commands creation
 *
 * @param <T> generic parameter of type InsuranceCommand
 */
public interface CommandFactory<T extends InsuranceCommand> {

    /**
     * creates command object
     *
     * @param parameters input parameters for insurance calculation
     * @return concrete command instance
     */
    T createCommand(final InsuranceParameters parameters);

}
