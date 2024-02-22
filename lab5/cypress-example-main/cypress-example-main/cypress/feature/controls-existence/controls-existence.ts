import { Given } from "@badeball/cypress-cucumber-preprocessor";

import MainPage from "../../e2e/main-page";

const { When, Then } = require("@badeball/cypress-cucumber-preprocessor");

When("I visit {string}", (url: string) => {
  MainPage.visitPage(url);
});

Then("I should see first input fields", () => {
  MainPage.getNumberInput("Input first number").should("exist");
});

Then("I should see second input fields", () => {
  MainPage.getNumberInput("Input second number").should("exist");
});

Then("I should see a select number system dropdown", () => {
  MainPage.getSelect("Select number system").should("exist");
});

Then("I should see a select operation dropdown", () => {
  MainPage.getSelect("Select operation").should("exist");
});

Then("I should see a submit button", () => {
  MainPage.getSubmitButton().should("exist");
});
