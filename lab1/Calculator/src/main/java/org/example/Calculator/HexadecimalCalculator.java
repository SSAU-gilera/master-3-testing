package org.example.Calculator;

public class HexadecimalCalculator extends BaseCalculator{
    public HexadecimalCalculator(String operation) {
        super(operation);
    }

    @Override
    protected int[] convertNumbersToDecimal(String num1, String num2) {
        int[] desNumbers = new int[2];
        desNumbers[0] = Integer.parseInt(num1, 16);
        desNumbers[1] = Integer.parseInt(num2, 16);
        return desNumbers;
    }

    @Override
    protected String convertResultToBase(int result){
        return Integer.toHexString(result);
    }
}
