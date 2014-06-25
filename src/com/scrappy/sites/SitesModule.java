package com.scrappy.sites;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Provides;
import com.google.inject.TypeLiteral;

import com.scrappy.json.desrializer.ElementJsonDeserializer;
import com.scrappy.json.desrializer.RelativeDeserializer;
import com.scrappy.json.desrializer.ScrapeJsonDeserializer;
import com.scrappy.json.desrializer.StepJsonDeserializer;
import com.scrappy.protogen.Scrape.Element;
import com.scrappy.protogen.Scrape.Relative;
import com.scrappy.protogen.Scrape.Scraper;
import com.scrappy.protogen.Scrape.Step;

/**
 * Configuration for sites module.
 */
public class SitesModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(ScrapeConfig.class).to(ScrapeConfigImpl.class);

    bind(new TypeLiteral<JsonDeserializer<Element>>() {}).to(ElementJsonDeserializer.class);
    bind(new TypeLiteral<JsonDeserializer<Relative>>() {}).to(RelativeDeserializer.class);
    bind(new TypeLiteral<JsonDeserializer<Scraper>>() {}).to(ScrapeJsonDeserializer.class);
    bind(new TypeLiteral<JsonDeserializer<Step>>() {}).to(StepJsonDeserializer.class);
  }

  @Provides
  private Gson registerDesearilizerTypeAdaptors(Injector injector) {
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.registerTypeAdapter(Element.class,
        injector.getInstance(Key.get(new TypeLiteral<JsonDeserializer<Element>>() {})));
    gsonBuilder.registerTypeAdapter(Relative.class,
        injector.getInstance(Key.get(new TypeLiteral<JsonDeserializer<Relative>>() {})));
    gsonBuilder.registerTypeAdapter(Step.class,
        injector.getInstance(Key.get(new TypeLiteral<JsonDeserializer<Step>>() {})));
    gsonBuilder.registerTypeAdapter(Scraper.class,
        injector.getInstance(Key.get(new TypeLiteral<JsonDeserializer<Scraper>>() {})));
    return gsonBuilder.create();
  }
}
