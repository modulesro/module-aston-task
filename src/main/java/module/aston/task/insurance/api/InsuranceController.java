package module.aston.task.insurance.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import module.aston.task.insurance.domain.service.InsuranceService;
import module.aston.task.insurance.model.InsuranceParameters;
import module.aston.task.insurance.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * api controller for handling all request related to insurance calculation
 */
@RestController
@RequestMapping(InsuranceController.RESOURCE)
public class InsuranceController {

    public final static String RESOURCE = "/insurance";
    private static final Logger log = LoggerFactory.getLogger(InsuranceController.class);

    private final InsuranceService insuranceService;

    public InsuranceController(@Autowired InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    /**
     * POST method for persisted insurance calculation
     *
     * @param parameters JSON request input calculation parameters
     * @return JSON response of calculation result
     */
    @PostMapping(value = "/calculation", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = org.springframework.http.HttpStatus.CREATED)
    @Tag(name = "post", description = "POST methods of Insurance calculation API")
    @Operation(summary = "method for insurance calculation and persisting result",
            description = "create calculation result")
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Result.class))}),
            @ApiResponse(responseCode = "400", description = "Validation error",
                    content = @Content)})
    public @ResponseBody Result calculateInsurance(@RequestBody final InsuranceParameters parameters) {
        log.info("calculateInsurance called with parameters: {}", parameters.toString().replace("\n", ""));
        return insuranceService.calculateInsurance(parameters);
    }

    /**
     * GET method for all so far calculated results
     *
     * @return list JSON response of all calculated results
     */
    @GetMapping(value = "/results", produces = MediaType.APPLICATION_JSON_VALUE)
    @Tag(name = "get", description = "GET methods of Insurance calculation API")
    @Operation(summary = "method for retrieving all persisted results",
            description = "retrieve calculation results")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Result.class))})})
    public @ResponseBody List<Result> allResults() {
        log.info("allResults called");
        return insuranceService.allResults();
    }

    /**
     * GET method for return calculated result by id
     *
     * @param id identifier of result record
     * @return JSON response retrieved from persisted storage
     */
    @GetMapping(value = "/result/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Tag(name = "get", description = "GET methods of Insurance calculation API")
    @Operation(summary = "method for retrieving persisted result for given id",
            description = "retrieve calculation result by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Result.class))}),
            @ApiResponse(responseCode = "404", description = "Result for given id not found",
                    content = @Content)})
    public @ResponseBody Optional<Result> result(@Parameter(
            description = "ID of Result to be retrieved",
            required = true) @PathVariable Long id) {
        log.info("result called with id: {}", id);
        return insuranceService.resultById(id);
    }

}
