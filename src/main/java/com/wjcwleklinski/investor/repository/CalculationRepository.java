package com.wjcwleklinski.investor.repository;

import com.wjcwleklinski.investor.entity.Calculation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalculationRepository extends CrudRepository<Calculation, Long> {
}
