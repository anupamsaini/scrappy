package com.scrappy.json.desrializer;

import com.google.common.collect.Lists;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.inject.Inject;

import com.scrappy.protogen.Scrape.Element;
import com.scrappy.protogen.Scrape.Relative;
import com.scrappy.sites.ScrapeConfig;

import java.lang.reflect.Type;

/**
 * Deserializes a json string to a {@link Relative}.
 */
public class RelativeDeserializer implements JsonDeserializer<Relative> {

  private final ScrapeConfig scrapeConfig;

  @Inject
  public RelativeDeserializer(ScrapeConfig scrapeConfig) {
    this.scrapeConfig = scrapeConfig;
  }

  public boolean hasObjectArray(Element[] objects) {
    return null != objects && objects.length > 0 ? true : false;
  }

  @Override
  public Relative deserialize(final JsonElement json, final Type typeOfT,
      final JsonDeserializationContext context) throws JsonParseException {
    final Element[] elements =
        context.deserialize(json.getAsJsonObject().get("elements"), Element[].class);
    // (TODO:anupam) Hack, setting parent id dummy, will be set in the parent Element.
    return scrapeConfig.buildRelative("dummy", Lists.newArrayList(elements));
  }
}
