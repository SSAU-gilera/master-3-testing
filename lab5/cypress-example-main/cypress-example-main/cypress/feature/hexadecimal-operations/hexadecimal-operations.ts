import { Given, Then, When } from "@badeball/cypress-cucumber-preprocessor";
import { ENumberSystemType } from "../../../../contracts/number-system-type.enum";
import { EOperation } from "../../../../contracts/operation.enum";
import MainPage from "../../e2e/main-page";

const hexadecimalNumberSystemType = ENumberSystemType.Hexadecimal;

Given("I visit {string}", (url: string) => {
  MainPage.visitPage(url);
});

When("I input data to form, set add operation and submit", (url: string) => {
  MainPage.performArithmeticOperation(
    hexadecimalNumberSystemType,
    EOperation.Add,
    "c",
    "1"
  );
});

Then("I should get right add result", () => {
  MainPage.verifyOperation("d").should("exist");
});

When(
  "I input data to form, set subtract operation and submit",
  (url: string) => {
    MainPage.performArithmeticOperation(
      hexadecimalNumberSystemType,
      EOperation.Subtract,
      "32",
      "6"
    );
  }
);

Then("I should get right subtract result", () => {
  MainPage.verifyOperation("2c").should("exist");
});

When(
  "I input data to form, set multiply operation and submit",
  (url: string) => {
    MainPage.performArithmeticOperation(
      hexadecimalNumberSystemType,
      EOperation.Multiply,
      "7",
      "6"
    );
  }
);

Then("I should get right multiply result", () => {
  MainPage.verifyOperation("2a").should("exist");
});

When("I input data to form, set divide operation and submit", (url: string) => {
  MainPage.performArithmeticOperation(
    hexadecimalNumberSystemType,
    EOperation.Divide,
    "f",
    "f"
  );
});

Then("I should get right divide result", () => {
  MainPage.verifyOperation("1").should("exist");
});

When("I input data to form with specific lettrs", (url: string) => {
  MainPage.performArithmeticOperation(
    hexadecimalNumberSystemType,
    EOperation.Divide,
    "qwertyuiopasdfghjklzxcvbnm",
    "qwertyuiopasdfghjklzxcvbnm"
  );
});

Then("I should get see a b c d e f", () => {
  MainPage.getNumberInput("Input first number")
    .find("input")
    .invoke("val")
    .should("eq", "eadfcb");
  MainPage.getNumberInput("Input second number")
    .find("input")
    .invoke("val")
    .should("eq", "eadfcb");
});
