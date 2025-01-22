package module.aston.task.insurance.domain.service;

import jakarta.validation.Valid;
import module.aston.task.insurance.exception.EntityNotFoundException;
import module.aston.task.insurance.model.InsuranceParameters;
import module.aston.task.insurance.model.Result;
import module.aston.task.insurance.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public class InsuranceServiceImpl implements InsuranceService {

    private final CommandFactory<? extends InsuranceCommand> insuranceCommandFactory;
    private final ResultRepository resultRepository;

    public InsuranceServiceImpl(@Autowired CommandFactory<? extends InsuranceCommand> insuranceCommandFactory, @Autowired ResultRepository resultRepository) {
        this.insuranceCommandFactory = insuranceCommandFactory;
        this.resultRepository = resultRepository;
    }

    @Override
    public Result calculateInsurance(@Valid final InsuranceParameters parameters) {
        var command = insuranceCommandFactory.createCommand(parameters);
        return command.execute();
    }

    @Override
    public List<Result> allResults() {
        List<Result> results = new ArrayList<>();
        resultRepository.findAll().iterator().forEachRemaining(results::add);
        return results;
    }

    @Override
    public Optional<Result> resultById(Long id) {
        return Optional.ofNullable(resultRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Result for id=" + id + " not found.")));
    }

}
