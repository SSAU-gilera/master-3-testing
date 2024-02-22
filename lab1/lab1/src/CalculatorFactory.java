public class CalculatorFactory {
    public Calculator createCalculator(String base, String operation) {
        return switch (base) {
            case "binary" -> new BinaryCalculator(operation);
            case "octal" -> new OctalCalculator(operation);
            case "decimal" -> new DecimalCalculator(operation);
            case "hexadecimal" -> new HexadecimalCalculator(operation);
            default -> throw new IllegalArgumentException("Wrong choice of number system");
        };
    }
}

