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
 * Deserializes a json string to an {@link Element}.
 */
public class ElementJsonDeserializer implements JsonDeserializer<Element> {


  public boolean hasObjectArray(Relative[] objects) {
    return null != objects && objects.length > 0 ? true : false;
  }

  @Override
  public Element deserialize(final JsonElement json, final Type typeOfT,
      final JsonDeserializationContext context) throws JsonParseException {
    final JsonObject jsonObject = json.getAsJsonObject();
    final Relative[] relatives = context.deserialize(jsonObject.get("relatives"), Relative[].class);

    final Element element = new Element();
    element.setBy(jsonObject.get("by").getAsString());
    element.setValue(jsonObject.get("value").getAsString());
    element.setId(element.getBy() + "_" + element.getValue());
    // Check and add parent id to relatives.
    if(hasObjectArray(relatives)) {
      for(int i=0;i< relatives.length ; i++) {
        Relative relative = relatives[i];
        relative.setParentId(element.getId());
      }
      element.setRelatives(relatives);
    }
    return element;
  }

}
