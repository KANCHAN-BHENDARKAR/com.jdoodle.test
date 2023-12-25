Feature: JDoodle Online Compiler Testing

  Background:
    Given User navigates to the JDoodle Compiler website

  Scenario: Verify Page Load
    Then JDoodle homepage should be displayed

  Scenario: Test the functionality of clearing code
    And User clear Existing code with selection of New Project
    Then the code input area should be empty

  Scenario: Execute Java Code
    When User enters valid Java code
    And User clicks on the execute button
    Then Expected output from the executed Java code should be displayed

  Scenario: Change Language
    When User selects a different programming language
    Then Code editor should display the selected programming language code

  Scenario: Error Handling
    When User enters invalid Java code
    And User clicks on the execute button
    Then Error message for invalid code should be displayed in Output Result
