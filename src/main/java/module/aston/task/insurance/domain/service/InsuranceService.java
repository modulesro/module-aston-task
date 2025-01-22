package module.aston.task.insurance.domain.service;

import jakarta.validation.Valid;
import module.aston.task.insurance.model.InsuranceParameters;
import module.aston.task.insurance.model.Result;

import java.util.List;
import java.util.Optional;

/**
 * main domain service layer between controller and domain itself
 */
public interface InsuranceService {

    /**
     * calculates insurance price based on input parameters
     * validates of input parameters based on context constraints
     *
     * @param parameters input parameters
     * @return calculation in form of result object
     */
    Result calculateInsurance(@Valid final InsuranceParameters parameters);

    /**
     * responsible to retrieve all persisted results
     *
     * @return list of all persisted results
     */
    List<Result> allResults();

    /**
     * retrieves result for given identifier
     *
     * @param id identifier of persisted calculation result
     * @return optional of persisted result
     */
    Optional<Result> resultById(Long id);

}
