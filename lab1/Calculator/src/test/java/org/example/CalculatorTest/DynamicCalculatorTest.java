package org.example.CalculatorTest;

import org.example.Calculator.Calculator;
import org.example.Calculator.CalculatorFactory;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.Assertions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DynamicCalculatorTest {
    @TestFactory
    Stream<DynamicTest> dynamicTests() throws IOException {
        List<DynamicTest> dynamicTests = new ArrayList<>();
        String csvFile = "src/test/resources/test_data.csv";
        String line = "";
        String delimiter = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(delimiter);
                String base = data[0];
                String operation = data[1];
                String num1 = data[2];
                String num2 = data[3];
                String expectedResult = data[4];

                String testName = String.format("%s %s %s and %s = %s", base, operation, num1, num2, expectedResult);

                DynamicTest dynamicTest = DynamicTest.dynamicTest(testName, () -> {
                    CalculatorFactory calculatorFactory = new CalculatorFactory();
                    Calculator calculator = calculatorFactory.createCalculator(base, operation);
                    String result = calculator.calculate(operation, base, num1, num2);

                    Assertions.assertEquals(expectedResult, result);
                });

                dynamicTests.add(dynamicTest);
            }
        }

        return dynamicTests.stream();
    }
}
