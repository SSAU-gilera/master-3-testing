public abstract class BaseCalculator implements Calculator {
    private String operation;

    public BaseCalculator(String operation) {
        this.operation = operation;
    }

    @Override
    public int calculate(String operation, String base, int num1, int num2) {
        int result = simpleCalculation(convertNumbersToDecimal(num1, num2), operation);
        return convertResultToBase(result);
    }

    protected abstract int[] convertNumbersToDecimal(int num1, int num2);

    private int simpleCalculation (int[] numbers, String operation){
        int result = 0;
        int num1 = numbers[0];
        int num2 = numbers[1];

        switch (operation) {
            case "add" -> result = num1 + num2;
            case "subtract" -> result = num1 - num2;
            case "multiply" -> result = num1 * num2;
            case "divide" -> result = divisionCheck(num1,num2);
            default -> throw new IllegalArgumentException("Wrong choice of operation");
        };
        return result;
    }

    protected int divisionCheck(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero is not possible.");
        }
        return a / b;
    }

    protected abstract int convertResultToBase(int result);
}
