# ToDoList-Vue

This file contains details to import the project, steps to execute it and view the reports

**Technology Used**  
1. Programming language - Java
2. IDE - Intellij
3. BDD Framework - Cucumber
4. Build Management tool - Maven
5. Testing framework - TestNG
6. Reporting - Cucumber reports


**This project contains multiple BDD scenarios written in GWT**

 1> All the test cases are written in gherkin and are stored in a feature file named "vue.feature" under src/test/java/features location
 
 2> Have Used POM design pattern to make it scalable in case of more pages gets added in future.
 
**Framework overview** 

 1. src/main/Java - 
  a. It contains utils package having PageActions class which has methods related to selenium like finding elements, enter text etc. used through out the project and a DriverUtil class to instantiate webdriver
  b. It contains aut pages package which will have page class for our webpage to hold properties of the webpage  
 2. src/test/java - It contains the runner package (which has TestRunner Class), stepDefinition package (Which has StepDefintion class for GWT)
 3. src/test/resources - It contains features directory (Which has the vue.feature) and driver directory which contains webdrivers


 
**Steps to Import and Run the project**

 1. Import project in IDE as Maven project 
 2. Right click pom.xml -> Maven -> Update project -> OK
 3. Project will resolve all the dependencies.
 4. Install plugins separately for your IDE - Cucumber, Cucumber for Java, and Gherkin.
 5. Once above all os installed, we are good to go on "how to run part".
 6. Execution is by using TestRunner class under src/test/java/runner location. Open TestRunner -> Right click -> click "Run TestRunner" or "TestNG Test"

**To run scenarios in Parallel (Parallel execution)**

 1. Go to TestRunner class and edit @DataProvider(parallel = false) to @DataProvider(parallel = true) 
 2. Right click -> click "Run TestRunner" or "TestNG Test"
 3. All scenarios will execute in Parallel

**To view execution reports**

 1. Go to target folder -> cucumber-reports.html.html


**Potential for future work**
 1. Advance reports like Allure or Extent reports can be implemented 
 2. Assertions could be better
 3. More scenarios could be added like selecting arrows to mark all tasks as completed, editing existing task's name,etc
 4. Logging can be improved
 5. Screenshots can be added incase of failures
 6. Cross browser testing can be implemented 
