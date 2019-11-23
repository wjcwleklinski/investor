package com.wjcwleklinski.investor.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Investment {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private BigDecimal interestRate;

    private Integer capitalizationPeriod;

    private Integer validityPeriod;

    @OneToMany(mappedBy = "investment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Calculation> calculations;
}
