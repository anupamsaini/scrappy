package com.scrappy.sites.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import com.scrappy.sites.json.ScrapeGsonRepresentation.Step;

import java.lang.reflect.Type;

/**
 * Deserializes a json string to an {@link ScrapeGsonRepresentation}.
 */
public class ScrapeJsonDeserializer implements JsonDeserializer<ScrapeGsonRepresentation> {

  @Override
  public ScrapeGsonRepresentation deserialize(final JsonElement json, final Type typeOfT,
      final JsonDeserializationContext context) throws JsonParseException {
    final JsonObject jsonObject = json.getAsJsonObject();
    final Step[] steps = context.deserialize(jsonObject.get("steps"), Step[].class);

    final ScrapeGsonRepresentation scrape = new ScrapeGsonRepresentation();
    scrape.setName(jsonObject.get("name").getAsString());
    scrape.setSteps(steps);
    return scrape;
  }
}
