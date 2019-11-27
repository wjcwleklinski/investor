package com.wjcwleklinski.investor;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.wjcwleklinski.investor.controller.ApiController;
import com.wjcwleklinski.investor.entity.Calculation;
import com.wjcwleklinski.investor.entity.Investment;
import com.wjcwleklinski.investor.util.CalculationAlgorithm;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class InvestorRestControllerTest {

    @Autowired
    private ApiController apiController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestRestTemplate template;


    @Before
    public void whenPost() throws Exception {

        Map<String, Object> investment = new HashMap<>();
        investment.put("name", "Basic investment");
        investment.put("rate", new BigDecimal("0.06"));
        investment.put("capitalisationPeriod", 3);
        investment.put("startDate", "2018-10-02");
        investment.put("endDate", "2019-10-02");


        Map<String, Object> calculation = new HashMap<>();
        calculation.put("amount", new BigDecimal("10000.0"));
        calculation.put("algorithm", CalculationAlgorithm.ON_INVESTMENTS_END);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();

        String requestInvestment = writer.writeValueAsString(investment);
        String requestCalculation = writer.writeValueAsString(calculation);

        RequestBuilder rb1 = MockMvcRequestBuilders
                .post(Endpoints.INVESTMENT_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestInvestment);
        mockMvc.perform(rb1).andExpect(MockMvcResultMatchers.status().isOk());

        RequestBuilder rb2 = MockMvcRequestBuilders
                .post(Endpoints.INVESTMENT_ENDPOINT + "/1/calculations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestCalculation);
        mockMvc.perform(rb2).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenControllerInjected() {
        Assert.assertNotNull(apiController);
    }

    @Test
    public void whenGetInvestments() throws Exception{
        RequestBuilder rb = MockMvcRequestBuilders
                .get(Endpoints.INVESTMENT_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON);
        ResultMatcher rm1 = MockMvcResultMatchers
                .status()
                .isOk();
        mockMvc
                .perform(rb)
                .andExpect(rm1);
    }

    @Test
    public void whenGetCalculations() throws Exception {
        RequestBuilder rb = MockMvcRequestBuilders
                .get(Endpoints.INVESTMENT_ENDPOINT + "/1/calculations")
                .contentType(MediaType.APPLICATION_JSON);
        ResultMatcher rm1 = MockMvcResultMatchers
                .status()
                .isOk();
        mockMvc
                .perform(rb)
                .andExpect(rm1);
    }
}
