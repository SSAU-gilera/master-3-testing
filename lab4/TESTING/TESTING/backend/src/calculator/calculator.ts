import { ENumberSystemType } from "../../../contracts/number-system-type.enum";
import { EOperation } from "../../../contracts/operation.enum";

abstract class ACalculator {
  private readonly numberSystem: number;
  private readonly operation: EOperation;

  constructor(numberSystem: number, operation: EOperation) {
    this.numberSystem = numberSystem;
    this.operation = operation;
  }

  public getResult(a: string, b: string): string | null {
    switch (this.operation) {
      case EOperation.Add:
        return this.add(a, b);
      case EOperation.Subtract:
        return this.subtract(a, b);
      case EOperation.Divide:
        return this.divide(a, b);
      case EOperation.Multiply:
        return this.multiply(a, b);
      default:
        return null;
    }
  }

  private add(a: string, b: string): string {
    return (
      parseInt(a, this.numberSystem) + parseInt(b, this.numberSystem)
    ).toString(this.numberSystem);
  }

  private subtract(a: string, b: string): string {
    return (
      parseInt(a, this.numberSystem) - parseInt(b, this.numberSystem)
    ).toString(this.numberSystem);
  }

  private multiply(a: string, b: string): string {
    return (
      parseInt(a, this.numberSystem) * parseInt(b, this.numberSystem)
    ).toString(this.numberSystem);
  }

  private divide(a: string, b: string): string {
    return (
      parseInt(a, this.numberSystem) / parseInt(b, this.numberSystem)
    ).toString(this.numberSystem);
  }
}

class BinaryCalculator extends ACalculator {
  constructor(operation: EOperation) {
    super(2, operation);
  }
}

class OctalCalculator extends ACalculator {
  constructor(operation: EOperation) {
    super(8, operation);
  }
}

class DecimalCalculator extends ACalculator {
  constructor(operation: EOperation) {
    super(10, operation);
  }
}

class HexadecimalCalculator extends ACalculator {
  constructor(operation: EOperation) {
    super(16, operation);
  }
}

class CalculatorFactory {
  public createCalculator(
    system: ENumberSystemType,
    operation: EOperation
  ): ACalculator | null {
    switch (system) {
      case ENumberSystemType.Binary:
        return new BinaryCalculator(operation);
      case ENumberSystemType.Octal:
        return new OctalCalculator(operation);
      case ENumberSystemType.Decimal:
        return new DecimalCalculator(operation);
      case ENumberSystemType.Hexadecimal:
        return new HexadecimalCalculator(operation);
      default:
        return null;
    }
  }
}

export default CalculatorFactory;
