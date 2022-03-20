#Author: Yogesh Kamble
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

Feature: Validate Vue feature

  @Scn_01
  Scenario: Add single task and validate
    Given user opens vue homepage
    When User adds 1 task "Task1" in the ToDo list
    Then Validate "Task1" is added in the ToDo List
    And "Task1" is available in Active List
    And count of items left is 1

  @Scn_02
  Scenario: Complete active task and validate
    Given user opens vue homepage
    When User adds 1 task "Task1" in the ToDo list
    And Completes the added task "Task1"
    Then Validate "Task1" is marked as completed
    And "Task1" is not present in Active List
    And count of items left is 0

  @Scn_03
  Scenario: Complete multiple active tasks and validate
    Given user opens vue homepage
    When User adds 3 tasks in the ToDo list
      | Task1 |
      | Task2 |
      | Task3 |
    And Completes the added task "Task1"
    Then Validate "Task1" is marked as completed
    And "Task1" is not present in Active List
    And Tasks are present in Active list
      | Task2 |
      | Task3 |
    And count of items left is 2

  @Scn_04
  Scenario: Validate Task can be deleted from all the tabs
    Given user opens vue homepage
    When User adds 3 tasks in the ToDo list
      | Task1 |
      | Task2 |
      | Task3 |
    And clicks on delete task "Task1"
    Then Validate "Task1" is removed from the list
    And count of items left is 2

  @Scn_05
  Scenario: Validate completed Task can be deleted from completed tab
    Given user opens vue homepage
    When User adds 3 tasks in the ToDo list
      | Task1 |
      | Task2 |
      | Task3 |
    And Completes the added task "Task2"
    And clicks on delete task "Task2"
    Then Validate "Task1" is removed from the list
    And count of items left is 2

  @Scn_06
  Scenario Outline: Validate URL against all the tabs
    Given user opens vue homepage
    When User adds 1 task "Task1" in the ToDo list
    And Completes the added task "Task1"
    And clicks on "<Tab>" tab
    Then Validate the URL contains word "<url>"

    Examples:
      | Tab       | url       |
      | Active    | active    |
      | Completed | completed |
      | All       | all       |

  @Scn_07
  Scenario: Validate clear completed option is not visible if no task is completed
    Given user opens vue homepage
    When User adds 1 task "Task1" in the ToDo list
    Then Validate clear completed button is not present on screen

  @Scn_08
  Scenario: Validate clear completed removes all completed tasks from the list
    Given user opens vue homepage
    When User adds 3 tasks in the ToDo list
      | Task1 |
      | Task2 |
      | Task3 |
    And Completes the added task "Task2"
    And Completes the added task "Task3"
    And clicks on clear completed task button
    Then Validate "Task2" is removed from the list
    And Validate "Task3" is removed from the list