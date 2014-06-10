package com.scrappy.sites.json;

import com.anupams.protogen.Scrape;

import java.util.Arrays;

/**
 * A simple POJO mapping a json string to {@link Scrape} protobuf.
 */
public class ScrapeGsonRepresentation {

  private String name;
  private Step[] steps;

  /**
   * @return the steps
   */
  public Step[] getSteps() {
    return steps;
  }

  /**
   * @param steps the steps to set
   */
  public void setSteps(Step[] steps) {
    this.steps = steps;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "ScrapeGsonRepresentation [name=" + name + ", steps=" + Arrays.toString(steps) + "]";
  }

  public static class Step {

    @Override
    public String toString() {
      return "Step [type=" + type + ", callbackClass=" + callbackClass + ", elements="
          + Arrays.toString(elements) + "]";
    }

    private String type;
    private String callbackClass;
    private Element[] elements;

    /**
     * @return the elements
     */
    public Element[] getElements() {
      return elements;
    }

    /**
     * @param elements the elements to set
     */
    public void setElements(Element[] elements) {
      this.elements = elements;
    }

    /**
     * @return the type
     */
    public String getType() {
      return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
      this.type = type;
    }

    /**
     * @return the callbackClass
     */
    public String getCallbackClass() {
      return callbackClass;
    }

    /**
     * @param callbackClass the callbackClass to set
     */
    public void setCallbackClass(String callbackClass) {
      this.callbackClass = callbackClass;
    }
  }

  public static class Element {
    @Override
    public String toString() {
      return "Element [by=" + by + ", id=" + id + ", value=" + value + ", relatives="
          + Arrays.toString(relatives) + "]";
    }

    private String by;
    private String id;
    private String value;
    private Relative[] relatives;

    public boolean hasRelatives() {
      return null != relatives && relatives.length > 0 ? true : false;
    }

    /**
     * @return the by
     */
    public String getBy() {
      return by;
    }

    /**
     * @param by the by to set
     */
    public void setBy(String by) {
      this.by = by;
    }

    /**
     * @return the id
     */
    public String getId() {
      return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
      this.id = id;
    }

    /**
     * @return the value
     */
    public String getValue() {
      return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
      this.value = value;
    }

    /**
     * @return the relatives
     */
    public Relative[] getRelatives() {
      return relatives;
    }

    /**
     * @param relatives the relatives to set
     */
    public void setRelatives(Relative[] relatives) {
      this.relatives = relatives;
    }
  }

  public static class Relative {

    @Override
    public String toString() {
      return "Relative [parentId=" + parentId + ", elements=" + Arrays.toString(elements) + "]";
    }

    private String parentId;
    private Element[] elements;

    /**
     * @return the parentId
     */
    public String getParentId() {
      return parentId;
    }

    /**
     * @param parentId the parentId to set
     */
    public void setParentId(String parentId) {
      this.parentId = parentId;
    }

    /**
     * @return the elements
     */
    public Element[] getElements() {
      return elements;
    }

    /**
     * @param elements the elements to set
     */
    public void setElements(Element[] elements) {
      this.elements = elements;
    }
  }
}
