package com.example.definitions;

import com.example.Calculator.CalculatorRepository;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.convert.Delimiter;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class StepsDefs extends SpringTest {

    @Autowired
    CalculatorRepository calculatorRepository;

    private String operation;
    private String base;
    private String num1;
    private String num2;
    private String startDate;
    private String endDate;
//    private int expectedOperationCount;


    @ParameterType("(?:.+;)+.+")
    public List<String> stringList(final String raw) {
        final String[] values = raw.split(";");
        return Arrays.asList(values);
    }

    @Given("Operation is {string}")
    public void operationIs(String operation) {
        this.operation = operation;
    }

    @Given("Number system is {string}")
    public void baseIs(String base) {
        this.base = base;
    }

    @When("First number is {string}")
    public void num1Is(String num1) {
        this.num1 = num1;
    }

    @And("Second number is {string}")
    public void num2Is(String num2) {
        this.num2 = num2;
    }

    @When("Numbers are \"{stringList}\"")
    public void numbersAre(@Delimiter(";") final List<String> numbers) {
        this.num1 = numbers.get(0);
        this.num2 = numbers.get(1);
    }

    @When("Start date is {string}")
    public void startDateIs(String startDate) {
        this.startDate = startDate;
    }

    @And("End date is {string}")
    public void endDateIs(String endDate) {
        this.endDate = endDate;
    }

    @Given("Execute operations")
    public void executeOperations(List<Map<String, String>> operations) {
        for (Map<String, String> operationData : operations) {
            String num1 = operationData.get("num1");
            String num2 = operationData.get("num2");
            String base = operationData.get("base");
            String operation = operationData.get("operation");

            executeGet("http://localhost:8080",num1,num2,base,base,operation);
        }
    }

    @Then("Result is {string}")
    public void resultIs(String expectedResult) {
        executeGet("http://localhost:8080",num1,num2,base,base,operation);
        assertThat(SpringTest.latestResponse.getBody()).isEqualTo("Result: " + expectedResult);
    }

    @Then("Get all {string} operations")
    public void getOperationsByType(String operation) {
        executeGetOperationsByType("http://localhost:8080",operation);
    }

    @Then("The number of operations should be {string}")
    public void countOperationsByTypeBetweenDates(String expectedResult) {
        Date fromDate = Date.valueOf(startDate);
        Date toDate = Date.valueOf(endDate);

        String actualOperationCount = calculatorRepository.countByDateBetweenAndOperationTypeAndFirstNumSystemAndSecondNumSystem(
                fromDate, toDate, operation, base, base
        );
        assertThat(actualOperationCount).isEqualTo(expectedResult);
    }

    @Given("Empty database")
    public void prepareDatabase() {
        calculatorRepository.clear();
    }

}
