import { Given, Then, When } from "@badeball/cypress-cucumber-preprocessor";
import { ENumberSystemType } from "../../../../contracts/number-system-type.enum";
import { EOperation } from "../../../../contracts/operation.enum";
import MainPage from "../../e2e/main-page";

const decimalNumberSystemType = ENumberSystemType.Decimal;

Given("I visit {string}", (url: string) => {
  MainPage.visitPage(url);
});

When("I input data to form, set add operation and submit", (url: string) => {
  MainPage.performArithmeticOperation(
    decimalNumberSystemType,
    EOperation.Add,
    "10",
    "5"
  );
});

Then("I should get right add result", () => {
  MainPage.verifyOperation("15").should("exist");
});

When(
  "I input data to form, set subtract operation and submit",
  (url: string) => {
    MainPage.performArithmeticOperation(
      decimalNumberSystemType,
      EOperation.Subtract,
      "10",
      "5"
    );
  }
);

Then("I should get right subtract result", () => {
  MainPage.verifyOperation("5").should("exist");
});

When(
  "I input data to form, set multiply operation and submit",
  (url: string) => {
    MainPage.performArithmeticOperation(
      decimalNumberSystemType,
      EOperation.Multiply,
      "10",
      "5"
    );
  }
);

Then("I should get right multiply result", () => {
  MainPage.verifyOperation("50").should("exist");
});

When("I input data to form, set divide operation and submit", (url: string) => {
  MainPage.performArithmeticOperation(
    decimalNumberSystemType,
    EOperation.Divide,
    "10",
    "5"
  );
});

Then("I should get right divide result", () => {
  MainPage.verifyOperation("2").should("exist");
});

When("I input data to form", (url: string) => {
  MainPage.input("1234FRG@%^&*&U^YTutyrg4546");
});

Then("I should get only numbers", () => {
  MainPage.getNumberInput("Input first number")
    .find("input")
    .invoke("val")
    .should("eq", "12344546");
  MainPage.getNumberInput("Input second number")
    .find("input")
    .invoke("val")
    .should("eq", "12344546");
});

When("I try input zero to second input", (url: string) => {
  MainPage.performArithmeticOperation(
    decimalNumberSystemType,
    EOperation.Divide,
    "0",
    "0"
  );
});

Then("I should not be able to input zero", () => {
  MainPage.getNumberInput("Input first number")
    .find("input")
    .invoke("val")
    .should("eq", "0");
  MainPage.getNumberInput("Input second number")
    .find("input")
    .invoke("val")
    .should("eq", "");
});
