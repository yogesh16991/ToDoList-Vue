package stepDefinitions;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.automation.sg.utils.PageActions;
import com.automation.sg.utils.DriverUtil;
import com.automation.sg.vue.autPages.VueHomePage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class StepDefinition {
    WebDriver driver;
    VueHomePage homePage;

   final static String baseUrl = "http://todomvc.com/examples/vue/";
    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(StepDefinition.class.getName());

    {
        String log4jPath = System.getProperty("user.dir") + "/log4j.properties";
        PropertyConfigurator.configure(log4jPath);
    }

    @Given("user opens vue homepage")
    public void user_opens_vue_homepage() throws InterruptedException {
        DriverUtil util = new DriverUtil();
        driver = util.getDriver();
        LOGGER.info("Launch browser...");
        driver.get(baseUrl);
        LOGGER.info("Open Vue home page");
    }

    @When("User adds {int} task {string} in the ToDo list")
    public void userAddsTaskInTheToDoList(int count, String taskName) {
        homePage = new VueHomePage(driver);
        homePage.enterTaskDetails(taskName);
    }

    @Then("Validate {string} is added in the ToDo List")
    public void validateIsAddedInTheToDoList(String taskName) {
        LOGGER.assertLog(homePage.isTaskAdded(taskName), "Task not added successfully");
    }

    @Then("{string} is available in Active List")
    public void isAvailableInActiveList(String taskName) {

        LOGGER.assertLog(homePage.isTaskActive(taskName), "Task is not present under active list ");
    }

    @Then("count of items left is {int}")
    public void countOfItemsLeftIs(Integer expectedCount) {
        LOGGER.assertLog(homePage.getCount() == expectedCount, "Count Matches");
    }

    @When("Completes the added task {string}")
    public void completes_the_added_task(String taskName) {
        homePage.completeTask(taskName);
    }

    @Then("Validate {string} is marked as completed")
    public void validate_is_removed_from_the_active_list(String taskName) {
        LOGGER.assertLog(homePage.isTaskCompleted(taskName), "Task is marked as completed");
    }

    @Then("{string} is not present in Active List")
    public void is_available_in_completed_list(String taskName) {
        Assert.assertFalse(homePage.isTaskActive(taskName), "Task is present in Active tab");
    }

    @When("User adds {int} tasks in the ToDo list")
    public void user_adds_tasks_in_the_to_do_list(Integer count, DataTable dataTable) {
        homePage = new VueHomePage(driver);
        List<String> taskNames = dataTable.asList();
        for (String taskName : taskNames) {
            homePage.enterTaskDetails(taskName);
        }
    }

    @Then("Tasks are present in Active list")
    public void tasks_are_present_in_active_list(DataTable dataTable) {
        List<String> taskNames = dataTable.asList();
        for (String taskName : taskNames) {
            LOGGER.assertLog(homePage.isTaskActive(taskName), "Task " + taskName + " is Active ");
        }
    }


    @And("clicks on delete task {string}")
    public void clicksOnDeleteTask(String taskName) {
        homePage.deleteTask(taskName);
    }

    @Then("Validate {string} is removed from the list")
    public void validateIsRemovedFromTheList(String taskName) {
        LOGGER.assertLog(homePage.isTaskRemoved(taskName), "Task is removed from the list");
    }

   
    @Then("Validate clear completed button is not present on screen")
    public void validateClearCompletedButtonIsNotPresentOnScreen() {
        LOGGER.assertLog(homePage.isClearCompletedButtonVisible(), "Clear completed button is visible");
    }

    @And("clicks on clear completed task button")
    public void clicksOnClearCompletedTaskButton() {
        homePage.clickClearCompleted();
    }

    @And("clicks on {string} tab")
    public void clicksOnTab(String tabName) {
        homePage.clickTab(tabName);
    }

    @Then("Validate the URL contains word {string}")
    public void validateTheURLContainsWord(String text) {
        LOGGER.assertLog(homePage.isTextPresentInURL(text), "Text " + text + " is not present in the url");
    }
    
    @After
    public void tearDown() {
        driver.quit();
    }
}
