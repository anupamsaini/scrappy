package com.scrappy.sites.interpretor;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.scrappy.json.GsonReader;
import com.scrappy.protogen.Scrape.Scraper;
import com.scrappy.protogen.Scrape.Step;

public class ScrapeInterprator {

  private URI uri;
  private WebDriver driver;
  private GsonReader gsonReader;

  @Inject
  public ScrapeInterprator(WebDriver webdriver, GsonReader gsonReader) {
    this.driver = webdriver;
    this.gsonReader = gsonReader;
  }

  public static void main(String[] args) throws IOException, URISyntaxException {
    Injector injector = Guice.createInjector(new InterpretorModule());
    URI googUri = new URI("http://www.google.com");
    ScrapeInterprator scrapeInterprator = injector.getInstance(ScrapeInterprator.class);
    scrapeInterprator.interpret(googUri);
  }

  public void interpret(URI googUri) throws UnsupportedEncodingException, IOException {
    uri = googUri;
    try (Reader data =
        new InputStreamReader(GsonReader.class.getResourceAsStream("test.json"), "UTF-8")) {
      // Parse JSON to Java
      Scraper scraper = this.gsonReader.jsonStringToObject(data);
      System.out.println(scraper.getScraperName());
      interpretSteps(scraper.getStepList());
    }
  }

  private void testDriver() {
    driver.get(this.uri.toString());
    WebElement element = driver.findElement(By.name("q"));
    System.out.println(element);
    element.sendKeys("Cheese!");
    element.submit();
    System.out.println("Page title is: " + driver.getTitle());
    driver.quit();
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
