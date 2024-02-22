package org.example.CalculatorTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.example.Calculator.Calculator;
import org.example.Calculator.CalculatorFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ParameterisedTest {

    @ParameterizedTest
    @CsvSource({
            "binary, add, 1010, 1101, 10111",
            "binary, subtract, 11001, 1101, 1100",
            "binary, multiply, 1010, 1101, 10000010",
            "binary, divide, 11100, 1101, 10",

            "octal, add, 75, 42, 137",
            "octal, subtract, 70, 16, 52",
            "octal, multiply, 75, 42, 4032",
            "octal, divide, 70, 16, 4",

            "decimal, add, 10, 15, 25",
            "decimal, subtract, 34, 18, 16",
            "decimal, multiply, 11, 2, 22",
            "decimal, divide, 120, 3, 40",

            "hexadecimal, add, 7A, 1F, 99",
            "hexadecimal, subtract, 5A, 9, 51",
            "hexadecimal, multiply, 2E, 1D, 536",
            "hexadecimal, divide, 1A, 5, 5"
    })

    public void testCalculatorOperations(String base, String operation, String num1, String num2, String expectedResult) {
        CalculatorFactory calculatorFactory = new CalculatorFactory();
        Calculator calculator = calculatorFactory.createCalculator(base, operation);
        String result = calculator.calculate(operation, base, num1, num2);

        assertEquals(expectedResult, result);
    }

}
