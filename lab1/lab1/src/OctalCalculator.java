public class OctalCalculator extends BaseCalculator{
    public OctalCalculator(String operation) {
        super(operation);
    }

    @Override
    protected int[] convertNumbersToDecimal(int num1, int num2) {
        int[] desNumbers = new int[2];
        desNumbers[0] = Integer.parseInt(Integer.toString(num1), 8);
        desNumbers[1] = Integer.parseInt(Integer.toString(num2), 8);
        return desNumbers;
    }

    @Override
    protected int convertResultToBase(int result){
        return Integer.parseInt(Integer.toOctalString(result));
    }
}
