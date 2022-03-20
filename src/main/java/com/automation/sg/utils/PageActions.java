package com.automation.sg.utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageActions {
    private static final Logger LOGGER = Logger.getLogger(PageActions.class.getName());

    public void clickElement(WebDriver driver, WebElement element, String message) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement waitElement = wait.until(ExpectedConditions.elementToBeClickable(element));
        try {
            LOGGER.info(message);
            waitElement.click();
        }
        catch (Exception e){
            LOGGER.info(e.getMessage());
        }
    }

    public void enterText(WebDriver driver, WebElement element, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement waitElement = wait.until(ExpectedConditions.elementToBeClickable(element));
        try {
           LOGGER.info("Enter text : "+text+" into element : "+element);
            waitElement.sendKeys(text);
        }
        catch (Exception e){
            LOGGER.info(e.getMessage());
        }
    }

    public void enterKeyPress(WebDriver driver, WebElement element) {
        try {
            LOGGER.info("Press enter key");
            element.sendKeys(Keys.ENTER);
        }
        catch (Exception e){
            LOGGER.info(e.getMessage());
        }
    }

    public void moveToElement(WebDriver driver, WebElement element) {
        try {
            LOGGER.info("Move to Element : "+element);
            Actions act = new Actions(driver);
           Action action = act.moveToElement(element).build();
            action.perform();
        }
        catch(Exception e){
            LOGGER.info(e.getMessage());
        }
    }

    public void doubleClick(WebDriver driver, WebElement element) {
        try {
            LOGGER.info("Double click Element : "+element);
            Actions act = new Actions(driver);
           Action action = act.doubleClick(element).build();
            action.perform();
        }
        catch(Exception e){
            LOGGER.info(e.getMessage());
        }
    }
}
