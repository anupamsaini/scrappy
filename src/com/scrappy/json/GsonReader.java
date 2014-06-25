package com.scrappy.json;

import com.google.gson.Gson;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import com.scrappy.protogen.Scrape.Scraper;
import com.scrappy.sites.SitesModule;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Reader for converting Json to corresponding {@link Scraper}.
 */
public class GsonReader {

  private final Gson gson;

  @Inject
  public GsonReader(Gson gson) {
    this.gson = gson;
  }

  /**
   * Converts a json to {@link Scraper}.
   *
   * @param data the json data to be converted
   * @return the {@link Scraper} representation of json data
   */
  public Scraper jsonStringToObject(Reader data) {
    Scraper scrape = gson.fromJson(data, Scraper.class);
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
