package com.scrappy.sites.interpretor;

import java.net.URI;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.google.inject.Inject;

/**
 * Performs the actual interaction with webpage using {@link WebDriver} API.
 */
class WebDriverOperations {

  private final WebDriver webdriver;

  @Inject
  public WebDriverOperations(WebDriver driver) {
    this.webdriver = driver;
  }

  protected WebDriver getDriver() {
    return this.webdriver;
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

  public void sendKeys(WebElement webElement, String key) {
    webElement.sendKeys(key);
  }

  public void submit(WebElement webElement) {
    webElement.submit();
  }

  public void getWebPage(URI uri) {
    this.webdriver.get(uri.toString());
  }
}
