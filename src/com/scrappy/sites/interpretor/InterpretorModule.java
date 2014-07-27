package com.scrappy.sites.interpretor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.scrappy.sites.SitesModule;
import com.scrappy.sites.interpretor.WebDriverOperations.WebDriverOperationsFactory;

public class InterpretorModule extends AbstractModule {

  @Override
  protected void configure() {
    install(new FactoryModuleBuilder().build(WebDriverOperationsFactory.class));
    install(new SitesModule());
  }

  @Provides
  public WebDriver getWebdriver() {
    return new FirefoxDriver();
  }
}
