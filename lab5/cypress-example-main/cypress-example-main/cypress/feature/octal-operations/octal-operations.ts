import { Given, Then, When } from "@badeball/cypress-cucumber-preprocessor";
import { ENumberSystemType } from "../../../../contracts/number-system-type.enum";
import { EOperation } from "../../../../contracts/operation.enum";
import MainPage from "../../e2e/main-page";

const octalNumberSystemType = ENumberSystemType.Octal;

Given("I visit {string}", (url: string) => {
  MainPage.visitPage(url);
});

When("I input data to form, set add operation and submit", (url: string) => {
  MainPage.performArithmeticOperation(
    octalNumberSystemType,
    EOperation.Add,
    "5",
    "5"
  );
});

Then("I should get right add result", () => {
  MainPage.verifyOperation("12").should("exist");
});

When(
  "I input data to form, set subtract operation and submit",
  (url: string) => {
    MainPage.performArithmeticOperation(
      octalNumberSystemType,
      EOperation.Subtract,
      "10",
      "6"
    );
  }
);

Then("I should get right subtract result", () => {
  MainPage.verifyOperation("2").should("exist");
});

When(
  "I input data to form, set multiply operation and submit",
  (url: string) => {
    MainPage.performArithmeticOperation(
      octalNumberSystemType,
      EOperation.Multiply,
      "18",
      "4"
    );
  }
);

Then("I should get right multiply result", () => {
  MainPage.verifyOperation("4").should("exist");
});

When("I input data to form, set divide operation and submit", (url: string) => {
  MainPage.performArithmeticOperation(
    octalNumberSystemType,
    EOperation.Divide,
    "10",
    "4"
  );
});

Then("I should get right divide result", () => {
  MainPage.verifyOperation("2").should("exist");
});
