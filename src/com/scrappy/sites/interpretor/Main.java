package com.scrappy.sites.interpretor;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.scrappy.json.GsonReader;
import com.scrappy.protogen.Scrape.Scraper;

public class Main {

  public static void main(String args[]) throws URISyntaxException, UnsupportedEncodingException,
      IOException {
    URI uri = new URI("http://www.google.com");

    Injector injector = Guice.createInjector(new InterpretorModule());
    ScrapeConfigRegistry registery = injector.getInstance(ScrapeConfigRegistry.class);
    registery.addToConfig(uri, readScraperConfig(injector.getInstance(GsonReader.class)));
    // Obtain a interpreter instance and begin playing
    ScrapeInterprator scrapeInterprator = injector.getInstance(ScrapeInterprator.class);
    scrapeInterprator.interpret(uri);
  }

  private static Scraper readScraperConfig(GsonReader gsonReader)
      throws UnsupportedEncodingException, IOException {
    try (Reader data =
        new InputStreamReader(GsonReader.class.getResourceAsStream("test.json"), "UTF-8")) {
      // Parse JSON to Java
      return gsonReader.jsonStringToObject(data);
    }
  }
}
