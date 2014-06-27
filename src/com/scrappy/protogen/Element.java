package com.scrappy.protogen;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Objects;

/**
 * The DOM element along with the information it has to be accessed, for i.e. through XPATH, ID or
 * CSS html attributes.
 */
public final class Element {

  /**
   * The user provided id to identify an element in the resultset. It must be unique in a step, it's
   * advisable to construct it with the form <ELEMENT.BY>_<ELEMENT_VALUE>
   */
  public final String id;

  /**
   * The enum describes the how an element is to be searched i.e. by XPATH, ID or CSS etc.
   */
  public final By search_by;

  /**
   * The value used to search an element in the DOM.
   */
  public final String value;

  /**
   * The elements that are to be searched relative to the containing element.
   */
  public final List<Relative> relative_element;

  public Element(String id, By search_by, String value, List<Relative> relative_element) {
    this.id = id;
    this.search_by = search_by;
    this.value = value;
    this.relative_element = ImmutableList.copyOf(relative_element);
  }

  private Element(Builder builder) {
    this(builder.id, builder.search_by, builder.value, builder.relative_element);
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof Element)) {
      return false;
    }
    Element o = (Element) other;
    return Objects.equals(id, o.id) && Objects.equals(search_by, o.search_by)
        && Objects.equals(value, o.value) && Objects.equals(relative_element, o.relative_element);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, search_by, value, relative_element);
  }

  public static final class Builder {

    public String id;
    public By search_by;
    public String value;
    public List<Relative> relative_element;

    public Builder() {}

    public Builder(Element message) {
      if (message == null) {
        return;
      }
      this.id = message.id;
      this.search_by = message.search_by;
      this.value = message.value;
      this.relative_element = Lists.newArrayList(message.relative_element);
    }

    /**
     * The user provided id to identify an element in the resultset. It must be unique in a step,
     * it's advisable to construct it with the form <ELEMENT.BY>_<ELEMENT_VALUE>
     */
    public Builder id(String id) {
      this.id = id;
      return this;
    }

    /**
     * The enum describes the how an element is to be searched i.e. by XPATH, ID or CSS etc.
     */
    public Builder search_by(By search_by) {
      this.search_by = search_by;
      return this;
    }

    /**
     * The value used to search an element in the DOM.
     */
    public Builder value(String value) {
      this.value = value;
      return this;
    }

    /**
     * The elements that are to be searched relative to the containing element.
     */
    public Builder relative_element(List<Relative> relative_element) {
      this.relative_element = relative_element;
      return this;
    }

    public Element build() {
      return new Element(this);
    }
  }

  public enum By {
    XPATH(0), CSS(1), ID(2), CLASS(3);

    private final int value;

    private By(int value) {
      this.value = value;
    }

    public int getValue() {
      return value;
    }
  }
}
