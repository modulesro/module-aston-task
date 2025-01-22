package module.aston.task.insurance.domain.config;

import module.aston.task.insurance.model.BasePrice;
import module.aston.task.insurance.repository.BasePriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * responsible for loading and supply price rates based on composite key of form type + variant
 * prices are loaded from DB on application startup into unmodifiable map
 */
@Component
public class BasePriceLoader implements ApplicationRunner {

    private final BasePriceRepository basePriceRepository;
    private Map<String, BigDecimal> priceMap;

    public BasePriceLoader(@Autowired BasePriceRepository basePriceRepository) {
        this.basePriceRepository = basePriceRepository;
    }

    private void createImmutableMap(final List<BasePrice> prices) {
        priceMap = prices.stream().collect(Collectors.toUnmodifiableMap(item -> item.type().name() + item.variant().name(), BasePrice::price));
    }

    @Override
    public void run(final ApplicationArguments args) {
        final List<BasePrice> prices = new ArrayList<>();
        final var iterator = basePriceRepository.findAll().iterator();
        iterator.forEachRemaining(prices::add);
        createImmutableMap(prices);
    }

    /**
     * returns price based of composite key of form type + variant
     *
     * @param type    string representation of enum InsuranceType class
     * @param variant string representation of enum InsuranceVariant class
     * @return base rate for calculation
     */
    public BigDecimal getPrice(final String type, final String variant) {
        return priceMap.get(type + variant);
    }

}
