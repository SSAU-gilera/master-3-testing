public class DecimalCalculator extends BaseCalculator{
    public DecimalCalculator(String operation) {
        super(operation);
    }
    @Override
    protected int[] convertNumbersToDecimal(int num1, int num2) {
        int[] desNumbers = new int[2];
        desNumbers[0] = num1;
        desNumbers[1] = num2;
        return desNumbers;
    }

    @Override
    protected int convertResultToBase(int result){
        return result;
    }
}
