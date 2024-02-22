class MainPage {
  static visitPage(url: string) {
    cy.visit(url);
  }

  static getNumberInput(label: string) {
    return cy.get(`app-number-input[label="${label}"]`);
  }

  static getSelect(label: string) {
    return cy.contains("mat-form-field", label);
  }

  static getSubmitButton() {
    return cy.contains("button", "Calculate");
  }

  static performArithmeticOperation(
    numberSystem,
    operation,
    firstNumber,
    secondNumber
  ) {
    MainPage.getSelect("Select number system").click();
    cy.get(`mat-option[ng-reflect-value="${numberSystem}"]`).click();

    MainPage.getSelect("Select operation").click();
    cy.get(`mat-option[ng-reflect-value="${operation}"]`).click();

    MainPage.getNumberInput("Input first number").type(firstNumber);
    MainPage.getNumberInput("Input second number").type(secondNumber);

    MainPage.getSubmitButton().click();
  }

  static verifyOperation(expectedResult) {
    return cy.get(".result-card").contains(`Last Result: ${expectedResult}`);
  }

  static getResult(value: string) {
    return cy.get(".result-card").find("span").contains(value);
  }

  static input(string: string): void {
    MainPage.getNumberInput("Input first number").type(string);
    MainPage.getNumberInput("Input second number").type(string);
  }
}

export default MainPage;
