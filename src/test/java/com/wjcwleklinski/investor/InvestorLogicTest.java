package com.wjcwleklinski.investor;

import com.wjcwleklinski.investor.entity.Calculation;
import com.wjcwleklinski.investor.entity.Investment;
import com.wjcwleklinski.investor.service.CalculationService;
import com.wjcwleklinski.investor.service.InvestmentService;
import com.wjcwleklinski.investor.util.CalculationAlgorithm;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class InvestorLogicTest {

    @Autowired
    private CalculationService calculationService;

    @Autowired
    private InvestmentService investmentService;

    @Test
    public void calculationLogicTest() {
        Calculation calculation = new Calculation(new BigDecimal("10000.0"),
                LocalDate.now(),
                CalculationAlgorithm.ON_INVESTMENTS_END);

        Assert.assertEquals(calculation, calculationService.save(calculation));

        LocalDate startDate = LocalDate.of(2019, Month.AUGUST, 10);
        LocalDate endDate = LocalDate.of(2020, Month.AUGUST, 10);

        Investment investment = new Investment("Basic investment",
                new BigDecimal("0.06"),
                3,
                startDate,
                endDate);


        BigDecimal result = new BigDecimal("613.64");
        Assert.assertEquals(result, calculationService.calculateProfit(calculation, investment));
    }

    @Test
    public void investmentLogicTest() {

        LocalDate startDate = LocalDate.of(2019, Month.AUGUST, 10);
        LocalDate endDate = LocalDate.of(2020, Month.AUGUST, 10);

        Investment investment = new Investment("Basic investment",
                new BigDecimal("0.06"),
                3,
                startDate,
                endDate);

        Assert.assertEquals(investment, investmentService.save(investment));
        Assert.assertTrue(investmentService.findById(investment.getId()).isPresent());

    }

    @Test
    public void whenServiceInjected() {
        Assert.assertNotNull(calculationService);
        Assert.assertNotNull(investmentService);
    }
}
