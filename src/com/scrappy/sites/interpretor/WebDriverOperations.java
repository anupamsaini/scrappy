package com.scrappy.sites.interpretor;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

/**
 * Performs the actual interaction with webpage using {@link WebDriver} API.
 */
public class WebDriverOperations {

  public interface WebDriverOperationsFactory {
    public WebDriverOperations create(@Assisted WebDriver driver);
  }

  private WebDriver webdriver;

  @Inject
  public WebDriverOperations(@Assisted WebDriver driver) {
    this.webdriver = driver;
  }

  public List<WebElement> getDocumentByid(String id) {
    return this.webdriver.findElements(By.id(id));
  }

  public List<WebElement> getDocumentByName(String name) {
    return this.webdriver.findElements(By.name(name));
  }

  public List<WebElement> getDocumentByXpath(String xpath) {
    return this.webdriver.findElements(By.xpath(xpath));
  }

  public List<WebElement> getDocumentByCss(String css) {
    return this.webdriver.findElements(By.className(css));
  }
}
