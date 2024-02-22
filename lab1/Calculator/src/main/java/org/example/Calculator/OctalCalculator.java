package org.example.Calculator;

public class OctalCalculator extends BaseCalculator{
    public OctalCalculator(String operation) {
        super(operation);
    }

    @Override
    protected int[] convertNumbersToDecimal(String num1, String num2) {
        int[] desNumbers = new int[2];
        desNumbers[0] = Integer.parseInt(num1, 8);
        desNumbers[1] = Integer.parseInt(num2, 8);
        return desNumbers;
    }

    @Override
    protected String convertResultToBase(int result){
        return Integer.toOctalString(result);
    }
}

