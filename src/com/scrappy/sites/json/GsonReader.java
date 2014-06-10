package com.scrappy.sites.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.scrappy.sites.json.ScrapeGsonRepresentation.Element;
import com.scrappy.sites.json.ScrapeGsonRepresentation.Relative;
import com.scrappy.sites.json.ScrapeGsonRepresentation.Step;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Reader for converting Json to corresponding {@link ScrapeGsonRepresentation}.
 */
public class GsonReader {

  public static void readJson() throws IOException {
    // Configure GSON
    final GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.registerTypeAdapter(ScrapeGsonRepresentation.class, new ScrapeJsonDeserializer());
    gsonBuilder.registerTypeAdapter(Step.class, new StepJsonDeserializer());
    gsonBuilder.registerTypeAdapter(Element.class, new ElementJsonDeserializer());
    gsonBuilder.registerTypeAdapter(Relative.class, new RelativeDeserializer());
    final Gson gson = gsonBuilder.create();
    // Read the JSON data
    try (Reader data =
        new InputStreamReader(GsonReader.class.getResourceAsStream("test.json"), "UTF-8")) {
      // Parse JSON to Java
      final ScrapeGsonRepresentation scrape = gson.fromJson(data, ScrapeGsonRepresentation.class);
    }
  }
}
