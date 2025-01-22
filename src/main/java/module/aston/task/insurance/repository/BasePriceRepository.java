package module.aston.task.insurance.repository;

import module.aston.task.insurance.model.BasePrice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasePriceRepository extends CrudRepository<BasePrice, Long> {
}
