Feature: Calculations Testing

  Background:
  Given Empty database

  @calculate
  Scenario: Calculate addition with binary numbers
    Given Operation is "add"
    And Number system is "binary"
    When First number is "1101"
    And Second number is "1010"
    Then Result is "10111"

  @calculate
  Scenario: Calculate addition with decimal numbers
    Given Operation is "add"
    And Number system is "decimal"
    When Numbers are "23;12"
    Then Result is "35"

  @calculate
  Scenario Outline: Calculate division with decimal numbers
    Given Operation is "divide"
    And Number system is "decimal"
    When First number is "<num1>"
    And Second number is "<num2>"
    Then Result is "<result>"
    Examples:
      | num1 | num2 | result |
      | 30   | 3    | 10     |
      | 4    | 4    | 1      |
      | 180  | 60   | 3      |
      | 36   | 6    | 6      |

  @count
  Scenario: Find operations between dates with decimal numbers
    Given Execute operations
      | num1 | num2 | base    | operation |
      | 20   | 10   | decimal | divide    |
      | 30   | 20   | decimal | multiply  |
      | 40   | 30   | decimal | subtract  |
      | 40   | 30   | decimal | add       |
    And Number system is "decimal"
    And Operation is "add"
    When Start date is "2023-11-08"
    And End date is "2023-11-08"
    Then The number of operations should be "1"

  @find
  Scenario: Find multiply operations
    Given Execute operations
      | num1 | num2 | base    | operation |
      | 20   | 10   | decimal | divide    |
      | 30   | 20   | decimal | multiply  |
      | 40   | 30   | decimal | subtract  |
      | 40   | 30   | decimal | add       |
    Then Get all "multiply" operations