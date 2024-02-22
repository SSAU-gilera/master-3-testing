import { Given, Then, When } from "@badeball/cypress-cucumber-preprocessor";
import { ENumberSystemType } from "../../../../contracts/number-system-type.enum";
import { EOperation } from "../../../../contracts/operation.enum";
import MainPage from "../../e2e/main-page";

const bunaryNumberSystemType = ENumberSystemType.Binary;

Given("I visit {string}", (url: string) => {
  MainPage.visitPage(url);
});

When("I input data to form, set add operation and submit", (url: string) => {
  MainPage.performArithmeticOperation(
    bunaryNumberSystemType,
    EOperation.Add,
    "1",
    "1"
  );
});

Then("I should get right add result", () => {
  MainPage.verifyOperation("10").should("exist");
});

When(
  "I input data to form, set subtract operation and submit",
  (url: string) => {
    MainPage.performArithmeticOperation(
      bunaryNumberSystemType,
      EOperation.Subtract,
      "1000011",
      "10001"
    );
  }
);

Then("I should get right subtract result", () => {
  MainPage.verifyOperation("110010").should("exist");
});

When(
  "I input data to form, set multiply operation and submit",
  (url: string) => {
    MainPage.performArithmeticOperation(
      bunaryNumberSystemType,
      EOperation.Multiply,
      "10001",
      "101"
    );
  }
);

Then("I should get right multiply result", () => {
  MainPage.verifyOperation("1010101").should("exist");
});

When("I input data to form, set divide operation and submit", (url: string) => {
  MainPage.performArithmeticOperation(
    bunaryNumberSystemType,
    EOperation.Divide,
    "11111",
    "1011"
  );
});

Then("I should get right divide result", () => {
  MainPage.verifyOperation("10").should("exist");
});
