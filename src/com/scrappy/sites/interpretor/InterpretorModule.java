package com.scrappy.sites.interpretor;

import java.net.URI;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import com.google.common.collect.Maps;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.scrappy.protogen.Scrape.Scraper;
import com.scrappy.sites.SitesModule;

public class InterpretorModule extends AbstractModule {

  @Override
  protected void configure() {
    install(new SitesModule());
  }

  @Provides
  public WebDriver getWebdriver() {
    return new PhantomJSDriver();
  }

  @Provides
  @Singleton
  public Map<URI, Scraper> getDomainToScraperRegistry() {
    // TODO(anupam): Instead of providing provider, Figure out a better way. Perhaps MultiBindings
    // API
    return Maps.newHashMap();
  }
}
