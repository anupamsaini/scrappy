package com.scrappy.json.desrializer;

import com.google.common.collect.Lists;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.inject.Inject;

import com.scrappy.protogen.Scrape.Element;
import com.scrappy.protogen.Scrape.Relative;
import com.scrappy.sites.ScrapeConfig;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Deserializes a json string to an {@link Element}.
 */
public class ElementJsonDeserializer implements JsonDeserializer<Element> {

  private final ScrapeConfig scrapeConfig;

  @Inject
  public ElementJsonDeserializer(ScrapeConfig scrapeConfig) {
    this.scrapeConfig = scrapeConfig;
  }

  private boolean hasObjectArray(Relative[] objects) {
    return null != objects && objects.length > 0 ? true : false;
  }

  @Override
  public Element deserialize(final JsonElement json, final Type typeOfT,
      final JsonDeserializationContext context) throws JsonParseException {
    final JsonObject jsonObject = json.getAsJsonObject();
    final Relative[] relatives = context.deserialize(jsonObject.get("relatives"), Relative[].class);

    String getElememtBy = jsonObject.get("by").getAsString();
    String elmentValue = jsonObject.get("value").getAsString();
    Element element = scrapeConfig.buildElement(getElememtBy, elmentValue);

    if (hasObjectArray(relatives)) {
      List<Relative> updatedRelatives = Lists.newArrayListWithCapacity(relatives.length);
      for (Relative relative : relatives) {
        // Replace dummy value with parent id in relative.
        updatedRelatives.add(relative.toBuilder().setParentId(element.getId()).build());
      }
      return scrapeConfig.addRelativesToElement(element, updatedRelatives);
    }
    return element;
  }
}
