package com.automation.sg.vue.autPages;

import com.automation.sg.utils.PageActions;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class VueHomePage {
	WebDriver driver;
	@FindBy(xpath = "//input[@placeholder='What needs to be done?']")
	WebElement enterTask;
	@FindBy(xpath = "//ul[@class='todo-list']//li//div//label")
	List<WebElement> tasksAdded;
	@FindBy(xpath = "//a[text()='Active']")
	WebElement activeTasksTab;
	@FindBy(xpath = "//a[text()='Completed']")
	WebElement completedTasksTab;
	@FindBy(xpath = "//span[@class='todo-count']/strong")
	WebElement count;
	@FindBy(xpath = "//button[@class='clear-completed']")
	WebElement clearCompletedButton;
	PageActions pageAction = new PageActions();

	String clickTab = "//a[text()='%s']";
	String completeTaskButton = "//label[text()='%s']//parent::div//input";
	String taskStatus = "//label[text()='%s']//parent::div//parent::li";
	String taskDescription = "//label[text()='%s']//parent::div//label";
	String deleteTaskButton = "//label[text()='%s']//parent::div//button";

	public VueHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void enterTaskDetails(String task) {
		pageAction.enterText(driver, enterTask, task);
		pageAction.enterKeyPress(driver, enterTask);
	}

	public boolean isTaskAdded(String taskName) {
		boolean flag = false;
		for (WebElement element : tasksAdded) {
			flag = element.getText().equalsIgnoreCase(taskName);
			if (flag)
				break;
		}
		return flag;
	}

	public boolean isTaskActive(String taskName) {
		pageAction.clickElement(driver, activeTasksTab, "Click Active tasks");
		boolean flag = false;
		for (WebElement element : tasksAdded) {
			flag = element.getText().equalsIgnoreCase(taskName);
			if (flag)
				break;
		}
		return flag;
	}

	public int getCount() {
		String listCount = count.getText();
		return Integer.parseInt(listCount);
	}

	public void completeTask(String taskName) {
		String xpath = completeTaskButton.replace("%s", taskName);
		WebElement completeCheckBox = driver.findElement(By.xpath(xpath));
		completeCheckBox.click();
	}

	public boolean isTaskCompleted(String taskName) {
		String xpath = taskStatus.replace("%s", taskName);
		WebElement taskStatusElement = driver.findElement(By.xpath(xpath));
		boolean isCompleted = taskStatusElement.getAttribute("class").equalsIgnoreCase("todo Completed");
		return isCompleted;
	}

	public void deleteTask(String taskName) {
		String xpath = deleteTaskButton.replace("%s", taskName);
		WebElement element = driver.findElement(By.xpath(xpath));
		String moveToXpath = taskStatus.replace("%s", taskName);
		WebElement moveToElement = driver.findElement(By.xpath(moveToXpath));
		pageAction.moveToElement(driver, moveToElement);
		pageAction.clickElement(driver, element, "CLicking delete task button");
	}

	public boolean isTaskRemoved(String taskName) {
		boolean isRemoved = true;
		for (WebElement element : tasksAdded) {
			if (element.getText().equalsIgnoreCase(taskName)) {
				isRemoved = false;
				break;
			}
		}
		return isRemoved;
	}

	public boolean isClearCompletedButtonVisible() {
		return (!clearCompletedButton.isDisplayed());
	}

	public void clickClearCompleted() {
		pageAction.clickElement(driver, clearCompletedButton, "Click clear completed button");
	}

	public void clickTab(String tabName) {
		String xpath = clickTab.replace("%s", tabName);
		WebElement element = driver.findElement(By.xpath(xpath));
		pageAction.clickElement(driver, element, "Click Tab :" + tabName);
	}

	public boolean isTextPresentInURL(String text) {
		String url = driver.getCurrentUrl();
		return url.contains(text);
	}
}
