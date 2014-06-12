package com.scrappy.json.desrializer;

import com.google.common.collect.Lists;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.inject.Inject;

import com.scrappy.protogen.Scrape.Scraper;
import com.scrappy.protogen.Scrape.Step;
import com.scrappy.sites.ScrapeConfig;

import java.lang.reflect.Type;

/**
 * Deserializes a json string to an {@link Scraper}.
 */
public class ScrapeJsonDeserializer implements JsonDeserializer<Scraper> {

  private final ScrapeConfig scrapeConfig;

  @Inject
  public ScrapeJsonDeserializer(ScrapeConfig scrapeConfig) {
    this.scrapeConfig = scrapeConfig;
  }

  @Override
  public Scraper deserialize(final JsonElement json, final Type typeOfT,
      final JsonDeserializationContext context) throws JsonParseException {
    final JsonObject jsonObject = json.getAsJsonObject();
    final Step[] steps = context.deserialize(jsonObject.get("steps"), Step[].class);
    String scraperName = jsonObject.get("name").getAsString();
    return scrapeConfig.buildScraper(scraperName, Lists.newArrayList(steps));
  }
}
