package com.scrappy.sites.interpretor;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.google.inject.Inject;
import com.scrappy.protogen.Scrape.Scraper;
import com.scrappy.protogen.Scrape.Step;

public class ScrapeInterprator {

  // TODO(anupam): Uri to scrape must not be part of class state. Implement a concurrent task queue
  // to obtain the URI.
  private URI uri;

  private WebDriverOperations driverOperations;
  private ScrapeConfigRegistry registery;

  /**
   * Constructs a scrape interpreter.
   * 
   * @param webdriver the webdriver instance backed by a browser
   * @param registery the registry of domain to scrape config mapping
   */
  @Inject
  public ScrapeInterprator(WebDriverOperations webdriver, ScrapeConfigRegistry registery) {
    this.driverOperations = webdriver;
    this.registery = registery;
  }

  public void interpret(URI googUri) throws UnsupportedEncodingException, IOException {
    uri = googUri;
    Scraper scraper = registery.getScraperConfig(uri);
    // TODO(anupam): Handle the case when scraper is not found.
    interpretSteps(scraper.getStepList());
  }

  private void testDriver() {
    driverOperations.getWebPage(this.uri);
    WebElement element = driverOperations.getDocumentByName("q").get(0);
    System.out.println(element);
    element.sendKeys("Cheese!");
    element.submit();
    System.out.println("Page title is: " + driverOperations.getDriver().getTitle());
    driverOperations.getDriver().quit();
  }

  private void interpretSteps(List<Step> steps) {
    testDriver();
    for (Step step : steps) {
      if (Step.Type.NAVIGATE == step.getStepType()) {
      } else if (Step.Type.SCRAPE == step.getStepType()) {

      } else if (Step.Type.PAGINATED == step.getStepType()) {
      }
    }
  }

  private void navigate() {

  }

}
