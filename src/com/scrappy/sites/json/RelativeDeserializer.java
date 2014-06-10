package com.scrappy.sites.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import com.scrappy.sites.json.ScrapeGsonRepresentation.Element;
import com.scrappy.sites.json.ScrapeGsonRepresentation.Relative;

import java.lang.reflect.Type;

/**
 * Deserializes a json string to a {@link Relative}.
 */
public class RelativeDeserializer implements JsonDeserializer<Relative> {

  public boolean hasObjectArray(Element[] objects) {
    return null != objects && objects.length > 0 ? true : false;
  }

  @Override
  public Relative deserialize(final JsonElement json, final Type typeOfT,
      final JsonDeserializationContext context) throws JsonParseException {
    final JsonObject jsonObject = json.getAsJsonObject();
    final Relative relative= new Relative();
    final Element[] elements = context.deserialize(jsonObject.get("elements"), Element[].class);
    //TODO(anupam): Relative element has to have atleast one element to scrape.
    if(hasObjectArray(elements)) {
      relative.setElements(elements);
    }
    // Parent id will be set in the calling Element.
    return relative;
  }

}
