public class BinaryCalculator extends BaseCalculator {
    public BinaryCalculator(String operation) {
        super(operation);
    }

    @Override
    protected int[] convertNumbersToDecimal(int num1, int num2) {
        int[] desNumbers = new int[2];
        desNumbers[0] = Integer.parseInt(Integer.toString(num1), 2);
        desNumbers[1] = Integer.parseInt(Integer.toString(num2), 2);
        return desNumbers;
    }

    @Override
    protected int convertResultToBase(int result){
        return Integer.parseInt(Integer.toBinaryString(result));
    }
}
