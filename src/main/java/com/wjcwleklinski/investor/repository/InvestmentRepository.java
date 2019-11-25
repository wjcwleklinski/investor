package com.wjcwleklinski.investor.repository;

import com.wjcwleklinski.investor.entity.Investment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestmentRepository extends CrudRepository<Investment, Long> {
}
