package com.scrappy.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import com.scrappy.json.desrializer.ElementJsonDeserializer;
import com.scrappy.json.desrializer.RelativeDeserializer;
import com.scrappy.json.desrializer.ScrapeJsonDeserializer;
import com.scrappy.json.desrializer.StepJsonDeserializer;
import com.scrappy.protogen.Scrape.Element;
import com.scrappy.protogen.Scrape.Relative;
import com.scrappy.protogen.Scrape.Scraper;
import com.scrappy.protogen.Scrape.Step;
import com.scrappy.sites.SitesModule;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Reader for converting Json to corresponding {@link Scraper}.
 */
public class GsonReader {

  private final GsonBuilder gsonBuilder;

  @Inject
  public GsonReader(ElementJsonDeserializer elementJsonDeserializer,
      RelativeDeserializer relativeDeserializer, ScrapeJsonDeserializer scarpeJsonDeserializer,
      StepJsonDeserializer stepJsonDeserializer, GsonBuilder gsonBuilder) {
    this.gsonBuilder = gsonBuilder;
    // Configure GSON
    gsonBuilder.registerTypeAdapter(Element.class, elementJsonDeserializer);
    gsonBuilder.registerTypeAdapter(Relative.class, relativeDeserializer);
    gsonBuilder.registerTypeAdapter(Scraper.class, scarpeJsonDeserializer);
    gsonBuilder.registerTypeAdapter(Step.class, stepJsonDeserializer);
  }

  /**
   * Converts a json to {@link Scraper}.
   *
   * @param data the json data to be converted
   * @return the {@link Scraper} representation of json data
   */
  public Scraper jsonStringToObject(Reader data) {
    final Gson gson = gsonBuilder.create();
    final Scraper scrape = gson.fromJson(data, Scraper.class);
    System.out.println(scrape);
    return scrape;
  }

  public static void main(final String[] args) throws IOException {
    Injector injector = Guice.createInjector(new SitesModule());
    GsonReader gsonReader = injector.getInstance(GsonReader.class);
    try (Reader data =
        new InputStreamReader(GsonReader.class.getResourceAsStream("test.json"), "UTF-8")) {
      // Parse JSON to Java
      gsonReader.jsonStringToObject(data);
    }
  }
}
