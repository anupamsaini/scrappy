package com.scrappy.sites.google;

import com.google.inject.Inject;

import com.scrappy.protogen.Scrape.Element.By;
import com.scrappy.protogen.Scrape.Step;
import com.scrappy.protogen.Scrape.Step.Type;
import com.scrappy.sites.ScrapeConfig;

/**
 * Scrape {@link Step} configuration class for <a href="http://google.com">http://google.com</a>
 */
public class GoogleConfig {

  private final ScrapeConfig scrapeConfig;

  @Inject
  public GoogleConfig(ScrapeConfig scrapeConfig) {
    this.scrapeConfig = scrapeConfig;
  }

  private Step landingPage() {
    return scrapeConfig.buildStep(Type.SCRAPE, scrapeConfig.buildElement(By.ID, "gbqfsa"), "test");
  }

  public void buildConfiguration() {
    landingPage();
  }
}
