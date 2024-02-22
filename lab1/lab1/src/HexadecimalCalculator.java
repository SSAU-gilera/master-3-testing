public class HexadecimalCalculator extends BaseCalculator{
    public HexadecimalCalculator(String operation) {
        super(operation);
    }

    @Override
    protected int[] convertNumbersToDecimal(int num1, int num2) {
        int[] desNumbers = new int[2];
        desNumbers[0] = Integer.parseInt(Integer.toString(num1), 16);
        desNumbers[1] = Integer.parseInt(Integer.toString(num2), 16);
        return desNumbers;
    }

    @Override
    protected int convertResultToBase(int result){
        return Integer.parseInt(Integer.toHexString(result));
    }
}
