package com.scrappy.json.desrializer;

import com.google.common.collect.Lists;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.inject.Inject;

import com.scrappy.protogen.Scrape.Element;
import com.scrappy.protogen.Scrape.Step;
import com.scrappy.sites.ScrapeConfig;

import java.lang.reflect.Type;


/**
 * Deserializes a json string to a {@link Step}.
 */
public class StepJsonDeserializer implements JsonDeserializer<Step> {

  private final ScrapeConfig scrapeConfig;

  @Inject
  public StepJsonDeserializer(ScrapeConfig scrapeConfig) {
    this.scrapeConfig = scrapeConfig;
  }

  public boolean hasObjectArray(Element[] objects) {
    return null != objects && objects.length > 0 ? true : false;
  }

  @Override
  public Step deserialize(final JsonElement json, final Type typeOfT,
      final JsonDeserializationContext context) throws JsonParseException {
    final JsonObject jsonObject = json.getAsJsonObject();
    final Element[] elements = context.deserialize(jsonObject.get("elements"), Element[].class);

    String stepType = jsonObject.get("type").getAsString();
    String callbackClass = jsonObject.get("callback").getAsString();
    if (hasObjectArray(elements)) {
      scrapeConfig.buildStep(stepType, Lists.newArrayList(elements), callbackClass);
      return scrapeConfig.buildStep(stepType, Lists.newArrayList(elements), callbackClass);
    }
    return scrapeConfig.buildStep(stepType, Lists.<Element>newArrayList(), callbackClass);
  }
}
