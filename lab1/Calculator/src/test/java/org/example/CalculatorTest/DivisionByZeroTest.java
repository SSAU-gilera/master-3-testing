package org.example.CalculatorTest;

import org.example.Calculator.Calculator;
import org.example.Calculator.CalculatorFactory;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DivisionByZeroTest {

    @Test
    public void testDivisionByZero() {
        CalculatorFactory calculatorFactory = new CalculatorFactory();
        Calculator calculator = calculatorFactory.createCalculator("decimal", "divide");

        assertThrows(ArithmeticException.class, () -> {
            calculator.calculate("divide", "decimal", "10", "0");
        });
    }
}
