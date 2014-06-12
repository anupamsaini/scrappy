package com.scrappy.sites;

import com.google.inject.AbstractModule;

import com.scrappy.json.GsonReader;
import com.scrappy.json.desrializer.ElementJsonDeserializer;
import com.scrappy.json.desrializer.RelativeDeserializer;
import com.scrappy.json.desrializer.ScrapeJsonDeserializer;
import com.scrappy.json.desrializer.StepJsonDeserializer;

/**
 * Configuration for sites module.
 */
public class SitesModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(ScrapeConfig.class).to(ScrapeConfigImpl.class);

    //TODO(anupam): Bind these to interface and use annotatedBy.
    bind(ElementJsonDeserializer.class);
    bind(RelativeDeserializer.class);
    bind(ScrapeJsonDeserializer.class);
    bind(StepJsonDeserializer.class);

    // Inject validators.
//    bind(Validatable.class).to(ElementValidtor.class);
    bind(GsonReader.class);
  }
}
