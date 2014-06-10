package com.scrappy.sites.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import com.scrappy.sites.json.ScrapeGsonRepresentation.Element;
import com.scrappy.sites.json.ScrapeGsonRepresentation.Step;

import java.lang.reflect.Type;


/**
 * Deserializes a json string to a {@link Step}.
 */
public class StepJsonDeserializer implements JsonDeserializer<Step> {

  public boolean hasObjectArray(Element[] objects) {
    return null != objects && objects.length > 0 ? true : false;
  }

  @Override
  public Step deserialize(final JsonElement json, final Type typeOfT,
      final JsonDeserializationContext context) throws JsonParseException {
    final JsonObject jsonObject = json.getAsJsonObject();
    final Element[] elements = context.deserialize(jsonObject.get("elements"), Element[].class);

    final Step step = new Step();
    step.setType(jsonObject.get("type").getAsString());
    step.setCallbackClass(jsonObject.get("callback").getAsString());
    // A step may or may not have elements.
    // TODO(anupam): If type scrape then element must be present. And callback has no meaning in
    // navigate step.
    if (hasObjectArray(elements)) {
      step.setElements(elements);
    }
    return step;
  }

}
